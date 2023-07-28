package com.example.casestudy_movieproject.controller;

import com.example.casestudy_movieproject.model.User;
import com.example.casestudy_movieproject.model.enums.ERole;
import com.example.casestudy_movieproject.service.request.AuthService;
import com.example.casestudy_movieproject.service.request.LoginRequest;
import com.example.casestudy_movieproject.service.request.RegisterRequest;
import com.example.casestudy_movieproject.service.user.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@AllArgsConstructor
@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    private final AuthService authService;
    @GetMapping("/login")
    public String showLogin(Model model){
        LoginRequest user = new LoginRequest();
        model.addAttribute("user", user);
        return "client/login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("user") LoginRequest request,
                               BindingResult result,
                               Model model){
        authService.loadUserByUsername(request.getUsername());
        if(result.hasErrors()){
            return "client/login";
        }
        Optional<User> user = userService.findByUsernameIgnoreCase(request.getUsername());
        if(user.get().getRole().equals(ERole.ROLE_ADMIN)) {
            return "admin/index";
        }
        else {
           return "client/index";
        }

    }


    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        RegisterRequest user = new RegisterRequest();
        model.addAttribute("user", user);
        return "client/register";
    }


    @PostMapping("/register")
    public String registration(@Valid @ModelAttribute("user") RegisterRequest request,
                               BindingResult result,
                               Model model){
        authService.checkUsernameOrEmail(request, result);
        if(result.hasErrors()){
            return "client/register";
        }
        authService.register(request);
        return "redirect:/login";
    }
    @GetMapping("/demo")
    public String handle(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Optional<User> user = userService.findByUsernameIgnoreCase(username);
        if(user.get().getRole().equals(ERole.ROLE_ADMIN)) {
            return "redirect:/admin";
        }
        else {
            return "redirect:/client";
        }
    }
}

