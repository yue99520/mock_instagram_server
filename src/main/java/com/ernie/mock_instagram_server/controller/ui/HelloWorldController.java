package com.ernie.mock_instagram_server.controller.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloWorldController {

    @GetMapping("/")
    public String helloIndex() {
        return "home";
    }

}
