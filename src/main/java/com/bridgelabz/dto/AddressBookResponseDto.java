package com.bridgelabz.dto;

import lombok.Data;

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
