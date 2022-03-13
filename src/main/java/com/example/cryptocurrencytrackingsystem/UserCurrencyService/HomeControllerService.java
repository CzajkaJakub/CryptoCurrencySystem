package com.example.cryptocurrencytrackingsystem.UserCurrencyService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class HomeControllerService {


    @GetMapping("/showMainSystem")
    public String showMainSystem() {
        return "userCurrencyService/currencySystem";
    }
}