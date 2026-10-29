<%--
  Name: Sudhangsu Shekhar Bairagi | Roll No: 13071025097 | Date: 28-10-2026
  Assignment 3: Feedback form (View). Faculty list comes from FeedbackBoard.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="board" class="bean.FeedbackBoard" scope="application"/>
<html>
<head><title>Faculty Feedback</title></head>
<body>
    <h2>Faculty Feedback Form</h2>
    <form action="result.jsp" method="post">
        Faculty:
        <select name="faculty">
            <option value="">-- Select --</option>
            <c:forEach var="f" items="${board.facultyNames}">
                <option value="${f}">${f}</option>
            </c:forEach>
        </select><br><br>

        Rate each parameter (1 = Poor, 5 = Excellent):<br><br>
        Teaching:
        <select name="teaching"><c:forEach begin="1" end="5" var="i"><option>${i}</option></c:forEach></select><br><br>
        Subject Knowledge:
        <select name="knowledge"><c:forEach begin="1" end="5" var="i"><option>${i}</option></c:forEach></select><br><br>
        Communication:
        <select name="communication"><c:forEach begin="1" end="5" var="i"><option>${i}</option></c:forEach></select><br><br>
        Punctuality:
        <select name="punctuality"><c:forEach begin="1" end="5" var="i"><option>${i}</option></c:forEach></select><br><br>

        Comments:<br>
        <textarea name="comments" rows="4" cols="40"></textarea><br><br>
        <input type="submit" value="Submit Feedback">
    </form>
    <p><a href="result.jsp">View rating summary</a></p>
</body>
</html>
