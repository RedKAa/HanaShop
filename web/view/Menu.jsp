<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--begin of menu-->
<nav class="navbar fixed-top navbar-expand-md navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/home">HanaShop</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
            <ul class="navbar-nav m-auto">
                <c:if test="${sessionScope.user.checkAdmin()}">
                    <li class="nav-item">
                        <a class="nav-link" href="ServletDispatcher?btAction=manager">Manager Product</a>
                    </li>
                </c:if>
                <li class="nav-item">
                    <a class="nav-link" href="#">Hello, ${user.fullname}</a>
                </li>
                <c:if test="${sessionScope.user != null}">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/ServletDispatcher?btAction=logout">Logout</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.user == null}">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/ServletDispatcher?btAction=login&var=1">Login</a>
                    </li>
                </c:if>
            </ul>

            <!--Search By Name Form-->
            <form action="ServletDispatcher" method="get" class="form-inline my-2 my-lg-0">
                <div class="input-group input-group-sm">
                    <input name="txtSearch" type="text" value="${txtSearch}" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Enter a name...">
                    <div class="input-group-append">
                        <button type="submit" class="btn btn-secondary btn-number" name="btAction" value="searchByName">
                            <i class="fa fa-search"></i>
                        </button>
                    </div>
                </div>
                <a class="btn btn-success btn-sm ml-3" href="cart?action=view cart">
                    <i class="fa fa-shopping-cart"></i> Cart
                    <c:if test="${not empty sessionScope.cart}">
                        <span class="badge badge-light">${sessionScope.cart.numOfItems}</span>
                    </c:if>
                    <c:if test="${empty sessionScope.cart}">
                        <span class="badge badge-light">0</span>
                    </c:if>
                </a>
            </form>
            <!-- End Search By Name Form-->

        </div>
    </div>
</nav>
<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading">Hana vua ăn vặt</h1>
        <p class="lead text-muted mb-0" >Cung cấp mọi loại ĐỒ</p>
    </div>
</section>
<!--end of menu-->
