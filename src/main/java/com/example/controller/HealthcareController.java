package com.example.controller;

import com.example.dao.UserDAO;
import com.example.dao.ReportDAO;
import com.example.dao.AppointmentDAO;
import com.example.model.UserCredentials;
import com.example.model.MedicalReport;
import com.example.model.Appointment;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api") // Base URL for all endpoints
public class HealthcareController {

    @Autowired
    private UserDAO userDAO; // Inject UserDAO

    @Autowired
    private ReportDAO reportDAO; // Inject ReportDAO

    @Autowired
    private AppointmentDAO appointmentDAO; // Inject AppointmentDAO

    @PostMapping("/authenticate") // Endpoint for user authentication
    public ResponseEntity<String> authenticateUser(@RequestBody UserCredentials credentials) {
        String id = credentials.getId();
        String password = credentials.getPassword();
        if (userDAO.isValidUser(id, password)) { // Use DAO method
            return ResponseEntity.ok("Login successful!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
        }
    }

    @GetMapping("/report-dates/{patientId}") // Endpoint for fetching report dates
    public ResponseEntity<List<String>> fetchReportDates(@PathVariable String patientId) {
        List<String> dates = reportDAO.fetchReportDates(patientId); // Use DAO method
        return ResponseEntity.ok(dates);
    }

    @GetMapping("/reports/{patientId}/{visitDate}") // Endpoint for fetching reports by date
    public ResponseEntity<List<MedicalReport>> fetchMedicalReports(@PathVariable String patientId, @PathVariable String visitDate) {
        List<MedicalReport> reports = reportDAO.fetchMedicalReports(patientId, visitDate); // Use DAO method
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/available-appointment-dates/{patientId}") // Endpoint for fetching available appointment dates
    public ResponseEntity<List<String>> fetchAvailableAppointmentDates(@PathVariable String patientId) {
        List<String> availableDates = appointmentDAO.fetchAvailableAppointmentDates(patientId); // Use DAO method
        return ResponseEntity.ok(availableDates);
    }

    @PostMapping("/book-appointment") // Endpoint for booking appointments
    public ResponseEntity<String> bookAppointment(@RequestBody Appointment appointment) {
        int rowsAffected = appointmentDAO.bookAppointment(appointment); // Use DAO method
        if (rowsAffected > 0) {
            return ResponseEntity.ok("Appointment booked successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to book appointment.");
        }
    }

    @GetMapping("/upcoming-appointments/{patientId}") // Endpoint for fetching upcoming appointments
    public ResponseEntity<List<Appointment>> fetchUpcomingAppointments(@PathVariable String patientId) {
        List<Appointment> appointments = appointmentDAO.fetchUpcomingAppointments(patientId); // Use DAO method
        return ResponseEntity.ok(appointments);
    }

    @PostMapping("/cancel-appointment") // Endpoint for canceling an appointment
    public ResponseEntity<String> cancelAppointment(@RequestBody Appointment appointment) {
        int rowsAffected = appointmentDAO.cancelAppointment(appointment); // Use DAO method
        if (rowsAffected > 0) {
            return ResponseEntity.ok("Appointment canceled successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to cancel appointment.");
        }
    }
}
