package com.realworld.feature;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RequestMapping("/auth/success")
@Controller
public class TestController {
    @GetMapping
    public String test(@RequestParam("token") String token, Model model) {
        log.info("token :: {}", token);
        model.addAttribute("token", token);

        return "test";
    }
}
