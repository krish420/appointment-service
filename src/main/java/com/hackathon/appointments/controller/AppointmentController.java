package com.hackathon.appointments.controller;

import com.hackathon.appointments.entity.Appointment;
import com.hackathon.appointments.model.Doctor;
import com.hackathon.appointments.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;


    @GetMapping("/view/{token}")
    public ResponseEntity<Map<List<String>, Map<LocalDate, Map<LocalTime,String>>>> viewSlots(@PathVariable String token) {
        LocalDate fromDate = LocalDate.now();
        LocalDate toDate = fromDate.plusDays(1);
        Map<List<String>, Map<LocalDate, Map<LocalTime,String>>> slots = appointmentService.availableSlots(fromDate, toDate,token);
        return ResponseEntity.ok(slots);
    }


    @GetMapping("/view/mode/{mode}/{token}")
    public ResponseEntity<Map<List<String>, Map<LocalDate, Map<LocalTime,String>>>> viewSlotsWithMode(@PathVariable String token,@PathVariable String mode) {
        LocalDate fromDate = LocalDate.now();
        LocalDate toDate = fromDate.plusDays(1);
        switch (mode) {
            case "w":
                toDate = fromDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));
                break;
            case "m":
                toDate = fromDate.with(TemporalAdjusters.lastDayOfMonth());
                break;
        }
        Map<List<String>, Map<LocalDate, Map<LocalTime,String>>> slots = appointmentService.availableSlots(fromDate, toDate, token);
        return ResponseEntity.ok(slots);
    }

    @PostMapping("/schedule")
    public ResponseEntity<Appointment> bookAppointment(@RequestBody Appointment appointment) {
        String generateAppointmentId = UUID.randomUUID().toString().substring(0, 7);
        appointment.setAppointmentId(generateAppointmentId);
        Appointment bookedAppointment = appointmentService.book(appointment);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookedAppointment);
    }

    @GetMapping("/history/{patientId}")
    public ResponseEntity<List<Appointment>> bookingHistoryOfAPatient(@PathVariable String patientId) {
        List<Appointment> history = appointmentService.fetchForAParticularPatient(patientId);
        return ResponseEntity.ok(history);
    }
}
