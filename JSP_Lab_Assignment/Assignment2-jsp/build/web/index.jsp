<%--
  Name: Sudhangsu Shekhar Bairagi | Roll No: 13071025097 | Date: 28-10-2026
  Assignment 2: Employee ID input page (View)
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
