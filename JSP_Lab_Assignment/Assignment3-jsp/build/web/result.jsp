<%--
  Name: Sudhangsu Shekhar Bairagi | Roll No: 13071025097 | Date: 28-10-2026
  Assignment 3: Saves feedback in the beans and shows average ratings (View).
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="fb" class="bean.FeedbackBean" scope="request"/>
<jsp:setProperty name="fb" property="*"/>
<jsp:useBean id="board" class="bean.FeedbackBoard" scope="application"/>

<%-- Save only when the form was submitted (faculty param present) --%>
<c:if test="${not empty param.faculty or not empty param.teaching}">
    <c:set var="error" value="${fb.errorMessage}"/>
    <c:if test="${empty error}">
        <c:set var="saved" value="${board.addFeedback(fb)}"/>
    </c:if>
</c:if>

<html>
<head>
    <title>Feedback Summary</title>
    <style>
        .bad  { background: #ffd6d6; }
        .none { color: gray; }
    </style>
</head>
<body>
<c:if test="${not empty error}">
    <h3 style="color:red"><c:out value="${error}"/></h3>
</c:if>
<c:if test="${saved}">
    <h3 style="color:green">Thank you! Your feedback was submitted.</h3>
</c:if>

<h2>Faculty Rating Summary</h2>
<table border="1" cellpadding="6">
    <tr><th>Faculty</th><th>Responses</th><th>Teaching</th><th>Knowledge</th>
        <th>Communication</th><th>Punctuality</th><th>Overall</th><th>Remark</th></tr>

    <c:forEach var="s" items="${board.summaries}">
        <c:choose>
            <c:when test="${s.count == 0}">
                <tr class="none"><td>${s.name}</td><td>0</td>
                    <td colspan="6">No feedback yet</td></tr>
            </c:when>
            <c:otherwise>
                <%-- Highlight row if faculty needs improvement --%>
                <tr class="${s.needsImprovement ? 'bad' : ''}">
                    <td>${s.name}</td>
                    <td>${s.count}</td>
                    <td><fmt:formatNumber value="${s.teaching}" pattern="0.0"/></td>
                    <td><fmt:formatNumber value="${s.knowledge}" pattern="0.0"/></td>
                    <td><fmt:formatNumber value="${s.communication}" pattern="0.0"/></td>
                    <td><fmt:formatNumber value="${s.punctuality}" pattern="0.0"/></td>
                    <td><b><fmt:formatNumber value="${s.overall}" pattern="0.00"/></b></td>
                    <td>
                        <c:if test="${s.needsImprovement}"><span style="color:red">Needs Improvement</span></c:if>
                        <c:if test="${not s.needsImprovement}"><span style="color:green">Good</span></c:if>
                    </td>
                </tr>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</table>
<p><a href="index.jsp">Give feedback</a></p>
</body>
</html>
