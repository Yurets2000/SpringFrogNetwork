<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<div class="input-form">
    <div class="input-form-head">
        <h2>Login</h2>
    </div>
    <div class="input-form-body">
        <c:url var="loginUrl" value="/login"/>
        <form action="${loginUrl}" method="post">
            <c:if test="${param.error != null}">
                <div class="alert alert-error">
                    <p>Invalid username and password.</p>
                </div>
            </c:if>
            <div class="form-group">
                <div class="form-group-item">
                    <input type="text" class="form-control" id="login-username" name="ssoId"
                           placeholder="Enter Username" required>
                </div>
                <div class="form-group-item">
                    <input type="password" class="form-control" id="login-password" name="password"
                           placeholder="Enter Password" required>
                </div>
                <div class="form-group-item">
                    <label><input type="checkbox" id="login-rememberme" name="remember-me"> Remember Me</label>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="form-group-item">
                    <input type="submit" value="Log in">
                </div>
            </div>
        </form>
        <a href="<c:url value="/registration"/>">Register Now</a>
    </div>
</div>
</body>
</html>