package com.silvionetto.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DemoAdmController {

    @Autowired
    private DemoRoleRepository roleRepository;

    @GetMapping("/adm")
    public String adm() {
        return "adm/adm";
    }

    @GetMapping("/adm/roles")
    public String roles(Model model) {
        model.addAttribute("roles", roleRepository.findAll());
        return "adm/roles";
    }

    @GetMapping("/adm/role")
    public String role(Model model) {
        model.addAttribute("role", new DemoRole());
        return "adm/role";
    }

    @GetMapping("/adm/role/{id}")
    public String role(@PathVariable("id") Long id, Model model) {
        model.addAttribute("role", roleRepository.findById(id).orElse(new DemoRole()));
        return "adm/role";
    }

    @PostMapping("/adm/role/new")
    public String newRole(DemoRole role) {
        role.setId(null);
        roleRepository.save(role);
        return "redirect:/adm/roles";
    }

    @PostMapping("/adm/role/update")
    public String updateRole(DemoRole role) {
        roleRepository.save(role);
        return "redirect:/adm/roles";
    }

    @PostMapping("/adm/role/delete/{id}")
    public String deleteRole(@PathVariable("id") Long id) {
        roleRepository.deleteById(id);
        return "redirect:/adm/roles";
    }
}
