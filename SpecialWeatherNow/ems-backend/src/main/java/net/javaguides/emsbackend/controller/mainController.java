package net.javaguides.emsbackend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("mainform")
public class mainController {

    @GetMapping
    public String mainForm() {
        return "mainForm";
    }

}
