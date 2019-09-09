<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<div>
    <div>
        <span>Dear <strong>${loggedInUser}</strong>, You are not authorized to access this page.</span>
        <span><a href="<c:url value="/logout" />">Logout</a></span>
    </div>
</div>
</body>
</html>