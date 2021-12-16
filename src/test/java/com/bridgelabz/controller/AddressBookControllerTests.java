package com.bridgelabz.controller;

import com.bridgelabz.dto.AddressBookDto;
import com.bridgelabz.dto.AddressBookResponseDto;
import com.bridgelabz.exception.NoDataFoundException;
import com.bridgelabz.service.AddressBookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddressBookControllerTests {

    AddressBookDto addressBookDto = new AddressBookDto();

    @InjectMocks
    private AddressBookController addressBookController;

    @Mock
    private AddressBookService addressBookService;

    @BeforeEach
    void setUp(){
        addressBookDto.setName("Ashwith");
        addressBookDto.setCity("Hyderabad");
        addressBookDto.setState("Telangana");
    }

    @Test
    void givenGetAllEmployeeMethodIsCalled_ShouldReturnTheListOfAllEmployeeResponseDto() {
        List<AddressBookResponseDto> addressBookResponseDtos = new ArrayList<>();
        AddressBookResponseDto addressBookResponseDto = new AddressBookResponseDto();
        addressBookResponseDto.setName("Ashwith");
        addressBookResponseDto.setCity("Hyderabad");
        addressBookResponseDto.setState("Telangana");
        addressBookResponseDtos.add(addressBookResponseDto);
        AddressBookResponseDto addressBookResponseDto2 = new AddressBookResponseDto();
        addressBookResponseDto2.setName("Rohith");
        addressBookResponseDto2.setCity("Hyderabad");
        addressBookResponseDto2.setState("Telangana");
        addressBookResponseDtos.add(addressBookResponseDto2);
        when(addressBookService.getAllAddresses()).thenReturn(addressBookResponseDtos);
        ResponseEntity<List<AddressBookResponseDto>> actualResponse = addressBookController.getAllAddresses();
        for (int i = 0; i < actualResponse.getBody().size(); i++) {
            assertEquals(addressBookResponseDtos.get(i).getName(), actualResponse.getBody().get(i).getName());
            assertEquals(addressBookResponseDtos.get(i).getCity(), actualResponse.getBody().get(i).getCity());
            assertEquals(addressBookResponseDtos.get(i).getState(), actualResponse.getBody().get(i).getState());
        }
    }

    @Test
    void givenAddEmployeeMethodIsCalled_ShouldAddEmployeeAndGenerateSuccessMessage() {
        String successString = "Address Added Successfully";
        ResponseEntity<String> expectedResponseEntity = new ResponseEntity<>(successString, HttpStatus.OK);
        when(addressBookService.addAddress(addressBookDto)).thenReturn(successString);
        ResponseEntity<String> actualResponseString = addressBookController.addAddress(addressBookDto);
        assertEquals(expectedResponseEntity, actualResponseString);
    }

    @Test
    void givenEditEmployeeMethodIsCalled_ShouldUpdateEmployeeAndGenerateSuccessMessage() {
        String successString = "Address edited successfully";
        ResponseEntity<String> expectedResponseEntity = new ResponseEntity<>(successString, HttpStatus.OK);
        int id = 1;
        when(addressBookService.editAddress(id, addressBookDto)).thenReturn(successString);
        ResponseEntity<String> actualResponseString = addressBookController.editAddress(id, addressBookDto);
        assertEquals(expectedResponseEntity, actualResponseString);
    }

    @Test
    void givenEditEmployeeMethodIsCalled_WhenIdNotFound_ShouldThrowException() {
        int id = 1;
        when(addressBookService.editAddress(id, addressBookDto)).thenThrow(NoDataFoundException.class);
        Assertions.assertThrows(NoDataFoundException.class, () -> addressBookController.editAddress(id, addressBookDto));
    }

    @Test
    void givenDeleteEmployeeMethodIsCalled_ShouldDeleteEmployeeAndGenerateSuccessMessage() {
        String successString = "Address delete successful";
        ResponseEntity<String> expectedResponseEntity = new ResponseEntity<>(successString, HttpStatus.OK);
        int id = 1;
        when(addressBookService.deleteAddress(id)).thenReturn(successString);
        ResponseEntity<String> actualResponseString = addressBookController.deleteAddress(id);
        assertEquals(expectedResponseEntity, actualResponseString);
    }

    @Test
    void givenDeleteEmployeeMethodIsCalled_WhenIdNotFound_ShouldThrowException() {
        int id = 1;
        when(addressBookService.deleteAddress(id)).thenThrow(NoDataFoundException.class);
        Assertions.assertThrows(NoDataFoundException.class, () -> addressBookController.deleteAddress(id));
    }
}
