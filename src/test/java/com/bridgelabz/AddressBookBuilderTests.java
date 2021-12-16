package com.bridgelabz;

import com.bridgelabz.builder.AddressBookBuilder;
import com.bridgelabz.dto.AddressBookDto;
import com.bridgelabz.model.AddressBook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AddressBookBuilderTests {

    @InjectMocks
    private AddressBookBuilder addressBookBuilder;

    @Test
    public void givenBuildEmployeeEntityMethodIsCalled_ShouldReturnEmployee() {
        AddressBookDto addressBookDto = new AddressBookDto();
        addressBookDto.setName("Ashwith");
        addressBookDto.setCity("Hyderabad");
        addressBookDto.setState("Telangana");

        AddressBook addressBook = new AddressBook();

        AddressBook actualAddress = addressBookBuilder.buildAddressBookEntity(addressBookDto, addressBook);

        AddressBook expectedAddress = new AddressBook();
        expectedAddress.setName("Ashwith");
        expectedAddress.setCity("Hyderabad");
        expectedAddress.setState("Telangana");
        Assertions.assertEquals(expectedAddress, actualAddress);
    }
}
