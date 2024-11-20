package com.hackathon.appointments.entity;

import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
@Entity
public class Appointment {
    @Id
    String appointmentId;
    String bookedBy;
    String patientId;
    String patientEmail;
    String appointmentType; // VIRTUAL or PHYSICAL
    String doctorId;
    LocalDate appointmentDate;
    LocalTime appointmentTime;


}
