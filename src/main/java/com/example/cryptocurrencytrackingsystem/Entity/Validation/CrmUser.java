package com.example.cryptocurrencytrackingsystem.Entity.Validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@FieldMatch.List({
    @FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
})
@Getter
@Setter
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
}
