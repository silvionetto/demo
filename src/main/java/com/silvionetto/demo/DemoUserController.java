package com.silvionetto.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/adm/users")
public class DemoUserController {

    @Autowired
    private DemoUserRepository userRepository;

    @Autowired
    private DemoRoleRepository roleRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "adm/users/index";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new DemoUser());
        model.addAttribute("allRoles", roleRepository.findAll());
        return "adm/users/edit";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        userRepository.findById(id).ifPresent(user -> {
            model.addAttribute("user", user);
            List<DemoRole> allRoles = roleRepository.findAll();
            model.addAttribute("allRoles", allRoles);
        });
        return "adm/users/edit";
    }

    @PostMapping("/save")
    public String saveUser(DemoUser user) {
        // The user object from the form will have its basic properties (userName, email)
        // and the set of roles bound from the form's checkboxes.
        // For a new user, the ID will be null.
        userRepository.save(user);
        return "redirect:/adm/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        // In a real app, you should check if the user exists before deleting.
        userRepository.deleteById(id);
        return "redirect:/adm/users";
    }
}