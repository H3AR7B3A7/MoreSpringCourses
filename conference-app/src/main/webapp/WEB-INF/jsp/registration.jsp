<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 9/20/2021
  Time: 2:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="styles/style.css" />
</head>
<body>
<h1>Registration</h1>
<form:form modelAttribute="registration">
    <form:errors path="*" cssClass="errorblock" element="div" />
    <table>
        <tr>
            <td>
                <spring:message code="name"/>:
            </td>
            <td>
                <form:input path="name"/>
            </td>
            <td>
                <form:errors path="name" cssClass="error" />
            </td>
        </tr>
        <tr>
            <td colspan="3">
                <button type="submit">
                    <spring:message code="save.changes"/>
                </button>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>