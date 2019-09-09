<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<div>
    <div>
        ${success}
    </div>
    <span class="floatRight">
        <a href="<c:url value='/login' />">Login</a>
    </span>
</div>
</body>
</html>