//package com.backend.template.domain.controller;
//
//import static org.springframework.web.bind.annotation.RequestMethod.GET;
//
//import com.backend.template.base.common.annotations.api.ApiCommonResponse;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@ApiCommonResponse
//@RequestMapping("/loginController")
//public class LoginController {
//    @RequestMapping(method = GET)
//    public String loginShow(@RequestParam(defaultValue = "true", required = false) boolean result,
//            Model model) {
//        model.addAttribute("isFail", result);
//        return "login";
//    }
//}
