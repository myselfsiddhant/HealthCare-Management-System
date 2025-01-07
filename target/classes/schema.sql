-- Admins Table
CREATE TABLE IF NOT EXISTS admins (
    admin_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,  -- Use longer for hashed passwords
    email VARCHAR(100),
    full_name VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_login TIMESTAMP NULL
);

-- Patients Table
CREATE TABLE IF NOT EXISTS patients (
    patient_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,  -- Use longer for hashed passwords
    email VARCHAR(100) NOT NULL,
    full_name VARCHAR(100),
    date_of_birth DATE,
    gender ENUM('Male', 'Female', 'Other'),
    contact_number VARCHAR(15),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_login TIMESTAMP NULL
);

-- Reports Table
CREATE TABLE IF NOT EXISTS medical_reports (
    record_id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id VARCHAR(50) NOT NULL,
    visit_date DATE NOT NULL,
    diagnosis TEXT,
    prescribed_medication TEXT,
    notes TEXT,
    doctor_name VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (patient_id) REFERENCES patients(username)
);

-- Appointments Table
CREATE TABLE IF NOT EXISTS appointments (
    appointment_id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id VARCHAR(50) NOT NULL,
    appointment_date DATE NOT NULL,
    appointment_time TIME,
    status ENUM('available', 'booked', 'completed', 'cancelled') DEFAULT 'available',
    doctor_name VARCHAR(100),
    department VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (patient_id) REFERENCES patients(username)
);

-- Indexes for performance
CREATE INDEX idx_patient_reports ON medical_reports(patient_id);
CREATE INDEX idx_patient_appointments ON appointments(patient_id);


-- Admin Insertions (Use secure password hashing in actual implementation)
INSERT INTO admins (username, password, email, full_name) VALUES 
('admin1', 'hashed_password_here', 'admin1@hospital.com', 'John Admin'),
('admin2', 'hashed_password_here', 'admin2@hospital.com', 'Jane Admin');

-- Patient Insertions
INSERT INTO patients (
    username, 
    password, 
    email, 
    full_name, 
    date_of_birth, 
    gender, 
    contact_number
) VALUES 
('patient1', 'hashed_password_here', 'patient1@example.com', 'Alice Johnson', '1990-05-15', 'Female', '+1234567890'),
('patient2', 'hashed_password_here', 'patient2@example.com', 'Bob Smith', '1985-08-22', 'Male', '+0987654321');

-- Medical Reports
INSERT INTO medical_reports (
    patient_id, 
    visit_date, 
    diagnosis, 
    prescribed_medication, 
    notes, 
    doctor_name
) VALUES 
('patient1', '2024-10-01', 'Hypertension', 'Lisinopril 10mg', 'Regular blood pressure monitoring recommended', 'Dr. Emily Chen'),
('patient1', '2024-10-05', 'Diabetes Type 2', 'Metformin 500mg', 'Dietary consultation suggested', 'Dr. Michael Rodriguez'),
('patient1', '2024-10-10', 'Seasonal Flu', 'Oseltamivir 75mg', 'Rest and hydration advised', 'Dr. Sarah Thompson');

-- Appointments
INSERT INTO appointments (
    patient_id, 
    appointment_date, 
    appointment_time,
    status, 
    doctor_name,
    department
) VALUES 
('patient1', '2024-11-01', '09:00:00', 'available', 'Dr. Emily Chen', 'Cardiology'),
('patient1', '2024-11-03', '14:30:00', 'booked', 'Dr. Michael Rodriguez', 'Endocrinology'),
('patient1', '2024-11-08', '11:15:00', 'available', 'Dr. Sarah Thompson', 'General Medicine');
