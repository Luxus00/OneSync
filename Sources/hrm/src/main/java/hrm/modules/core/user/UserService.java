package hrm.modules.core.user;

import hrm.base.common.BaseService;
import hrm.base.common.exception.BackendError;
import hrm.modules.core.user.model.QUser;
import hrm.modules.core.user.model.User;
import hrm.modules.core.user.repository.UserRepository;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService extends BaseService<QUser> {

    UserRepository userRepo;
    PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        super(QUser.user, User.class.getName());
        entityPathBuilder = new PathBuilder<>(this.modelClass, "user");
        this.userRepo = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Long countByUsername(String username) {
        return getJPAQueryFactory()
                    .select(qModel.username.count())
                    .where(qModel.username.eq(username))
                    .from(qModel)
                    .fetchOne();
    }

    public  User updateUser(User user) {
        userRepo.save(user);
        return  user;
    }

    public  User updateUserPassword(User user, String password
    ) {
        user.setPassword(passwordEncoder.encode(password));
        return updateUser(user);
    }

    public User createUser(User user) throws BackendError {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            user = userRepo.save(user);
        } catch (Exception ex) {
            throw new BackendError(HttpStatus.BAD_REQUEST, "Duplicate username");
        }
        user.setPassword(null);
        return user;
    }
    public User getByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}
