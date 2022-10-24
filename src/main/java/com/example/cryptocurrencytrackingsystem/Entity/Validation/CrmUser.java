package com.example.cryptocurrencytrackingsystem.Entity.Validation;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@FieldMatch.List(
{ @FieldMatch( first = "password", second = "matchingPassword", message = "The password fields must match" ) } )
@Getter
@Setter
public class CrmUser
{

    private final String fillEmptyFieldMessage = "Fill empty field!";
    private final String wrongEmailFormatField = "Wrong email format!";

    @NotNull( message = fillEmptyFieldMessage )
    @Size( min = 1, message = fillEmptyFieldMessage )
    private String userName;

    @NotNull( message = fillEmptyFieldMessage )
    @Size( min = 1, message = fillEmptyFieldMessage )
    private String password;

    @NotNull( message = fillEmptyFieldMessage )
    @Size( min = 1, message = fillEmptyFieldMessage )
    private String matchingPassword;

    @NotNull( message = fillEmptyFieldMessage )
    @Size( min = 1, message = fillEmptyFieldMessage )
    private String firstName;

    @NotNull( message = fillEmptyFieldMessage )
    @Size( min = 1, message = fillEmptyFieldMessage )
    private String lastName;

    @Email( message = wrongEmailFormatField )
    @NotNull( message = fillEmptyFieldMessage )
    @Size( min = 1, message = fillEmptyFieldMessage )
    private String email;
}
