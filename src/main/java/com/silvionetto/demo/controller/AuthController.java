package com.silvionetto.demo.controller;

import com.silvionetto.demo.model.DemoUser;
import com.silvionetto.demo.service.DemoUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @Autowired
    private DemoUserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new DemoUser());
        return "register"; // Thymeleaf template
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") @Valid DemoUser demoUser, // Add @Valid for validation
                               BindingResult result,
                               RedirectAttributes redirectAttributes) {
        // Basic validation (you'd add more robust validation)
        if (userService.isUserNameTaken(demoUser.getUserName())) {
            result.rejectValue("username", "error.user", "Username is already taken.");
        }
        if (userService.isEmailTaken(demoUser.getEmail())) {
            result.rejectValue("email", "error.user", "Email is already registered.");
        }

        if (result.hasErrors()) {
            return "register";
        }

        userService.registerNewUser(demoUser);
        redirectAttributes.addFlashAttribute("message", "Registration successful! Please log in.");
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Thymeleaf template
    }
}
