CREATE TABLE IF NOT EXISTS admins (
    admin_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS patients (
    patient_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS Reports (
    RecordID INT PRIMARY KEY AUTO_INCREMENT,
    PatientID VARCHAR(255) NOT NULL,
    VisitDate DATE NOT NULL,
    Diagnosis TEXT,
    PrescribedMedication TEXT,
    Notes TEXT
);

CREATE TABLE IF NOT EXISTS appointments (
    appointment_id INT PRIMARY KEY AUTO_INCREMENT,
    patientID VARCHAR(255) NOT NULL,
    appointment_date DATE NOT NULL,
    status ENUM('available', 'booked') DEFAULT 'available',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO admins (username, password) VALUES ('admin1', 'admin123');
INSERT INTO admins (username, password) VALUES ('admin2', 'admin456');

INSERT INTO patients (username, password) 
VALUES ('patient1', 'patient123');
INSERT INTO patients (username, password) 
VALUES ('patient2', 'patient123');

INSERT INTO Reports (PatientID, VisitDate, Diagnosis, PrescribedMedication, Notes)
VALUES 
    ('patient1', '2024-10-01', 'Hypertension', 'Lisinopril 10mg', 'Patient advised to monitor blood pressure regularly.'),
    ('patient1', '2024-10-05', 'Diabetes Type 2', 'Metformin 500mg', 'Diet and exercise recommendations provided.'),
    ('patient1', '2024-10-10', 'Flu', 'Oseltamivir 75mg', 'Patient advised to rest and stay hydrated.'),
    ('patient1', '2024-10-15', 'Asthma', 'Albuterol Inhaler as needed', 'Follow up in one month.'),
    ('patient1', '2024-10-20', 'Routine Checkup', NULL, 'All vitals normal; continue current medication.');

INSERT INTO appointments (patientID, appointment_date, status) VALUES
('patient1', '2024-11-01', 'available'),
('patient1', '2024-11-03', 'booked'),
('patient1', '2024-11-08', 'available'),
('patient1', '2024-11-11', 'available'),
('patient1', '2024-11-20', 'booked');
