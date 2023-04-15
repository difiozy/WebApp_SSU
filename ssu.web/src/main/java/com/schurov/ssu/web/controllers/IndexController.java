package com.schurov.ssu.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/main")
    public String getIndex() {
        return "index";
    }

}
