package com.example.cryptocurrencytrackingsystem;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class HomeControllerService {

    @GetMapping( "/")
    public String showHomePage() {
        return "index";
    }
    
    @GetMapping("/showMainSystem")
    public String showMainSystem() {
        return "loginSystemPages/currencySystem";
    }
}