package net.javaguides.emsbackend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("employeeform")
public class EmployeeFormController {

    @GetMapping
    public String employeeForm() {
        return "employeeForm";
    }

}
