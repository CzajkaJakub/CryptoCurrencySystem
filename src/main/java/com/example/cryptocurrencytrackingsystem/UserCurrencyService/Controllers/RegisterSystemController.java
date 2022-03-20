package com.example.cryptocurrencytrackingsystem.UserCurrencyService.Controllers;

import com.example.cryptocurrencytrackingsystem.Database.DataServiceInterface;
import com.example.cryptocurrencytrackingsystem.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registerSystem")
public class RegisterSystemController {

    private DataServiceInterface userService;

    @Autowired
    public void setDataServiceDAO(@Qualifier("dataService") DataServiceInterface userService) {
        this.userService = userService;
    }

    @GetMapping("/registerNewUser")
    public String showForm(Model theModel) {
        theModel.addAttribute("user", new User());
        return "loginSystemPages/userRegistration";
    }

    @GetMapping("/processRegistrationData")
    public String processForm(Model theModel,
                              @Valid @ModelAttribute("user") User theUser,
                              BindingResult theBindingResult) {

        if(theBindingResult.hasErrors()){
            return "loginSystemPages/userRegistration";
        } else if (userService.getUser(theUser.getLogin()) != null){
            theBindingResult.rejectValue("login", "error.login", "User with this login already exist!");
            return "loginSystemPages/userRegistration";
        } else {
            userService.saveUser(theUser);
            theBindingResult.rejectValue("login", "error.login", "Account was created!");
            return "loginSystemPages/userRegistration";

        }
    }
}
