package com.example.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReportDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<String> fetchReportDates(String patientId) {
        String query = "SELECT DISTINCT VisitDate FROM Reports WHERE PatientID = ?";
        return jdbcTemplate.queryForList(query, new Object[]{patientId}, String.class);
    }

    public List<MedicalReport> fetchMedicalReports(String patientId, String visitDate) {
        String query = "SELECT * FROM Reports WHERE PatientID = ? AND VisitDate = ?";
        return jdbcTemplate.query(query, new Object[]{patientId, visitDate}, (rs, rowNum) -> {
            MedicalReport report = new MedicalReport();
            report.setRecordId(rs.getInt("RecordID"));
            report.setDiagnosis(rs.getString("Diagnosis"));
            report.setPrescribedMedication(rs.getString("PrescribedMedication"));
            report.setNotes(rs.getString("Notes"));
            return report;
        });
    }
}
