<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<body>
<form method="GET" action="<c:url value="/search-user"/>">
    <input type="text" name="ssoId" id="search-username"/>
    <input type="submit" value="Search"/>
</form>
<c:if test="${searchedUser != null}">
    <c:choose>
        <c:when test="${searchedUser.user == null}">
            <p>No user with such username</p>
        </c:when>
        <c:otherwise>
            <div class="user-info-tablet">
                <img src="${searchedUser.user.profilePhoto.path}" class="tablet-photo"/>
                <p>${searchedUser.user.firstName} ${searchedUser.user.lastName}</p>
                <p>
                    <c:url var="viewProfile" value="/view-profile-${searchedUser.user.ssoId}"/>
                    <form:form method="GET" action="${viewProfile}">
                        <input type="submit" value="View Profile">
                    </form:form>
                </p>
                <p>
                    <c:if test="${searchedUser.canAddToFriends}">
                        <c:choose>
                            <c:when test="${!searchedUser.alreadyInInvites}">
                                <c:url var="invite" value="/invite-user-${searchedUser.user.ssoId}"/>
                                <form:form method="POST" action="${invite}">
                                    <input type="submit" value="Invite To Friends"
                                        ${searchedUser.alreadyInInvitees ? 'disabled="disabled"' : ''}/>
                                </form:form>
                            </c:when>
                            <c:otherwise>
                                <c:url var="add" value="/add-friend-${searchedUser.user.ssoId}"/>
                                <form:form method="POST" action="${add}">
                                    <input type="submit" value="Add To Friends"/>
                                </form:form>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                </p>
            </div>
        </c:otherwise>
    </c:choose>
</c:if>
</body>
</html>
