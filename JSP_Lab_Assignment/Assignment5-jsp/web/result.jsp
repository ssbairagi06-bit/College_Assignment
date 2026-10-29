<%--
  Name: Sudhangsu Shekhar Bairagi | Roll No: 13071025097 | Date: 28-10-2026
  Assignment 5: Cart summary (View). The cart is a session-scoped JavaBean.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="catalog" class="bean.CatalogBean" scope="application"/>
<jsp:useBean id="cart" class="bean.CartBean" scope="session"/>

<%-- Add items: loop over products and read qty_<id> from the form --%>
<c:if test="${param.action == 'add'}">
    <c:forEach var="p" items="${catalog.products}">
        <c:set var="key" value="qty_${p.id}"/>
        <c:set var="q" value="${param[key]}"/>
        <c:set var="done" value="${cart.updateItem(p, q)}"/>
    </c:forEach>
</c:if>

<%-- Empty the cart --%>
<c:if test="${param.action == 'clear'}">
    <c:set var="done" value="${cart.clear()}"/>
</c:if>

<html>
<head><title>Cart Summary</title></head>
<body>
<h2>Your Cart</h2>
<c:choose>
    <c:when test="${empty cart.items}">
        <p>Your cart is empty.</p>
    </c:when>
    <c:otherwise>
        <table border="1" cellpadding="6">
            <tr><th>Product</th><th>Price</th><th>Qty</th><th>Subtotal</th><th>Discount</th><th>Total</th></tr>
            <c:forEach var="item" items="${cart.items}">
                <tr>
                    <td>${item.product.name}</td>
                    <td><fmt:formatNumber value="${item.product.price}" pattern="#,##0.00"/></td>
                    <td>${item.quantity}</td>
                    <td><fmt:formatNumber value="${item.subtotal}" pattern="#,##0.00"/></td>
                    <td>
                        <%-- Show discount only where applicable --%>
                        <c:if test="${item.product.discountPercent > 0}">
                            <span style="color:green">
                                ${item.product.discountPercent}% (-<fmt:formatNumber value="${item.discount}" pattern="#,##0.00"/>)
                            </span>
                        </c:if>
                        <c:if test="${item.product.discountPercent == 0}">-</c:if>
                    </td>
                    <td><fmt:formatNumber value="${item.total}" pattern="#,##0.00"/></td>
                </tr>
            </c:forEach>
        </table>
        <p>Subtotal: Rs. <fmt:formatNumber value="${cart.subtotal}" pattern="#,##0.00"/></p>
        <p>Total Discount: Rs. <fmt:formatNumber value="${cart.totalDiscount}" pattern="#,##0.00"/></p>
        <h3>Amount Payable: Rs. <fmt:formatNumber value="${cart.grandTotal}" pattern="#,##0.00"/></h3>
        <a href="result.jsp?action=clear">Clear cart</a> |
    </c:otherwise>
</c:choose>
<a href="index.jsp">Continue shopping</a>
</body>
</html>
