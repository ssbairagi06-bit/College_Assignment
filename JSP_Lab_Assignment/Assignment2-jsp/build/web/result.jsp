<%--
  Name: Sudhangsu Shekhar Bairagi | Roll No: 13071025097 | Date: 28-10-2026
  Assignment 2: Pay slip (View). Calculations are in PayrollBean; JSP only displays.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="pay" class="bean.PayrollBean" scope="request"/>
<jsp:setProperty name="pay" property="empId"/>
<jsp:useBean id="today" class="java.util.Date"/>

<html>
<head><title>Pay Slip</title></head>
<body>
<c:choose>
    <c:when test="${not pay.found}">
        <h3 style="color:red">Employee ID not found. Please try again.</h3>
        <a href="index.jsp">Go back</a>
    </c:when>

    <c:otherwise>
        <h2>Pay Slip - <fmt:formatDate value="${today}" pattern="MMMM yyyy"/></h2>
        <p>
            ID: <c:out value="${pay.empId}"/> |
            Name: <c:out value="${pay.name}"/> |
            Designation: <c:out value="${pay.designation}"/> |
            Department: <c:out value="${pay.department}"/>
        </p>

        <%-- Earnings table (loop over rows from the bean) --%>
        <table border="1" cellpadding="6">
            <tr><th>Earnings</th><th>Amount (Rs.)</th></tr>
            <c:forEach var="row" items="${pay.earnings}">
                <tr><td>${row[0]}</td>
                    <td><fmt:formatNumber value="${row[1]}" pattern="#,##0.00"/></td></tr>
            </c:forEach>
            <tr><th>Gross Salary</th>
                <th><fmt:formatNumber value="${pay.gross}" pattern="#,##0.00"/></th></tr>
        </table>
        <br>

        <%-- Deductions table --%>
        <table border="1" cellpadding="6">
            <tr><th>Deductions</th><th>Amount (Rs.)</th></tr>
            <c:forEach var="row" items="${pay.deductions}">
                <tr><td>${row[0]}</td>
                    <td><fmt:formatNumber value="${row[1]}" pattern="#,##0.00"/></td></tr>
            </c:forEach>
            <tr><th>Total Deductions</th>
                <th><fmt:formatNumber value="${pay.totalDeductions}" pattern="#,##0.00"/></th></tr>
        </table>

        <%-- Conditional tax bracket message --%>
        <p>Tax bracket applied:
        <c:choose>
            <c:when test="${pay.taxRate == 0}">0% (gross up to Rs. 25,000)</c:when>
            <c:when test="${pay.taxRate == 5}">5% (gross Rs. 25,001 - 50,000)</c:when>
            <c:when test="${pay.taxRate == 10}">10% (gross Rs. 50,001 - 1,00,000)</c:when>
            <c:otherwise>20% (gross above Rs. 1,00,000)</c:otherwise>
        </c:choose>
        </p>

        <h3 style="color:green">Net Salary: Rs. <fmt:formatNumber value="${pay.netSalary}" pattern="#,##0.00"/></h3>
        <a href="index.jsp">Generate another</a>
    </c:otherwise>
</c:choose>
</body>
</html>
