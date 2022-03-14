package com.example.cryptocurrencytrackingsystem.UserCurrencyService;

import com.example.cryptocurrencytrackingsystem.Database.UserServiceInterface;
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
@RequestMapping("/loginSystem")
public class LoginSystemController {

    private UserServiceInterface userService;

    @Autowired
    public void setCustomerDAO(@Qualifier("userService") UserServiceInterface userService) {
        this.userService = userService;
    }

    @GetMapping("/logIntoSystem")
    public String showForm(Model theModel) {
        theModel.addAttribute("user", new User());
        return "loginSystemPages/userLoginSystem";
    }

    @GetMapping("/processLoginData")
    public String processForm(@Valid @ModelAttribute("user") User theUser,
                              BindingResult theBindingResult) {

        if (theBindingResult.hasErrors()) {
            return "loginSystemPages/userLoginSystem";
        } else if (userService.getUser(theUser.getLogin()) == null) {
            theBindingResult.rejectValue("login", "error.login", "Invalid username, try again!");
            return "loginSystemPages/userLoginSystem";
        } else if (checkLoginData(theUser)) {
            theBindingResult.rejectValue("login", "error.login", "zalogowano!");
            return "loginSystemPages/userLoginSystem";
        } else {
            theBindingResult.rejectValue("login", "error.login", "Wrong data, try again!");
            return "loginSystemPages/userLoginSystem";
        }
    }



    private boolean checkLoginData(User theUser) {
        User tempUser = userService.getUser(theUser.getLogin());
        return  tempUser.getLogin().equals(theUser.getLogin()) &&
                tempUser.getPassword().equals(theUser.getPassword()) &&
                tempUser.getEmail().equals(theUser.getEmail());
    }
}
