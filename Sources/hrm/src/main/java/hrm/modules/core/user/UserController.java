package hrm.modules.core.user;

import hrm.base.common.ConstSetting.ERoles;
import hrm.base.common.annotations.api.ApiCommonResponse;
import hrm.base.common.annotations.auth.Authorization;
import hrm.base.common.response.ResponseTool;
import hrm.base.common.response.model.APIResponse;
import hrm.modules.core.auth.model.Role;
import hrm.modules.core.user.dto.UpdateUserDTO;
import hrm.modules.core.user.dto.UserRegisterDTO;
import hrm.modules.core.user.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("user")
@ApiCommonResponse
public class UserController {

    private final UserService userService;

    @Autowired
    UserController(@Qualifier("userService") UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "register", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "User register")
    public ResponseEntity<APIResponse> createUser(@Valid @RequestBody UserRegisterDTO userRegisterDTO) throws Exception {
        User user = new User();
        user.setUsername(userRegisterDTO.getUsername());
        user.setPassword(userRegisterDTO.getPassword());
        user.setEmail(userRegisterDTO.getEmail());
        Role role = new Role();
        role.setName(ERoles.ADMIN);
        user.setRole(role);
        return ResponseTool.POST_OK(this.userService.createUser(user));
    }


    @GetMapping(path = "my/profile", produces =  MediaType.APPLICATION_JSON_VALUE)
    @Authorization
    @Operation(description = "Get My profile, role = ADMIN, USER", summary =  "Get My", security =  @SecurityRequirement(name = "bearer-jwt" ))
    @PreAuthorize("@EndPointAuthorizer.authorizer({'ADMIN', 'USER'})")
    public ResponseEntity<APIResponse> getMyProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userService.getByUsername(authentication.getName());
        return ResponseTool.GET_OK(user);
    }

    @GetMapping(path =  "get-user-by-username/{username}", produces =  MediaType.APPLICATION_JSON_VALUE)
    @Operation(description =  "Get profile by id, role = ADMIN",
            summary = "Get by username",
            security = @SecurityRequirement(name = "bearer-jwt" ) )
    @PreAuthorize("@EndPointAuthorizer.authorizer({'ADMIN', 'USER'})")
    public ResponseEntity<APIResponse> getByUsername(@PathVariable String username){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userService.getByUsername(username);
        return ResponseTool.GET_OK(user);
    }
    @PutMapping (path =  "my/profile", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description =  "update my, role = ADMIN",
            summary = "update my",
            security = @SecurityRequirement(name = "bearer-jwt" ) )
    @PreAuthorize("@EndPointAuthorizer.authorizer({'ADMIN', 'USER'})")
    public ResponseEntity<APIResponse> updateMy(
            @Valid @RequestBody UpdateUserDTO updateUserDTO
            ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());
        User user = this.userService.getByUsername(authentication.getName());
        user.setFullname(Objects.isNull(updateUserDTO.getFullname()) ?
            updateUserDTO.getFullname() : user.getFullname());
        user.setEmail(Objects.isNull(updateUserDTO.getEmail()) ?
                updateUserDTO.getEmail() : user.getEmail());
        User result;
        if (updateUserDTO.getPassword() != null) {
            result = this.userService.updateUser(user);
        } else {
            result = this.userService.updateUserPassword(user, updateUserDTO.getPassword());
        }
        return ResponseTool.PUT_OK(result);
    }
}
