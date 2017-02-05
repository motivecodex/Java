<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>${pageTitle}</title>
        <link href="/MVCWebApp_Hibernate/style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <h2>${pageTitle}</h2>
        <!-- if error != empty, show error -->
        <c:if test="${not empty error}">
            <p>${error}</p>
        </c:if>
        <c:choose>
            <c:when test="${empty id}">
                <!-- If id is empty, then create new user -->
                <form id="userNew" action="new" method="post">
                </c:when>
                <c:otherwise>
                    <!-- Else update user -->
                    <form id="userEdit" action="edit" method="post">
                    </c:otherwise>
                </c:choose>
                <p>
                    <c:if test="${not empty id}">
                        <input type="hidden" name="id" id="id" value="${id}"></input>
                    </c:if>
                <table border="0">
                    <tr>
                        <td>
                            <label for="firstName">First name</label>
                        </td>
                        <td>
                            <input type="textfield" id="firstName" name="firstName" value="${firstName}"></input>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="lastName">Last name</label>
                        </td>
                        <td>
                            <input type="textfield" id="lastName" name="lastName" value="${lastName}"></input>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="email">Email</label>
                        </td>
                        <td>
                            <input type="textfield" id="email" name="email" value="${email}"></input>
                        </td>
                    </tr>
                </table>
                </p>
                <p>
                    <input class="submit" type="submit" value="send">
                </p>
            </form>
    </body>
</html>