<%-- 
    Document   : index
    Created on : Oct 29, 2026, 4:08:33 PM
    Author     : sudhangsu-shekhar-bairagi
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="catalog" class="bean.CatalogBean" scope="application"/>
<html>
<head><title>Online Store</title></head>
<body>
    <h2>Products</h2>
    <form action="result.jsp" method="post">
        <input type="hidden" name="action" value="add">
        <table border="1" cellpadding="6">
            <tr><th>Product</th><th>Price (Rs.)</th><th>Discount</th><th>Quantity</th></tr>
            <c:forEach var="p" items="${catalog.products}">
                <tr>
                    <td>${p.name}</td>
                    <td>${p.price}</td>
                    <td>
                        <c:if test="${p.discountPercent > 0}">${p.discountPercent}% off</c:if>
                    </td>
                    <%-- input name = qty_ + product id --%>
                    <td><input type="number" name="qty_${p.id}" min="0" value="0"></td>
                </tr>
            </c:forEach>
        </table><br>
        <input type="submit" value="Add to Cart">
    </form>
    <p><a href="result.jsp">View cart</a></p>
</body>
</html>
