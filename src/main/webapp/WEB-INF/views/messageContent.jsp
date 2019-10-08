<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.springfrog.utils.MediaContentMnemonic" %>
<%@ page import="com.springfrog.utils.MediaContentMnemonicResolver" %>
<html>
<body>
<c:choose>
    <c:when test="${message.mediaContent == null}">
        <p>${message.text}</p>
    </c:when>
    <c:otherwise>
        <c:choose>
            <c:when test="${MediaContentMnemonicResolver.resolveMnemonic(message.mediaContent) == MediaContentMnemonic.PHOTO}">
                <div class="message-photo">
                    <img src="${message.mediaContent.path}"/>
                    <p>${message.text}</p>
                </div>
            </c:when>
            <c:when test="${MediaContentMnemonicResolver.resolveMnemonic(message.mediaContent) == MediaContentMnemonic.FILE}">
                <div class="message-file">

                </div>
            </c:when>
            <c:otherwise/>
        </c:choose>
    </c:otherwise>
</c:choose>
</body>
</html>
