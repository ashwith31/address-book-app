package com.bridgelabz.controller;

import com.bridgelabz.dto.AddressBookDto;
import com.bridgelabz.dto.AddressBookResponseDto;
import com.bridgelabz.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
/**********************************************************************************************************************
 * Purpose: This class is to make different HTTP request method calls using Rest controller.
 *
 * @author Ashwith
 * @since 11/12/21
 *********************************************************************************************************************/
@RestController
@RequestMapping("/api")
public class AddressBookController {
    @Autowired
    private AddressBookService addressBookService;

    /**
     * This method is to get all the data that is present in the database.
     *
     * @return List of all the data in the database.
     */
    @GetMapping(value = "/getAllAddresses")
    public ResponseEntity<List<AddressBookResponseDto>> getAllAddresses() {
        return new ResponseEntity<>(addressBookService.getAllAddresses(), HttpStatus.OK);
    }

    /**
     * This method is to add the data that is being passed into the database.
     *
     * @param addressBookDto the data that is to saved in the database.
     * @return string to say that if the data is saved successfully or not.
     */
    @PostMapping(value = "/addAddress")
    public ResponseEntity<String> addAddress(@Valid @RequestBody AddressBookDto addressBookDto) {
        return new ResponseEntity<String>(addressBookService.addAddress(addressBookDto), HttpStatus.OK);
    }

    /**
     * This method is to update the data in the database based on id of that database.
     *
     * @param id     of the data to be updated.
     * @param addressBookDto the data to be updated
     * @return string to say that if the data is updated successfully or not.
     */
    @PutMapping("/editAddress")
    public ResponseEntity<String> editAddress(@Valid @RequestParam int id,
                                               @RequestBody AddressBookDto addressBookDto) {
        return new ResponseEntity<>(addressBookService.editAddress(id, addressBookDto), HttpStatus.OK);
    }

    /**
     * This method is to delete the data in the database based on id of that database.
     *
     * @param id of the data to be deleted.
     * @return string to say that if the data is deleted successfully or not.
     */
    @DeleteMapping("/removeAddress")
    public ResponseEntity<String> deleteAddress(@Valid @RequestParam int id) {
        return new ResponseEntity<>(addressBookService.deleteAddress(id), HttpStatus.OK);
    }
}
