<%-- 
    Document   : index.jsp
    Created on : Oct 29, 2026, 3:45:39 PM
    Author     : sudhangsu-shekhar-bairagi
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Payroll Slip Generator</title></head>
<body>
    <h2>Employee Payroll Slip Generator</h2>
    <form action="result.jsp" method="post">
        Employee ID: <input type="text" name="empId">
        <input type="submit" value="Generate Pay Slip">
    </form>
    <p>Sample IDs: E101, E102, E103, E104</p>
</body>
</html>