<%--
  Name: Sudhangsu Shekhar Bairagi | Roll No: 13071025097 | Date: 28-10-2026
  Assignment 4: Search form (View)
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="library"
             class="bean.LibraryBean"
             scope="request" />

<jsp:setProperty name="library"
                 property="keyword"
                 param="keyword" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Results</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 30px;
        }

        table {
            border-collapse: collapse;
            width: 80%;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #eee;
        }

        .available {
            background-color: #d4f7d4;
            color: green;
        }

        .issued {
            background-color: #ffd6d6;
            color: red;
        }
    </style>
</head>

<body>

<h2>Library Search Results</h2>

<c:choose>

    <%-- Empty keyword --%>
    <c:when test="${empty library.keyword}">
        <p style="color:red;">
            Please enter a book title or author to search.
        </p>
    </c:when>

    <%-- No matching books --%>
    <c:when test="${empty library.results}">
        <p>
            No books found for
            "<c:out value="${library.keyword}" />".
        </p>
    </c:when>

    <%-- Display matching books --%>
    <c:otherwise>

        <h3>
            Results for
            "<c:out value="${library.keyword}" />"
        </h3>

        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Status</th>
                </tr>
            </thead>

            <tbody>

                <c:forEach var="b" items="${library.results}">

                    <tr>
                        <td><c:out value="${b.id}" /></td>
                        <td><c:out value="${b.title}" /></td>
                        <td><c:out value="${b.author}" /></td>

                        <c:choose>

                            <c:when test="${b.available}">
                                <td class="available">
                                    <strong>Available</strong>
                                </td>
                            </c:when>

                            <c:otherwise>
                                <td class="issued">
                                    <strong>Issued</strong>
                                </td>
                            </c:otherwise>

                        </c:choose>
                    </tr>

                </c:forEach>

            </tbody>
        </table>

    </c:otherwise>

</c:choose>

<br>

<a href="index.jsp">New Search</a>

</body>
</html>