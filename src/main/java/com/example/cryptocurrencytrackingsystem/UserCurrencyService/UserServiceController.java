package com.example.cryptocurrencytrackingsystem.UserCurrencyService;


import com.example.cryptocurrencytrackingsystem.Database.UserServiceInterface;
import com.example.cryptocurrencytrackingsystem.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserServiceController {

	private UserServiceInterface userService;

	@Autowired
	public void setCustomerDAO(@Qualifier("userService") UserServiceInterface userService) {
		this.userService = userService;
	}


	// add an initbinder ... to convert trim input strings
	// remove leading and trailing whitespace
	// resolve issue for our validation

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}


	@GetMapping("/registerNewUser")
	public String showForm(Model theModel) {
		theModel.addAttribute("newUser", new User());
		return "userCurrencyService/userRegistration";
	}

	@GetMapping("/saveUserInDatabase")
	public String processForm(@Valid @ModelAttribute("newUser") User theUser,
							  BindingResult theBindingResult) {

		// ma errory
		if(theBindingResult.hasErrors()){
			return "userCurrencyService/userRegistration";

			// nie mozna zarejestrowac bo istnieje juz taki uzttkownik
		} else if (userService.getUser(theUser.getLogin()) != null){
			theBindingResult.rejectValue("login", "error.login", "User with this login exist!");
			return "userCurrencyService/userRegistration";

		} else {
			// zarejestrowano
			userService.saveUser(theUser);
			theBindingResult.rejectValue("login", "error.login", "Account was created!");
			return "userCurrencyService/userRegistration";
		}
	}


	@GetMapping("/loginIntoSystem")
	public String loginIntoAccount(Model theModel){
		theModel.addAttribute("existedUser", new User());
		return "userCurrencyService/userLoginSystem";
	}

	@GetMapping("/loginProcess")
	public String loginProcess(@Valid @ModelAttribute("existedUser") User theUser,
							   BindingResult theBindingResult) {

		// ma errory
		if(theBindingResult.hasErrors()){
			return "userCurrencyService/userLoginSystem";
		}

		// zalogowano
		if (checkUserData(theUser)) {
			return "userCurrencyService/userPanel";

			// podano zle dane
		} else {
			theBindingResult.rejectValue("login", "error.login", "Wrong data, try again!");
			return "userCurrencyService/userLoginSystem";
		}
	}


	private boolean checkUserData(User theUser) {
		User tempUser = userService.getUser(theUser.getLogin());
		return tempUser != null &&
				tempUser.getLogin().equals(theUser.getLogin()) &&
				tempUser.getPassword().equals(theUser.getPassword()) &&
				tempUser.getEmail().equals(theUser.getEmail());
	}

}