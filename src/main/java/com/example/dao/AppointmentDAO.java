package com.example.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppointmentDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<String> fetchAvailableAppointmentDates(String patientId) {
        String query = "SELECT appointment_date FROM appointments WHERE patientID = ? AND status = 'available'";
        return jdbcTemplate.queryForList(query, new Object[]{patientId}, String.class);
    }

    public int bookAppointment(Appointment appointment) {
        String queryUpdate = "UPDATE appointments SET status = 'booked' WHERE patientID = ? AND appointment_date = ?";
        return jdbcTemplate.update(queryUpdate, appointment.getPatientId(), appointment.getDate());
    }

    public List<Appointment> fetchUpcomingAppointments(String patientId) {
        String query = "SELECT * FROM appointments WHERE patientID = ? AND status = 'booked' AND appointment_date >= CURDATE() ORDER BY appointment_date";
        return jdbcTemplate.query(query, new Object[]{patientId}, (rs, rowNum) -> {
            Appointment appointment = new Appointment();
            appointment.setPatientId(rs.getString("patientID"));
            appointment.setDate(rs.getString("appointment_date"));
            return appointment;
        });
    }

    public int cancelAppointment(Appointment appointment) {
        String queryUpdate = "UPDATE appointments SET status = 'available' WHERE patientID = ? AND appointment_date = ?";
        return jdbcTemplate.update(queryUpdate, appointment.getPatientId(), appointment.getDate());
    }
}
