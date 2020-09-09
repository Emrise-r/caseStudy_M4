package com.casestudy.controller;

import com.casestudy.exception.UserAlreadyExistException;
import com.casestudy.model.User;
import com.casestudy.service.user.RoleService;
import com.casestudy.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/home")
public class UserController {
//    @Autowired
//    ProductService productService;

    @Autowired
    UserService userServiceImpl;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleService roleServiceImpl;

    private String getPrincipal() {
        String username = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView(("login"));
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }

    @GetMapping()
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("username", getPrincipal());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView formCreate() {
        ModelAndView modelAndView = new ModelAndView("signUp");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveUser(@ModelAttribute("user") @Valid User user, HttpServletRequest request, Errors errors) {
        try {
            userServiceImpl.registerNewUserAccount(user);
        } catch (UserAlreadyExistException e) {
            ModelAndView modelAndView = new ModelAndView("signUp");
            modelAndView.addObject("message", "Existed");
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("signUp");
        modelAndView.addObject("message", "Successfully");
        modelAndView.addObject("user", user);
        return modelAndView;
    }



}
