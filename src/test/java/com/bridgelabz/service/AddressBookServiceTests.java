package com.bridgelabz.service;

import com.bridgelabz.builder.AddressBookBuilder;
import com.bridgelabz.dto.AddressBookDto;
import com.bridgelabz.dto.AddressBookResponseDto;
import com.bridgelabz.exception.NoDataFoundException;
import com.bridgelabz.model.AddressBook;
import com.bridgelabz.repository.AddressBookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class AddressBookServiceTests {
    AddressBook addressBook = new AddressBook();
    AddressBookDto addressBookDto = new AddressBookDto();

    @Mock
    ModelMapper modelMapper;
    @InjectMocks
    private AddressBookService addressBookService;
    @Mock
    private AddressBookRepository addressBookRepository;
    @Mock
    private AddressBookBuilder addressBookBuilder;


    @BeforeEach
    void setUp(){
        addressBook.setId(1);
        addressBook.setName("Ashwith");
        addressBook.setCity("Hyderabad");
        addressBook.setState("Telangana");

        addressBookDto.setName("Ashwith");
        addressBookDto.setCity("Hyderabad");
        addressBookDto.setState("Telangana");
    }

    @Test
    void givenGetAllEmployeeMethodIsCalled_ShouldReturnListOfEmployeeResponseDto() {
        List<AddressBook> addressBooks = new ArrayList<>();
        addressBooks.add(addressBook);
        AddressBook addressBook2 = new AddressBook();
        addressBook2.setId(2);
        addressBook2.setName("Rohith");
        addressBook2.setCity("Hyderabad");
        addressBook2.setState("Telangana");
        addressBooks.add(addressBook2);

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

        when(addressBookRepository.findAll()).thenReturn(addressBooks);
        when(modelMapper.map(addressBooks.get(0), AddressBookResponseDto.class)).thenReturn(addressBookResponseDto);
        when(modelMapper.map(addressBooks.get(1), AddressBookResponseDto.class)).thenReturn(addressBookResponseDto2);
        List<AddressBookResponseDto> actualListOfAddress = addressBookService.getAllAddresses();
        assertEquals(2, actualListOfAddress.size());
        assertEquals(addressBookResponseDtos, actualListOfAddress);
    }

    @Test
    void givenAddEmployeeMethodIsCalled_ShouldAddEmployeeAndGenerateSuccessMessage() {
        when(modelMapper.map(addressBookDto, AddressBook.class)).thenReturn(addressBook);
        String actualStringMessage = addressBookService.addAddress(addressBookDto);
        assertEquals("Address Added Successfully", actualStringMessage);
        verify(addressBookRepository, times(1)).save(addressBook);
    }

    @Test
    void givenEditEmployeeMethodIsCalled_WhenIdIsNotPresent_ShouldThrowExceptionMessage() {
        int id = 1;
        when(addressBookRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NoDataFoundException.class, () -> addressBookService.editAddress(id, addressBookDto));
    }

    @Test
    void givenEditEmployeeMethodIsCalled_ShouldUpdateEmployeeDetailsAndReturnSuccessMessage() {
        ArgumentCaptor<AddressBook> addressBookArgumentCaptor = ArgumentCaptor.forClass(AddressBook.class);

        int id = 1;
        AddressBook addressBook2 = new AddressBook();

        when(addressBookRepository.findById(id)).thenReturn(Optional.of(addressBook2));
        addressBook2.setName(addressBookDto.getName());
        addressBook2.setCity(addressBookDto.getCity());
        addressBook2.setState(addressBookDto.getState());
        when(addressBookBuilder.buildAddressBookEntity(addressBookDto, addressBook2)).thenReturn(addressBook2);
        String actualSuccessMessage = addressBookService.editAddress(id, addressBookDto);
        verify(addressBookRepository, times(1)).save(addressBookArgumentCaptor.capture());
        assertEquals("Address edited successfully", actualSuccessMessage);
        assertEquals(addressBookDto.getName(), addressBookArgumentCaptor.getValue().getName());
        assertEquals(addressBookDto.getCity(), addressBookArgumentCaptor.getValue().getCity());
        assertEquals(addressBookDto.getState(), addressBookArgumentCaptor.getValue().getState());
    }

    @Test
    void givenDeleteEmployeeMethodIsCalled_IfIdNotFound_shouldThrowExceptionMessage() {
        int id = 1;
        when(addressBookRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NoDataFoundException.class, () -> addressBookService.deleteAddress(id));
    }

    @Test
    void givenDeleteEmployeeMethodIsCalledWithAnId_ShouldDeleteTheDataOfThatId() {
        int id = 1;
        when(addressBookRepository.findById(id)).thenReturn(Optional.of(addressBook));
        String actualMessage = addressBookService.deleteAddress(id);
        assertEquals("Address delete successful", actualMessage);
        verify(addressBookRepository, times(1)).delete(addressBook);
    }
}
