<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tools</title>
    </head>
    <body>
        <h1>Tools page</h1>
        <p>select city:</p>
        <select>
            <c:forEach items="${cities}" var="city"> 
                <option value="${city.id}">${city.title}</option>
            </c:forEach>
        </select>
    </body>
</html>
