package com.example.cryptocurrencytrackingsystem;

import com.example.cryptocurrencytrackingsystem.Database.Service.DataServiceInterface;
import com.example.cryptocurrencytrackingsystem.Entity.Currency;
import com.example.cryptocurrencytrackingsystem.Entity.Validation.CrmUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("/showSortedCurrencies")
    public String showSortedCurrencies(Model theModel, @RequestParam(required=false) String sortType, @RequestParam(required = false) Integer pageNumber) {
        if (pageNumber == null || pageNumber < 0){ pageNumber = 0; }
        theModel.addAttribute("currenciesData", getSortedCurrencies(sortType, pageNumber));
        theModel.addAttribute("currencySymbol", "");
        theModel.addAttribute("pageNumber", pageNumber);
        return "User/currenciesData";
    }

    private List<Currency> getSortedCurrencies(String sortType, Integer pageNumber){
        List<Currency> currencies;
        if (sortType != null) {
            int theSortField = Integer.parseInt(sortType);
            currencies = dataService.getSortedCurrencies(theSortField, pageNumber);
        } else {
            currencies = dataService.getSortedCurrencies(0, pageNumber);
        }
        return currencies;
    }

    @GetMapping("/showRegisterForm")
    public String showForm(Model theModel) {
        theModel.addAttribute("user", new CrmUser());
        return "Panel/registerForm";
    }

    @PostMapping("/processRegistrationData")
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

}
