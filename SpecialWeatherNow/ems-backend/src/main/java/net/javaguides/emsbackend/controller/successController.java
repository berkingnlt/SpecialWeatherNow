package net.javaguides.emsbackend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class successController {

    @GetMapping("/success")
    public String success() {
        return "success";
    }
}
