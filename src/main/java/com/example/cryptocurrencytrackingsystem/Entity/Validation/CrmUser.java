package com.example.cryptocurrencytrackingsystem.Entity.Validation;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@FieldMatch.List({
    @FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
})

public class CrmUser {

	@NotNull(message = "Fill empty field!")
	@Size(min = 1, message = "Fill empty field!")
	private String userName;

	@NotNull(message = "Fill empty field!")
	@Size(min = 1, message = "Fill empty field!")
	private String password;

	@NotNull(message = "Fill empty field!")
	@Size(min = 1, message = "Fill empty field!")
	private String matchingPassword;

	@NotNull(message = "Fill empty field!")
	@Size(min = 1, message = "Fill empty field!")
	private String firstName;

	@NotNull(message = "Fill empty field!")
	@Size(min = 1, message = "Fill empty field!")
	private String lastName;

	@Email(message = "Wrong email format!")
	@NotNull(message = "Fill empty field!")
	@Size(min = 1, message = "Fill empty field!")
	private String email;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
