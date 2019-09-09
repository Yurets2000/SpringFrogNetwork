<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<body>
<%@include file="authHeader.jsp" %>
<div class="input-form">
    <div class="input-form-head">
        <h2>List of Users</h2>
    </div>
    <div class="input-form-body">
        <table class="user-table">
            <thead>
            <tr>
                <th>Firstname</th>
                <th>Lastname</th>
                <th>Email</th>
                <th>SSO ID</th>
                <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                    <th width="100">Edit</th>
                </sec:authorize>
                <sec:authorize access="hasRole('ADMIN')">
                    <th width="100">Delete</th>
                </sec:authorize>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.email}</td>
                    <td>${user.ssoId}</td>
                    <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                        <td><a href="<c:url value='/edit-user-${user.ssoId}' />">Edit</a></td>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ADMIN')">
                        <td><a href="<c:url value='/delete-user-${user.ssoId}' />">Delete</a></td>
                    </sec:authorize>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <sec:authorize access="hasRole('ADMIN')">
    <a href="<c:url value='/add-user'/>">Add New User</a>
    </sec:authorize>
</body>
</html>