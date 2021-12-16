package com.bridgelabz.dto;

import lombok.Data;
/********************************************************************************************************
 * Purpose: This is a pojo class which is used to for generating objects.
 *
 * @author Ashwith
 * @since 11/12/21
 *******************************************************************************************************/
@Data
public class AddressBookResponseDto {

    private int id;
    private String name;
    private String address;
    private String city;
    private String state;
    private String phoneNumber;
    private String zip;
}
