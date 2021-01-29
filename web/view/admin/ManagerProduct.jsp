<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@page
    contentType="text/html" pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html lang="en">
        <head>
            <meta charset="utf-8" />
            <meta http-equiv="X-UA-Compatible" content="IE=edge" />
            <meta name="viewport" content="width=device-width, initial-scale=1" />
            <title>ADMIN PAGE</title>
            <link
                rel="stylesheet"
                href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round"
                />
            <link
                rel="stylesheet"
                href="https://fonts.googleapis.com/icon?family=Material+Icons"
                />
            <link
                rel="stylesheet"
                href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
                />
            <link
                rel="stylesheet"
                href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
                />
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
            <link href="css/manager.css" rel="stylesheet" type="text/css" />
            <style>
                img {
                    width: 200px;
                    height: 120px;
                }
            </style>
        </head>

        <body>
            <div class="container">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-sm-6">
                                <h2>Manage <b>Products</b></h2>
                            </div>
                            <div class="col-sm-6">
                                <h2>${requestScope.msg}</h2>
                                <a
                                    href="#addProductModal"
                                    class="btn btn-success"
                                    data-toggle="modal"
                                    ><i class="material-icons">&#xE147;</i>
                                    <span>Add New Product</span></a
                                >
                            </div>
                        </div>
                    </div>

                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>
                                    <span class="custom-checkbox">
                                        <input type="checkbox" id="selectAll" />
                                        <label for="selectAll"></label>
                                    </span>
                                </th>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Image</th>
                                <th>Price</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                        <form action="ServletDispatcher" method="get" class="form-inline my-2 my-lg-0">
                            <div class="input-group input-group-sm" style="margin-bottom:  1rem">
                                <input name="txtSearch" type="text" value="${txtSearch}" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Search..." style="display: flex;">
                                <div class="input-group-append">
                                    <button type="submit" class="btn btn-secondary btn-number" name="btAction" value="searchByNameAdmin" style="background-color: #fff; display: flex; align-items: center;">
                                        <i class="fa fa-search"></i>
                                    </button>
                                </div>
                            </div>
                        </form>

                        <!--DELETE FORM-->
                        <form action="ServletDispatcher" onsubmit="return confirm('Are you sure to delete?')" method="post"> 
                            <c:forEach items="${Products}" var="p">              
                                <tr>
                                    <td>
                                        <span class="custom-checkbox">
                                            <input type="checkbox" id="checkbox1" name="deleteList" value="${p.id}">
                                            <label for="checkbox1"></label>
                                        </span>
                                    </td>
                                    <td>${p.id}</td>
                                    <td>${p.name}</td>
                                    <td>
                                        <img src="${p.image}">
                                    </td>
                                    <td>${p.price} VND</td>
                                    <td>
                                        <a href="ServletDispatcher?btAction=loadEdit&pid=${p.id}"  class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                        <a href="ServletDispatcher?btAction=deleteProduct&pid=${p.id}" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <jsp:include page="Paging.jsp"></jsp:include>
                                <input type="hidden" name="btAction" value="deleteProducts">
                                <button type="submit" class="btn btn-danger" data-toggle="modal"><span>Delete</span></button>
                                <a href="home"><button type="button" class="btn btn-primary">Back to home</button> </a>
                                </tr>
                            </form>
                            </tbody>
                        </table>

                        <a href="#">Back to top</a>

                        <!-- ADD Modal -->
                        <div id="addProductModal" class="modal fade">
                            <div class="modal-dialog">
                                <form action="ServletDispatcher" method="GET">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h4 class="modal-title">Add Product</h4>
                                            <button
                                                type="button"
                                                class="close"
                                                data-dismiss="modal"
                                                aria-hidden="true"
                                                >
                                                &times;
                                            </button>
                                        </div>

                                        <div class="modal-body">
                                            <div class="form-group">
                                                <label>Name</label>
                                                <input
                                                    name="name"
                                                    type="text"
                                                    class="form-control"
                                                    required
                                                    />
                                            </div>

                                            <div class="form-group">
                                                <label>Image</label>
                                                <input
                                                    name="image"
                                                    type="text"
                                                    class="form-control"
                                                    required
                                                    />
                                            </div>

                                            <div class="form-group">
                                                <label>Price</label>
                                                <input
                                                    name="price"
                                                    type="text"
                                                    class="form-control"
                                                    required
                                                    />
                                            </div>

                                            <div class="form-group">
                                                <label>Quantity</label>
                                                <input
                                                    name="quantity"
                                                    type="text"
                                                    class="form-control"
                                                    required
                                                    />
                                            </div>

                                            <div class="form-group">
                                                <label>Description</label>
                                                <textarea
                                                    name="description"
                                                    class="form-control"
                                                    required
                                                    ></textarea>
                                            </div>

                                            <div class="form-group">
                                                <label>Category</label>
                                                <select
                                                    name="category"
                                                    class="form-select"
                                                    aria-label="Default select example"
                                                    >
                                                <c:forEach items="${Category}" var="c">
                                                    <option value="${c.id}">${c.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>Active</label>
                                            <input type="checkbox" checked name="status" />
                                        </div>
                                    </div>

                                    <div class="modal-footer">
                                        <input
                                            type="hidden"
                                            name="btAction"
                                            value="addProductByAdmin"
                                            />
                                        <input
                                            type="button"
                                            class="btn btn-default"
                                            data-dismiss="modal"
                                            value="Cancel"
                                            />
                                        <input type="submit" class="btn btn-success" value="Add" />
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <!-- End Add Modal -->

                    <!-- Delete One Modal -->
                    <div id="deleteProductModal" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <form action="deleteProduct">
                                    <div class="modal-header">
                                        <h4 class="modal-title">Delete Product</h4>
                                        <button
                                            type="button"
                                            class="close"
                                            data-dismiss="modal"
                                            aria-hidden="true"
                                            >
                                            &times;
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <p>Are you sure you want to delete these Records?</p>
                                        <p class="text-warning">
                                            <small>This action cannot be undone.</small>
                                        </p>
                                    </div>
                                    <div class="modal-footer">
                                        <input
                                            type="button"
                                            class="btn btn-default"
                                            data-dismiss="modal"
                                            value="Cancel"
                                            />
                                        <input type="submit" class="btn btn-danger" value="Delete" />
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <!-- End Delete Modal -->
                </div>
            </div>
        </body>
    </html>
