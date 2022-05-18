package com.example.cryptocurrencytrackingsystem.UserCurrencyService.Controllers;

import com.example.cryptocurrencytrackingsystem.Database.Service.DataServiceInterface;
import com.example.cryptocurrencytrackingsystem.Entity.UserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

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

    @GetMapping("/showCryptoForm")
    public String showCryptoForm(Model theModel){
        theModel.addAttribute("userAdd", new UserAddress());
        return "User/cryptoForm";
    }

    @PostMapping("/processUpdateUserAddress")
    public String processUpdateUserAddress(Model theModel,
                                           @Valid @ModelAttribute("userAdd") UserAddress userAddress,
                                           BindingResult theBindingResult,
                                           Principal principal){

        if (!theBindingResult.hasErrors()) {
            dataService.updateUserAddress(principal.getName(), userAddress);
            theModel.addAttribute("serverResponse", "Address added!");
            theModel.addAttribute("userAdd", new UserAddress());
        }
        return "User/cryptoForm";
    }

    @GetMapping("/showTrendingView")
    public String showTrendingView(Model theModel,
                                   @RequestParam("currencySymbol") String currencySymbol){
        theModel.addAttribute("currencySymbol", currencySymbol);
        return "User/currencyTrendingView";
    }
}
