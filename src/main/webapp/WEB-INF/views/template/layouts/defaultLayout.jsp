<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="<c:url value='/static/css/main.css' />" rel="stylesheet" type="text/css"/>
    <title><tiles:insertAttribute name="title" ignore="true"/></title>
</head>
<body>
<header>
    <tiles:insertAttribute name="header"/>
</header>
<div id="content-wrapper">
    <div id="content">
        <tiles:insertAttribute name="body"/>
    </div>
</div>
<footer>
    <tiles:insertAttribute name="footer"/>
</footer>
</body>
</html>