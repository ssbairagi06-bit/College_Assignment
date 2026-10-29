<%-- 
    Document   : result
    Created on : Oct 29, 2026, 3:25:20 PM
    Author     : sudhangsu-shekhar-bairagi
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- Create the bean and copy all form fields into it --%>
<jsp:useBean id="student" class="bean.StudentBean" scope="request"/>
<jsp:setProperty name="student" property="*"/>

<%-- Validation result comes from the bean --%>
<c:set var="error" value="${student.errorMessage}"/>

<html>
    <head><title>Student Profile</title></head>
    <body>
        <c:choose>
            <%-- Invalid data: show message --%>
            <c:when test="${not empty error}">
                <h3 style="color:red"><c:out value="${error}"/></h3>
                <a href="index.jsp">Go back</a>
            </c:when>

            <%-- Valid data: show profile --%>
            <c:otherwise>
                <h2>Registration Successful - Student Profile</h2>
                <table border="1" cellpadding="6">
                    <tr><td>Name</td><td><c:out value="${student.name}"/></td></tr>
                    <tr><td>Roll No</td><td><c:out value="${student.rollNo}"/></td></tr>
                    <tr><td>Email</td><td><c:out value="${student.email}"/></td></tr>
                    <tr><td>Course</td><td><c:out value="${student.course}"/></td></tr>
                    <tr><td>Residence</td><td><c:out value="${student.residence}"/></td></tr>

                    <%-- Conditional section: hostel info only for hostellers --%>
                    <c:if test="${student.hosteller}">
                        <tr><td>Hostel</td><td><c:out value="${student.hostelName}"/></td></tr>
                        <tr><td>Room No</td><td><c:out value="${student.roomNo}"/></td></tr>
                    </c:if>
                </table>
                <p><a href="index.jsp">Register another student</a></p>
            </c:otherwise>
        </c:choose>
    </body>
</html>
