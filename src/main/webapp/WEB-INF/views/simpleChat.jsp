<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<body>
<h2>${chat.name}</h2>
<p>
    <c:forEach items="${chat.messages}" var="message">
    <div class="message-info-tablet">
        <div class="message-header">
            <img src="downloaded/${message.sender.profilePhoto.path}" class="tablet-photo"/>
<p>${message.sender.firstName} ${message.sender.lastName}</p>
</div>
<hr>
<%@include file="messageContent.jsp" %>
<p class="message-footer"><fmt:formatDate type="time" value="${message.sendingTime}"/></p>
</div>
<br>
</c:forEach>
</p>
<hr>
<p>
    <c:url var="writeToSimpleChat" value="/write-to-simple-chat-${chat.id}"/>
    <form:form method="POST" modelAttribute="messageWrapper"
               action="${writeToSimpleChat}?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data">
        <form:textarea path="text"/><br>
        <form:input type="file" path="addendum"/><br>
        <input type="submit" value="Send"/>
    </form:form>
</p>
</body>
</html>
