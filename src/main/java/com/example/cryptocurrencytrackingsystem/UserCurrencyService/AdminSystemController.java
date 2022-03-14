package com.example.cryptocurrencytrackingsystem.UserCurrencyService;

        import com.example.cryptocurrencytrackingsystem.Database.UserServiceInterface;
        import com.example.cryptocurrencytrackingsystem.Entity.Currency;
        import com.example.cryptocurrencytrackingsystem.Entity.User;
        import com.fasterxml.jackson.core.type.TypeReference;
        import com.fasterxml.jackson.databind.ObjectMapper;
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
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.util.List;

@Controller
@RequestMapping("/adminSystem")
public class AdminSystemController {

    private UserServiceInterface userService;

    @Autowired
    public void setCustomerDAO(@Qualifier("userService") UserServiceInterface userService) {
        this.userService = userService;
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
        } else if (checkAdminData(admin)) {
            return "adminPanel/adminPanel";
        } else {
            theBindingResult.rejectValue("login", "error.login", "Wrong data, try again!");
            return "loginSystemPages/adminLoginForm";
        }
    }

    private boolean checkAdminData(User data) {
        User adminAccount = userService.getAdminAccount();
        return  adminAccount.getLogin().equals(data.getLogin()) &&
                adminAccount.getPassword().equals(data.getPassword()) &&
                adminAccount.getEmail().equals(data.getEmail());
    }


    @GetMapping("/deleteAnAccount")
    public String deleteAnAccount(Model theModel,
                                  @RequestParam("userId") Integer userId) {

        userService.deleteAnAccount(userId);
        theModel.addAttribute("usersData", userService.getUsers(SortUtils.id_sort));
        return "adminPanel/adminPanel";
    }

    @GetMapping("/updateAnAccount")
    public String updateAnAccount(Model theModel,
                                  @RequestParam("userId") Integer userId) {

        theModel.addAttribute("userToUpdate", userService.getUser(userId));
        return "adminPanel/updateUserForm";
    }

    @GetMapping("/processAnUpdate")
    public String processAnUpdate(Model theModel,
                                  @Valid @ModelAttribute("userToUpdate") User updatedUser,
                                  BindingResult theBindingResult) {

        if(!theBindingResult.hasErrors()) {
            userService.updateUser(updatedUser);
            theBindingResult.rejectValue("login", "error.login", "Account updated!");
            theModel.addAttribute("usersData", userService.getUsers(SortUtils.id_sort));
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

    ////////////////////////////////////////////////TODO
    @GetMapping("/showCurrencies")
    public String showCurrencies(Model theModel) {//TODO TU ZROBIC NOWA KLASE
        theModel.addAttribute("usersData", userService.getUsers(SortUtils.id_sort));
        return "adminPanel/currenciesData";
    }

//    private List<Currency> getCurrencies() {
//        try {
//            URL ulr = new URL("https://api.pancakeswap.info/api/v2/tokens");
//            ObjectMapper objectMapper = new ObjectMapper();
//            return objectMapper.readValue(ulr, new TypeReference<>(){});
//
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }
    ///////////// TODO

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
        List<User> theCustomers = null;
        if (sort != null) {
            int theSortField = Integer.parseInt(sort);
            theCustomers = userService.getUsers(theSortField);
        } else {
            theCustomers = userService.getUsers(SortUtils.id_sort);
        }
        return theCustomers;
    }
}



