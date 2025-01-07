<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Profile</title>
</head>
<body>
    <h2>User Profile</h2>
    
    <c:if test="${not empty message}">
        <div style="color: green;">
            <c:out value="${message}" />
        </div>
    </c:if>

    <c:if test="${not empty user}">
        <div>
            <p>Username: <c:out value="${user.username}" /></p>
            <p>Email: <c:out value="${user.email}" /></p>
        </div>
    </c:if>
</body>
</html>
