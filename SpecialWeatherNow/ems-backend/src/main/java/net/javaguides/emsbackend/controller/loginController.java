package net.javaguides.emsbackend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.javaguides.emsbackend.entity.Employee;
import net.javaguides.emsbackend.repository.EmployeeRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/loginform")
public class loginController {
 
    EmployeeRepository employeeRepository;

    public loginController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @GetMapping
    public String loginForm() {
        return "loginForm"; 
    }

    @PostMapping
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        Employee emp = employeeRepository.findByEmailAndPassword(email, password);

        if (emp != null) {
            model.addAttribute("message", "Giriş başarılı.");
            return "successlogin";
        } else {
            model.addAttribute("message", "Giriş başarısız. Lütfen tekrar deneyin.");
        }
        return "loginForm"; 
    } 
}
