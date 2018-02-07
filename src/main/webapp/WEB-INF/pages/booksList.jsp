<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Javarush test task</title>
    <!-- Bootstrap CSS -->
    <%-- <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet"> --%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <style type="text/css">
        .myrow-container {
            margin: 20px;
        }

        .btn {
            padding: 2px 2px;
            width: 5em;
            height: 2em;
            background-color: #4d3a1e;
            color: #f1f1f1;
            border-radius: 0;
            transition: .2s;
        }

        .btn:hover, .btn:focus {
            border: 1px solid #4d3a1e;
            background-color: #fff;
            color: #000;
        }

        a.aEdit:link, a.aDelete:link {
            color: #a83016;
        }

        a.aEdit:visited, a.aDelete:visited {
            color: #947872;
        }

        a.aEdit:hover, a.aDelete:hover {
            color: #60a870;
        }

        a.aEdit:active, a.aDelete:active {
            color: #ded728;
        }

        a.aCreateBook:link {
            color: #d1cbbc;
        }

        a.aCreateBook:visited {
            color: #c4bba5;
        }

        a.aCreateBook:hover {
            color: #a0cc95;
        }

        .panel-footer a{
            font-size: 1.2em;
        }
        .panel-footer a:link {
            color: #d1cbbc;
        }

        .panel-footer a:visited {
            color: #c4bba5;
        }

        .panel-footer a:hover {
            color: #a0cc95;
        }

        .panel-footer a:active {
            color: #ded728;
        }

    </style>
</head>
<body class=".container-fluid" style="background-color:whitesmoke">
<div class="container myrow-container">
    <div class="panel">
        <div class="panel-heading" style="background-color:#786455">
            <h3 class="panel-title ">
                <div align="left"><a class="aCreateBook" href="createBook">Create new book</a></div>
            </h3>
        </div>
        <div class="panel-body">
            <c:if test="${empty bookList}">
                There are no Books
            </c:if>
            <c:if test="${not empty bookList}">
                <form action="searchBook">
                    <div class="row">
                        <div class="col-md-2">Search books by name:</div>
                        <div class="col-md-2"><input type="text" name="searchBook" id="searchBook"
                                                     placeholder="type name here.."></div>
                        <div class="col-md-2"><input class="btn btn-xs" type='submit' value='Search'/></div>
                    </div>
                </form>
                <form action="searchBookByYear">
                    <div class="row">
                        <div class="col-md-2">Search books by year:</div>
                        <div class="col-md-2"><input type="text" name="searchBookByYear" id="searchBookByYear"
                                                     placeholder="type year here.."></div>
                        <div class="col-md-2"><input class="btn btn-xs" type='submit' value='Search'/></div>
                    </div>
                </form>

                <table class="table table-hover table-bordered">
                    <thead style="background-color: #b39b89;">
                    <tr>
                        <th>Id</th>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Author</th>
                        <th>Isbn</th>
                        <th>Print Year</th>
                        <th>readAlready</th>
                        <th>Read</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${bookList}" var="book">
                        <tr>
                            <th><c:out value="${book.id}"/></th>
                            <th><c:out value="${book.title}"/></th>
                            <th><c:out value="${book.description}"/></th>
                            <th><c:out value="${book.author}"/></th>
                            <th><c:out value="${book.isbn}"/></th>
                            <th><c:out value="${book.printYear}"/></th>
                            <th><c:out value="${book.readAlready}"/></th>
                            <th><a class="aRead" href="readBook?id=<c:out value='${book.id}'/>">Read</a></th>
                            <th><a class="aEdit" href="editBook?id=<c:out value='${book.id}'/>">Update</a></th>
                            <th><a class="aDelete" href="deleteBook?id=<c:out value='${book.id}'/>">Delete</a></th>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
        <div align="center" class="panel-footer" style="background-color:#786455" id="pagination">
            <c:url value="/" var="prev">
                <c:param name="page" value="${page-1}"/>
            </c:url>
            <c:if test="${page > 1}">
                <a href="<c:out value="${prev}" />" class="pn prev">Prev</a>
            </c:if>

            <c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
                <c:choose>
                    <c:when test="${page == i.index}">
                        <span>${i.index}</span>
                    </c:when>
                    <c:otherwise>
                        <c:url value="/" var="url">
                            <c:param name="page" value="${i.index}"/>
                        </c:url>
                        <a href='<c:out value="${url}" />'>${i.index}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:url value="/" var="next">
                <c:param name="page" value="${page + 1}"/>
            </c:url>
            <c:if test="${page + 1 <= maxPages}">
                <a href='<c:out value="${next}" />' class="pn next">Next</a>
            </c:if>
        </div>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

    <%-- <script src="<c:url value="/resources/js/jquery-2.1.3.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    --%>

</body>
</html>
