package com.bridgelabz.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
/********************************************************************************************************
 * Purpose: This is a pojo class which is used to for generating objects.
 *
 * @author Ashwith
 * @since 11/12/21
 *******************************************************************************************************/
@Data
public class AddressBookDto {

    @Pattern(regexp = "^[A-Z]{1}[a-z]{3,10}$", message = "Improper Name!!!")
    private String name;
    @Size(max = 150, message = " Address can have only up-to 150 characters ")
    private String address;
    @Size(max = 20, message = "City name can have only up-to 20 characters ")
    private String city;
    @Size(max = 20, message = "State name can have only up-to 20 characters ")
    private String state;
    @NotNull
    @Pattern(regexp = "^[1-9]{2}\\s[1-9][0-9]{9}$", message = "Invalid Phone Number. Eg: 91 9895612345")
    private String phoneNumber;
    @NotNull
    @Pattern(regexp = "^[0-9]{6}$", message = "ZIP Code should have 6 digits.")
    private String zip;
}
