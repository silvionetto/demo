package com.silvionetto.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/adm/users")
public class DemoUserController {

    @Autowired
    private DemoUserService demoUserService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", demoUserService.findAll());
        return "adm/users/index";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new DemoUser());
        return "adm/users/form";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", demoUserService.findById(id));
        return "adm/users/form";
    }

    @PostMapping
    public String saveUser(@ModelAttribute DemoUser user) {
        demoUserService.save(user);
        return "redirect:/adm/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        demoUserService.deleteById(id);
        return "redirect:/adm/users";
    }
}
