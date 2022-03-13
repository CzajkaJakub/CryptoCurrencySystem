package com.example.portfolio.UserCurrencyService;

import com.example.portfolio.Database.UserServiceInterface;
import com.example.portfolio.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminServiceController {

    private UserServiceInterface userService;

    @Autowired
    public void setCustomerDAO(@Qualifier("userService") UserServiceInterface userService) {
        this.userService = userService;
    }

    @GetMapping("/showAdminForm")
    public String showAdminForm(Model theModel) {
        theModel.addAttribute("admin", new User());
        return "userCurrencyService/adminLoginForm";
    }

    @GetMapping("/processAdminLogin")
    public String processAdminLogin(Model theModel,
                                    @Valid @ModelAttribute("admin") User admin,
                                    BindingResult theBindingResult) {

        // ma errory
        if (theBindingResult.hasErrors()) {
            return "userCurrencyService/adminLoginForm";


            // zalogowano jako admin
        } else if (checkAdminData(admin)) {
            theModel.addAttribute("usersData", userService.getUsers());
            return "userCurrencyService/adminPanel";

            // zle dane admina
        } else {
            theBindingResult.rejectValue("login", "error.login", "Wrong data, try again!");
            return "userCurrencyService/adminLoginForm";
        }
    }

    private boolean checkAdminData(User admin) {
        User adminAccount = userService.getAdminAccount();
        return adminAccount.getLogin().equals(admin.getLogin()) &&
                adminAccount.getPassword().equals(admin.getPassword()) &&
                adminAccount.getEmail().equals(admin.getEmail());
    }

    @GetMapping("/deleteAnAccount")
    public String deleteAnAccount(Model theModel,
                                  @RequestParam("userId") Integer userId) {

        userService.deleteAnAccount(userId);
        theModel.addAttribute("usersData", userService.getUsers());
        return "userCurrencyService/adminPanel";
    }

    @GetMapping("/updateAnAccount")
    public String updateAnAccount(Model theModel,
                                  @RequestParam("userId") Integer userId) {

        theModel.addAttribute("userToUpdate", userService.getUser(userId));
        return "userCurrencyService/updateUserForm";
    }

    @GetMapping("/processAnUpdate")
    public String processAnUpdate(Model theModel,
                                  @Valid @ModelAttribute("userToUpdate") User updatedUser,
                                  BindingResult theBindingResult) {

        theModel.addAttribute("userToUpdate", updatedUser);
        if(theBindingResult.hasErrors()){
            return "userCurrencyService/updateUserForm";
        } else {
            userService.updateUser(updatedUser);
            theBindingResult.rejectValue("login", "error.login", "Account was updated!");
            return "userCurrencyService/updateUserForm";
        }
    }
}


