<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<body>
<c:choose>
    <c:when test="${friends.size() > 0}">
        <c:forEach items="${friends}" var="friend">
            <a href="<c:url value="/view-profile-${friend.ssoId}"/>">
                <div class="user-info-tablet">
                    <img src="${friend.profilePhoto.path}" class="tablet-photo"/>
                    <p>${friend.firstName} ${friend.lastName}</p>
                </div>
            </a>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <p>No friends yet...</p>
    </c:otherwise>
</c:choose>
</body>
</html>
