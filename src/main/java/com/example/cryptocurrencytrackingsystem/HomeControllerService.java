package com.example.cryptocurrencytrackingsystem;

import com.example.cryptocurrencytrackingsystem.Database.Service.DataServiceInterface;
import com.example.cryptocurrencytrackingsystem.Entity.Validation.CrmUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequestMapping("/")
public class HomeControllerService {

    private DataServiceInterface dataService;

    @Autowired
    public void setDataServiceDAO(@Qualifier("dataService") DataServiceInterface dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/")
    public String showDashboard() {
        return "Panel/dashboard";
    }

    @GetMapping("/showRegisterForm")
    public String showForm(Model theModel) {
        theModel.addAttribute("user", new CrmUser());
        return "Panel/registerForm";
    }

    @GetMapping("/processRegistrationData")
    public String processForm(Model theModel,
                              @Valid @ModelAttribute("user") CrmUser theUser,
                              BindingResult theBindingResult) {


        if(theBindingResult.hasErrors()){
            return "Panel/registerForm";

        } else if (dataService.getUser(theUser.getUserName()) != null) {
            theModel.addAttribute("user", new CrmUser());
            theModel.addAttribute("registrationResponse", "User with this login already exist!");
            return "Panel/registerForm";
        } else {

            dataService.saveUser(theUser);
            theModel.addAttribute("user", new CrmUser());
            theModel.addAttribute("registrationResponse", "Account was created!");
            return "Panel/registerForm";
        }
    }

    @GetMapping("/showAccessDeniedPage")
    public String showAccessDeniedPage() {
        return "Panel/accessDeniedPage";
    }

}
