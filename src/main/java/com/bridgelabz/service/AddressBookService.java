package com.bridgelabz.service;

import com.bridgelabz.builder.AddressBookBuilder;
import com.bridgelabz.dto.AddressBookDto;
import com.bridgelabz.dto.AddressBookResponseDto;
import com.bridgelabz.exception.NoDataFoundException;
import com.bridgelabz.model.AddressBook;
import com.bridgelabz.repository.AddressBookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressBookService {

    private static final String ADDRESS_ADDED_SUCCESSFULLY = "Address Added Successfully";
    private static final String ADDRESS_EDITED_SUCCESSFULLY = "Address edited successfully";
    private static final String ADDRESS_DELETED_SUCCESSFULLY = "Address delete successful";
    private static final String INVALID_ID = "Invalid id";

    @Autowired
    private AddressBookRepository addressBookRepository;

    @Autowired
    private AddressBookBuilder addressBookBuilder;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * This method is to get all the data that is present in the database.
     *
     * @return List of all the data in the database.
     */
    public List<AddressBookResponseDto> getAllAddresses() {
        return addressBookRepository.findAll()
                .stream()
                .map(addressBook -> modelMapper.map(addressBook, AddressBookResponseDto.class))
                .collect(Collectors.toList());
    }

    /**
     * This method is to check if there is data in the database for a specific id.
     *
     * @param id to be checked if the data exists or not.
     * @return boolean true if data exists else false.
     */
    private AddressBook findEmployeeById(int id) {
        return addressBookRepository.findById(id).orElseThrow(() -> new NoDataFoundException(INVALID_ID));
    }

    /**
     * This method is to add the data that is being passed into the database.
     *
     * @param addressBookDto the data that is to saved in the database.
     * @return string to say that if the data is saved successfully or not.
     */
    public String addAddress(AddressBookDto addressBookDto) {
        AddressBook addressBook = modelMapper.map(addressBookDto, AddressBook.class);
        addressBookRepository.save(addressBook);
        return ADDRESS_ADDED_SUCCESSFULLY;
    }

    /**
     * This method is to update the data in the database based on id of that database.
     *
     * @param id of the data to be updated.
     * @param addressBookDto the data to be updated
     * @return string to say that if the data is updated successfully or not.
     * @throws NoDataFoundException if there is a invalid id passed as argument.
     */
    public String editAddress(int id,  AddressBookDto addressBookDto) throws NoDataFoundException{
        AddressBook addressBook = findEmployeeById(id);
        addressBook = addressBookBuilder.buildAtmEntity(addressBookDto, addressBook);
        addressBookRepository.save(addressBook);
        return ADDRESS_EDITED_SUCCESSFULLY;
    }

    /**
     * This method is to delete the data in the database based on id of that database.
     *
     * @param id of the data to be deleted.
     * @return string to say that if the data is deleted successfully or not.
     * @throws NoDataFoundException if there is a invalid id passed as argument.
     */
    public String deleteAddress(int id) throws NoDataFoundException {
        AddressBook addressBook = findEmployeeById(id);
        addressBookRepository.delete(addressBook);
        return ADDRESS_DELETED_SUCCESSFULLY;
    }
}
