<%--
  Created by IntelliJ IDEA.
  User: Nurislam
  Date: 22.05.2018
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/resources/templates/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<t:mainLayout title="LogIn">

    <form:form class="form-horizontal" method="POST" commandName="user">
        <t:input label="Email" required="true" path="email"/>
        <form:errors path="email" />
        <t:input label="Password" required="true" path="password" />
        <form:errors path="password"/>
        <button type="submit" class="btn btn-success">Log In</button>
    </form:form>
</t:mainLayout>