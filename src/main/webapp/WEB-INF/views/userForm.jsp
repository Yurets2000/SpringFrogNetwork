<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<div class="input-form">
    <div class="input-form-head">
        <h2>User Form</h2>
    </div>
    <div class="input-form-body">
        <form:form method="POST" modelAttribute="user">
            <form:input type="hidden" path="id" id="id"/>
            <div class="row">
                <label for="userForm-firstName">First Name</label>
                <div>
                    <form:input type="text" path="firstName" id="userForm-firstName" class="form-control"/>
                    <div class="has-error">
                        <form:errors path="firstName" class="help-inline"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <label for="userForm-lastName">Last Name</label>
                <div>
                    <form:input type="text" path="lastName" id="userForm-lastName" class="form-control"/>
                    <div class="has-error">
                        <form:errors path="lastName" class="help-inline"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <label for="userForm-username">Username</label>
                <div>
                    <c:choose>
                        <c:when test="${edit}">
                            <form:input type="text" path="ssoId" id="userForm-username" class="form-control input-sm"
                                        disabled="true"/>
                        </c:when>
                        <c:otherwise>
                            <form:input type="text" path="ssoId" id="userForm-username" class="form-control input-sm"/>
                            <div class="has-error">
                                <form:errors path="ssoId" class="help-inline"/>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="row">
                <label for="userForm-password">Password</label>
                <div>
                    <form:input type="password" path="password" id="userForm-password" class="form-control"/>
                    <div class="has-error">
                        <form:errors path="password" class="help-inline"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <label for="userForm-email">Email</label>
                <div>
                    <form:input type="text" path="email" id="userForm-email" class="form-control"/>
                    <div class="has-error">
                        <form:errors path="email" class="help-inline"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <label for="userForm-userProfiles">Roles</label>
                <div>
                    <form:select path="userProfiles" id="userForm-userProfiles" items="${roles}" multiple="true"
                                 itemValue="id" itemLabel="type" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="userProfiles" class="help-inline"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div>
                    <c:choose>
                        <c:when test="${edit}">
                            <input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a
                                href="<c:url value='/list' />">Cancel</a>
                        </c:when>
                        <c:otherwise>
                            <input type="submit" value="Register" class="btn btn-primary btn-sm"/> or <a
                                href="<c:url value='/list' />">Cancel</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>