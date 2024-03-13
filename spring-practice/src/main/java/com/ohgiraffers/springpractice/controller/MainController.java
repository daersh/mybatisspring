package com.ohgiraffers.springpractice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String defaultLocation(){
        return "main";
    }
    @GetMapping("main")
    public void main(){}
    @GetMapping("/next")
    public String nextLocation(){
        return "next";
    }
}
