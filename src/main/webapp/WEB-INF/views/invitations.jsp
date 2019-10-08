<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<c:choose>
    <c:when test="${invites.size() > 0}">
        <c:forEach items="${invites}" var="invitor">
            <a href="<c:url value="/view-profile-${invitor.ssoId}"/>">
                <div class="user-info-tablet">
                    <img src="${invitor.profilePhoto.path}" class="tablet-photo"/>
                    <p>${invitor.firstName} ${invitor.lastName}</p>
                    <p>
                        <c:url var="add" value="/accept-invitation-from-invitations-${invitor.ssoId}"/>
                        <form:form method="POST" action="${add}">
                            <input type="submit" value="Accept Invitation"/>
                        </form:form>
                    </p>
                </div>
            </a>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <p>Invitations list is empty</p>
    </c:otherwise>
</c:choose>
</body>
</html>
