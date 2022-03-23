package com.example.cryptocurrencytrackingsystem.UserCurrencyService.Controllers;

import com.example.cryptocurrencytrackingsystem.Database.Service.DataServiceInterface;
import com.example.cryptocurrencytrackingsystem.Entity.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private DataServiceInterface dataService;

    @Autowired
    public void setDataServiceDAO(@Qualifier("dataService") DataServiceInterface dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/showLoginForm")
    public String showDashboard() {
        return "Panel/loginForm";
    }


    @GetMapping("/showSortedCurrencies")
    public String showSortedCurrencies(Model theModel, @RequestParam(required=false) String sortType) {
        theModel.addAttribute("currenciesData", getSortedCurrencies(sortType));
        return "User/currenciesData";
    }

    private List<Currency> getSortedCurrencies(String sortType){
        List<Currency> currencies;
        if (sortType != null) {
            int theSortField = Integer.parseInt(sortType);
            currencies = dataService.getSortedCurrencies(theSortField);
        } else {
            currencies = dataService.getSortedCurrencies(0);
        }
        return currencies;
    }


}
