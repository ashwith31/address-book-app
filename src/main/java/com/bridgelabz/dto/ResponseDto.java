package com.bridgelabz.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;
/********************************************************************************************************
 * Purpose: This is a pojo class which is used to for generating objects.
 *
 * @author Ashwith
 * @since 11/12/21
 *******************************************************************************************************/
@Data
public class ResponseDto {
    HttpStatus httpStatus;
    String message;

    public ResponseDto(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
