package com.hackathon.appointments.payload;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
@Builder
public class ErrorResponse {
    private String message;
    private String status;
    private HttpStatus httpStatus;
}
