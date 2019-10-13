<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<body>
<c:choose>
    <c:when test="${chats.size() > 0}">
        <c:forEach items="${chats}" var="chat">
            <a href="<c:url value="/simple-chat-${chat.id}" />">
                <div class="chat-info-tablet">
                    <img src="downloaded/${chat.photo.path}" class="tablet-photo"/>
                    <p>${chat.name}</p>
                </div>
            </a>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <p>No chats yet...</p>
    </c:otherwise>
</c:choose>
</body>
</html>
