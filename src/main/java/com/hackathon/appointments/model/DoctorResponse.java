package com.hackathon.appointments.model;

import lombok.*;

import java.util.List;

@Data 
@Getter 
@Setter 
@AllArgsConstructor 
@NoArgsConstructor 
@Builder
public class DoctorResponse {
    private List<Doctor> doctors;
    
}
