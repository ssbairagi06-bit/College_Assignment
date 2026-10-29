<%-- 
    Document   : index
    Created on : Oct 29, 2026, 3:24:30 PM
    Author     : sudhangsu-shekhar-bairagi
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head><title>Student Registration</title></head>
    <body>
        <h2>Student Registration</h2>
        <form action="result.jsp" method="post">
            Name: <input type="text" name="name"><br><br>
            Roll No: <input type="text" name="rollNo"><br><br>
            Email: <input type="text" name="email"><br><br>
            Course:
            <select name="course">
                <option value="">-- Select --</option>
                <option>MCA</option>
                <option>MBA</option>
                <option>B.Tech</option>
            </select><br><br>
            Residence:
            <input type="radio" name="residence" value="Hosteller"> Hosteller
            <input type="radio" name="residence" value="Day Scholar"> Day Scholar<br><br>
            <i>Fill the next two fields only if you are a hosteller:</i><br>
            Hostel Name: <input type="text" name="hostelName"><br><br>
            Room No: <input type="text" name="roomNo"><br><br>
            <input type="submit" value="Register">
        </form>
    </body>
</html>
