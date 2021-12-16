package com.bridgelabz.model;

import lombok.Data;

import javax.persistence.*;
/********************************************************************************************************
 * Purpose: This is the entity class where all the messages are stored in the database.
 *
 * @author Ashwith
 * @since 2/12/21
 *******************************************************************************************************/
@Entity
@Data
public class AddressBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id")
    private int id;
    @Column(name = "name1")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "zip")
    private String zip;
}
