package com.hackathon.appointments.repository;

import com.hackathon.appointments.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {
    List<Appointment> findByAppointmentDate(LocalDate appointmentDate);
    List<Appointment> findByPatientId(String patientId);
    boolean existsByAppointmentDateAndAppointmentTime(LocalDate bookingDate, LocalTime bookingTime);
}
