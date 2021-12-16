package com.bridgelabz.builder;

import com.bridgelabz.dto.AddressBookDto;
import com.bridgelabz.model.AddressBook;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
/********************************************************************************************************
 * Purpose: This class is for building the methods so that we can avoid DRY principal.
 *
 * @author Ashwith
 * @since 11/12/21
 *******************************************************************************************************/
@Component
public class AddressBookBuilder {
    private ModelMapper modelMapper = new ModelMapper();

    public AddressBook buildAddressBookEntity(AddressBookDto addressBookDto, AddressBook addressBook) {
        modelMapper.map(addressBookDto, addressBook);
        return addressBook;
    }
}
