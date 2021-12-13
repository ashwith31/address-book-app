package com.bridgelabz.builder;

import com.bridgelabz.dto.AddressBookDto;
import com.bridgelabz.model.AddressBook;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AddressBookBuilder {
    private ModelMapper modelMapper = new ModelMapper();

    public AddressBook buildAtmEntity(AddressBookDto addressBookDto, AddressBook addressBook) {
        modelMapper.map(addressBookDto, addressBook);
        return addressBook;
    }
}
