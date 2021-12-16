package com.bridgelabz.integration;

import com.bridgelabz.controller.AddressBookController;
import com.bridgelabz.dto.AddressBookDto;
import com.bridgelabz.service.AddressBookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(AddressBookController.class)
public class AddressBookIT {

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AddressBookService addressBookService;

    @Test
    void getAllEmployeeTest() throws Exception {
        when(addressBookService.getAllAddresses()).thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/getAllAddresses"))
                .andExpect(status().isOk());
    }

    @Test
    void addEmployeeTest() throws Exception {
        AddressBookDto addressBookDto = new AddressBookDto();
        addressBookDto.setName("Ashwith");
        addressBookDto.setCity("Hyderabad");
        addressBookDto.setState("Telangana");
        addressBookDto.setPhoneNumber("91 9391599889");
        addressBookDto.setZip("500044");
        addressBookDto.setAddress("Uppal");
        String jsonRequest = objectMapper.writeValueAsString(addressBookDto);
        when(addressBookService.addAddress(any())).thenReturn("Address Added Successfully");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/addAddress")
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("Address Added Successfully"));
    }

    @Test
    void editEmployeeTest() throws Exception {
        int id = 1;
        AddressBookDto addressBookDto = new AddressBookDto();
        addressBookDto.setName("Ashwith");
        addressBookDto.setCity("Hyderabad");
        addressBookDto.setState("Telangana");
        addressBookDto.setPhoneNumber("91 9391599889");
        addressBookDto.setZip("500044");
        addressBookDto.setAddress("Uppal");

        String jsonRequest = objectMapper.writeValueAsString(addressBookDto);
        when(addressBookService.editAddress(id, addressBookDto)).thenReturn("Address Edited Successfully");
        mockMvc.perform(MockMvcRequestBuilders.put("/api/editAddress?id=1")
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("Address Edited Successfully"));
    }

    @Test
    void deleteEmployeeTest() throws Exception {
        int id = 1;
        when(addressBookService.deleteAddress(id)).thenReturn("Address Deleted Successfully");
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/removeAddress?id=1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("Address Deleted Successfully"));
    }

}
