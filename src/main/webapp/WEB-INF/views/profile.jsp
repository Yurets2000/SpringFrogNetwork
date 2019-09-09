<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<p>
    <img src="${user.profilePhoto.path}" class="profile-photo"/>
    <c:if test="${user.ssoId.equals(loggedInUser)}">
        <c:url var="uploadPhoto" value="/upload-photo"/>
        <form:form method="POST" action="${uploadPhoto}?${_csrf.parameterName}=${_csrf.token}"
                   enctype="multipart/form-data">
            Select Photo: <input type="file" name="file" onselect=""/><br>
            <input type="submit" value="Update"/>
        </form:form>
        <form:errors path="errors"/>
    </c:if>
</p>
<p>${user.firstName} ${user.lastName}</p>
<p>Username: ${user.ssoId}</p>
<p>Email: ${user.email}</p>
<p>
    <c:if test="${!(user.ssoId.equals(loggedInUser))}">
        <c:url var="getSimpleChat" value="/get-simple-chat-${user.ssoId}"/>
        <form:form method="GET" action="${getSimpleChat}">
            <input type="submit" value="Write Message"/>
        </form:form>
    </c:if>
</p>
</body>
</html>
