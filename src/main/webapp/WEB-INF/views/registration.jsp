<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<div class="input-form">
    <div class="input-form-head">
        <h2>Registration</h2>
    </div>
    <div class="input-form-body">
        <form:form method="POST" modelAttribute="user">
            <form:input type="hidden" path="id" id="id"/>
            <div class="row">
                <label for="registration-firstName">First Name</label>
                <div>
                    <form:input type="text" path="firstName" id="registration-firstName" class="form-control"/>
                    <div class="has-error">
                        <form:errors path="firstName" class="help-inline"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <label for="registration-lastName">Last Name</label>
                <div>
                    <form:input type="text" path="lastName" id="registration-lastName" class="form-control"/>
                    <div class="has-error">
                        <form:errors path="lastName" class="help-inline"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <label for="registration-username">Username</label>
                <div>
                    <form:input type="text" path="ssoId" id="registration-username" class="form-control"/>
                    <div class="has-error">
                        <form:errors path="ssoId" class="help-inline"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <label for="registration-password">Password</label>
                <div>
                    <form:input type="password" path="password" id="registration-password" class="form-control"/>
                    <div class="has-error">
                        <form:errors path="password" class="help-inline"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <label for="registration-email">Email</label>
                <div>
                    <form:input type="text" path="email" id="registration-email" class="form-control"/>
                    <div class="has-error">
                        <form:errors path="email" class="help-inline"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div>
                    <input type="submit" value="Register"/> or <a href="<c:url value='/' />">Cancel</a>
                </div>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>