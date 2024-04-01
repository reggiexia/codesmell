package org.demo.codesmell.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("hello")
    public String hello() {
        System.out.println("Hello, World!");
        return "Hello, World!";
    }
}
