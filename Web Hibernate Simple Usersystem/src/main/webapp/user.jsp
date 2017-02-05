<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>User</title>
        <link href="/MVCWebApp_Hibernate/style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <h2>User</h2>
        <c:choose>
            <c:when test="${userCount != 0}">
                <!-- Wanneer er user opgeslagen zijn, worden ze hier getoond -->
                <table class="user">
                    <tr>
                        <td>
                            <strong>First name</strong>
                        </td>
                        <td>
                            <strong>Last name</strong>
                        </td>
                        <td>
                            <strong>email</strong>
                        </td>
                        <td></td>
                    </tr>
                    <c:forEach var="userTemp" items="${user}">
                        <!-- Each user gets a row -->
                        <tr>
                            <td>
                                ${userTemp.firstName}
                            </td>
                            <td>
                                ${userTemp.lastName}
                            </td>
                            <td>
                                ${userTemp.email}
                            </td>
                            <td>
                                <a href="user/edit?id=${userTemp.userId}">edit</a> |
                                <a href="javascript:if(confirm('Are you sure you want to delete user?'))
                                   window.location='user/delete?id=${userTemp.userId}';">delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <!-- If no user, shows this message -->
                There are no users.
            </c:otherwise>
        </c:choose>
        <p>
            <a href="user/new">Add new user.</a>
        </p>
        <p>
            <a href="index">Home</a>
        </p>
    </body>
</html>
