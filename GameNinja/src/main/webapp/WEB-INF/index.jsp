<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ninja Gold</title>
</head>
<body>
<h1>Ninja Gold</h1>
    <p>Your Gold: ${gold}</p>
    
    <form action="/farm" method="post">
        <button type="submit">Farm</button>
    </form>
    <form action="/cave" method="post">
        <button type="submit">Cave</button>
    </form>
    <form action="/house" method="post">
        <button type="submit">House</button>
    </form>
    <form action="/casino" method="post">
        <button type="submit">Casino</button>
    </form>
    <form action="/spa" method="post">
        <button type="submit">Spa</button>
    </form>
    <form action="/reset" method="post">
        <button type="submit">Reset</button>
    </form>

    <h2>Activities:</h2>
    <ul>
        <c:forEach items="${activities}" var="activity">
            <li>${activity}</li>
        </c:forEach>
        </ul>
</body>
</html>