
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>ADMIN PAGE</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="css/manager.css" rel="stylesheet" type="text/css"/>
        <style>
            img{
                width: 200px;
                height: 120px;
            }
        </style>
    <body>
        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Edit <b>Products</b></h2>
                        </div>             
                    </div>
                </div>
                <table class="table table-striped table-hover">
                    <!-- Edit Modal HTML -->
                    <form action="ServletDispatcher" method="post">
                        <div class="modal-header">						
                        </div>
                        <div class="modal-body">	
                            <div class="form-group">
                                <label>Name</label>
                                <input value="${product.id}" name="ID" type="text" class="form-control" required readonly>
                            </div>
                            <div class="form-group">
                                <label>Name</label>
                                <input value="${product.name}" name="name" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Image</label>
                                <input value="${product.image}" name="image" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Price</label>
                                <input value="${product.price}" name="price" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Quantity</label>
                                <input value="${product.quantity}" name="quantity" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Description</label>
                                <textarea name="description" class="form-control" required>${product.description}</textarea>
                            </div>
                            <div class="form-group">
                                <label>Category</label>
                                <select name="category" class="form-select" aria-label="Default select example">
                                    <c:forEach items="${Category}" var="c">
                                        <%--         <c:if test="${product.categoryID eq c.id}">
                                            <option value="${c.id}" selected>${c.name}</option>
                                        </c:if>
                                        <c:if test="${not product.categoryID eq c.id}">
                                            <option value="${c.id}">${c.name}</option>
</c:if>--%>
                                        <c:out value="${product.categoryID eq c.id}"></c:out>
                                        <c:out value="${product.categoryID}"></c:out>
                                        <c:out value="${c.id}"></c:out>
                                        <option value="${c.id}">${c.name}</option>

                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Active</label>
                                <c:if test="${product.status}">
                                    <input type="checkbox" name="status" checked >
                                </c:if>
                                <c:if test="${not product.status}">
                                    <input type="checkbox" name="status" >
                                </c:if>

                            </div>
                            <c:if test="${not empty msg}"> <p class="alert alert-danger">${msg}</p></c:if>

                        </div>
                        <div class="modal-footer">
                            <input type="hidden" name="btAction" value="editProduct">
                            <input type="submit" class="btn btn-success" value="Save">
                        </div>
                    </form>
                </table>
                <a href="home"><button type="button" class="btn btn-primary">Back to home</button> </a>
                <a href="ServletDispatcher?btAction=manager"><button type="button" class="btn btn-primary">Back to Manager</button> </a>
            </div>
            <div style="margin-bottom: 50px"></div>
        </div>
    </div>
</body>
</html>
