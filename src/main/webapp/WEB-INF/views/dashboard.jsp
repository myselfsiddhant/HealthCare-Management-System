<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Healthcare Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .dashboard-container {
            max-width: 800px;
            margin: 20px auto;
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
        }
        .dashboard-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            border-bottom: 1px solid #ddd;
            padding-bottom: 15px;
        }
        .section {
            margin-top: 20px;
        }
        .card {
            background-color: #f9f9f9;
            border: 1px solid #ddd;
            border-radius: 4px;
            padding: 15px;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <div class="dashboard-container">
        <div class="dashboard-header">
            <h1>Welcome, ${sessionScope.username}!</h1>
            <a href="logout">Logout</a>
        </div>

        <div class="section">
            <h2>My Appointments</h2>
            <c:choose>
                <c:when test="${not empty appointments}">
                    <c:forEach var="appointment" items="${appointments}">
                        <div class="card">
                            <p>Date: <c:out value="${appointment.date}" /></p>
                            <p>Status: <c:out value="${appointment.status}" /></p>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p>No upcoming appointments</p>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="section">
            <h2>Medical Reports</h2>
            <c:choose>
                <c:when test="${not empty medicalReports}">
                    <c:forEach var="report" items="${medicalReports}">
                        <div class="card">
                            <p>Visit Date: <c:out value="${report.visitDate}" /></p>
                            <p>Diagnosis: <c:out value="${report.diagnosis}" /></p>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p>No medical reports available</p>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="section">
            <h2>Quick Actions</h2>
            <div class="card">
                <a href="book-appointment">Book New Appointment</a>
                <a href="view-reports">View All Reports</a>
            </div>
        </div>
    </div>
</body>
</html>
