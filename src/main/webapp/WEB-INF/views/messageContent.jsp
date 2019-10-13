<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<c:choose>
    <c:when test="${message.mediaContent == null || message.mediaContent.mnemonic == 'UNSUPPORTED'}">
        <p>${message.text}</p>
    </c:when>
    <c:otherwise>
        <c:choose>
            <c:when test="${message.mediaContent.mnemonic == 'PHOTO'}">
                <div class="message-photo">
                    <img src="downloaded/${message.mediaContent.path}"/>
                    <p>${message.text}</p>
                </div>
            </c:when>
            <c:when test="${message.mediaContent.mnemonic == 'FILE'}">
                <div class="message-file">
                    <a href="<c:url value="/download-file-${message.mediaContent.id}"/>">
                        <div>
                            <img src="<c:url value="/static/icons/saveFile.png"/>"/>
                            <p>${message.mediaContent.filename}</p>
                        </div>
                    </a>
                    <p>${message.text}</p>
                </div>
            </c:when>
            <c:otherwise/>
        </c:choose>
    </c:otherwise>
</c:choose>
</body>
</html>
