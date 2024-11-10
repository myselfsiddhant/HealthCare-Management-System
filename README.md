# Healthcare Management System

## Overview
The **Healthcare Management System** is a Spring Boot application designed to facilitate user authentication, manage medical reports, and handle appointment scheduling for patients. This project aims to provide a simple yet effective way for healthcare providers to manage patient data and appointments through a RESTful API.

## Features
- **User Authentication**: Allows users (patients and admins) to log in securely.
- **Medical Reports Management**: Fetches report dates and details based on patient ID and visit date.
- **Appointment Scheduling**: Enables patients to view available appointment dates, book appointments, and cancel them if necessary.

## Technologies Used
- **Java**: The primary programming language for backend development.
- **Spring Boot**: Framework for building the RESTful API.
- **Spring JDBC**: For database interactions.
- **Maven**: Build automation tool for managing project dependencies.
- **H2 Database/MySQL**: In-memory or persistent database for storing user and appointment data.

## Project Structure
```
src
└── main
    └── java
        └── com
            └── example
                ├── dao
                │   ├── UserDAO.java
                │   ├── ReportDAO.java
                │   └── AppointmentDAO.java
                ├── controller
                │   └── HealthcareController.java
                └── model
                    ├── UserCredentials.java
                    ├── MedicalReport.java
                    └── Appointment.java
    └── resources
        └── schema.sql
```

### Description of Key Components

1. **Models**:
   - `UserCredentials`: Holds the user's ID and password for authentication.
   - `MedicalReport`: Represents a medical report with fields such as diagnosis and prescribed medication.
   - `Appointment`: Contains information about the patient's ID and appointment date.

2. **DAOs (Data Access Objects)**:
   - `UserDAO`: Handles operations related to user authentication.
   - `ReportDAO`: Manages fetching medical report data from the database.
   - `AppointmentDAO`: Facilitates appointment-related operations like booking and cancellation.

3. **Controller**:
   - `HealthcareController`: Exposes RESTful endpoints for user authentication, report retrieval, and appointment management.

4. **Database Schema Initialization**:
   - **`schema.sql`**: This file is located in the `src/main/resources` directory and contains SQL commands to initialize the database schema. It includes commands to create tables for admins, patients, reports, and appointments, as well as insert initial data into these tables. When the application starts, Spring Boot automatically executes this script to set up the database structure required for the application to function properly.

## Setup Instructions

### Prerequisites
- Java Development Kit (JDK) 11 or higher.
- Apache Maven installed on your machine.
- A MySQL database set up for storing application data.

### Installation Steps

1. **Clone the Repository**:
   ```bash
   git clone <repository-url>
   cd HealthcareManagementSystem
   ```

2. **Configure Database Settings**:
   Update the `src/main/resources/application.properties` file with your database connection details:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   ```

3. **Run the Application**:
   Start the Spring Boot application:
   ```bash
   mvn spring-boot:run
   ```

4. **Accessing the API**:
   The application will run on `http://localhost:8080/api`. You can use tools like Postman or cURL to interact with the API endpoints.

## API Endpoints

### Authentication Endpoint

- **POST /api/authenticate**
  - Request Body: `{ "id": "username", "password": "password" }`
  - Response: Login success or failure message.

### Medical Reports Endpoints

- **GET /api/report-dates/{patientId}**
  - Response: List of distinct visit dates for the specified patient.

- **GET /api/reports/{patientId}/{visitDate}**
  - Response: List of medical reports for a specific patient on a given visit date.

### Appointment Endpoints

- **GET /api/available-appointment-dates/{patientId}**
  - Response: List of available appointment dates for the specified patient.

- **POST /api/book-appointment**
  - Request Body: `{ "patientId": "patient_id", "date": "appointment_date" }`
  - Response: Booking confirmation message.

- **POST /api/cancel-appointment**
  - Request Body: `{ "patientId": "patient_id", "date": "appointment_date" }`
  - Response: Cancellation confirmation message.

## Conclusion

The Healthcare Management System provides a robust framework for managing healthcare-related data efficiently. By leveraging Spring Boot's capabilities, this project aims to streamline processes within healthcare facilities, enhancing both patient experience and administrative efficiency. 

For further contributions or issues, please feel free to reach out or submit pull requests!

Citations:
[1] https://ppl-ai-file-upload.s3.amazonaws.com/web/direct-files/28908918/9574fa76-6127-4e4c-9dfc-adecbcb4bdb9/paste.txt
[2] https://ppl-ai-file-upload.s3.amazonaws.com/web/direct-files/28908918/49e8c3f9-45e5-4d3b-9cb4-487c73a8df1b/paste.txt
