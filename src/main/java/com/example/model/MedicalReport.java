package com.example.model;

public class MedicalReport {
    private int recordId;
    private String diagnosis;
    private String prescribedMedication;
    private String notes;

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getPrescribedMedication() {
        return prescribedMedication;
    }

    public void setPrescribedMedication(String prescribedMedication) {
        this.prescribedMedication = prescribedMedication;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
