package com.example.cryptocurrencytrackingsystem.UserCurrencyService.Controllers;

import com.example.cryptocurrencytrackingsystem.Database.DataServiceInterface;
import com.example.cryptocurrencytrackingsystem.Entity.User;
import com.example.cryptocurrencytrackingsystem.UserCurrencyService.SortUtils.SortUtilsUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/adminSystem")
public class AdminSystemController {

    private DataServiceInterface dataService;

    @Autowired
    public void setDataServiceDAO(@Qualifier("dataService") DataServiceInterface dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/logIntoSystem")
    public String showForm(Model theModel) {
        theModel.addAttribute("admin", new User());
        return "loginSystemPages/adminLoginForm";
    }

    @GetMapping("/processAdminLogin")
    public String processForm(@Valid @ModelAttribute("admin") User admin,
                              BindingResult theBindingResult) {

        if (theBindingResult.hasErrors()) {
            return "loginSystemPages/adminLoginForm";
        } else if (dataService.getAdminAccount(admin) != null) {
            return "adminPanel/adminPanel";
        } else {
            theBindingResult.rejectValue("login", "error.login", "Wrong data, try again!");
            return "loginSystemPages/adminLoginForm";
        }
    }

    @GetMapping("/deleteAnAccount")
    public String deleteAnAccount(Model theModel,
                                  @RequestParam("userId") Integer userId) {

        dataService.deleteAnAccount(userId);
        theModel.addAttribute("usersData", dataService.getUsers(SortUtilsUsers.id_sort));
        return "adminPanel/adminPanel";
    }

    @GetMapping("/updateAnAccount")
    public String updateAnAccount(Model theModel,
                                  @RequestParam("userId") Integer userId) {

        theModel.addAttribute("userToUpdate", dataService.getUser(userId));
        return "adminPanel/updateUserForm";
    }

    @GetMapping("/processAnUpdate")
    public String processAnUpdate(Model theModel,
                                  @Valid @ModelAttribute("userToUpdate") User updatedUser,
                                  BindingResult theBindingResult) {

        if(!theBindingResult.hasErrors()) {
            dataService.updateUser(updatedUser);
            theBindingResult.rejectValue("login", "error.login", "Account updated!");
            theModel.addAttribute("usersData", dataService.getUsers(SortUtilsUsers.id_sort));
        }
        return "adminPanel/updateUserForm";
    }

    @GetMapping("/showTable")
    public String showTable(Model theModel, @RequestParam(required=false) String sort) {
        theModel.addAttribute("usersData", getSortedList(sort));
        return "adminPanel/dataTable";
    }


    @GetMapping("/showDashboard")
    public String showDashboard() {
        return "adminPanel/adminPanel";
    }


    @GetMapping("/showCurrencies")
    public String showCurrencies(Model theModel) {
        theModel.addAttribute("currenciesData", dataService.getCurrencies());
        return "adminPanel/currenciesData";
    }

    @GetMapping("/showSortedCurrencies")
    public String showSortedCurrencies(Model theModel, @RequestParam(required=false) int sortType) {
        theModel.addAttribute("currenciesData", dataService.getSortedCurrencies(sortType));
        return "adminPanel/currenciesData";
    }

    @GetMapping("/showTableToUpdate")
    public String showTableToUpdate(Model theModel, @RequestParam(required=false) String sort) {
        theModel.addAttribute("usersData", getSortedList(sort));
        return "adminPanel/updateDataTable";
    }

    @GetMapping("/showTableToRemove")
    public String showTableToRemove(Model theModel, @RequestParam(required=false) String sort) {
        theModel.addAttribute("usersData", getSortedList(sort));
        return "adminPanel/removeUserTable";
    }


    private List<User> getSortedList(String sort) {
        List<User> theCustomers;
        if (sort != null) {
            int theSortField = Integer.parseInt(sort);
            theCustomers = dataService.getUsers(theSortField);
        } else {
            theCustomers = dataService.getUsers(SortUtilsUsers.id_sort);
        }
        return theCustomers;
    }
}



