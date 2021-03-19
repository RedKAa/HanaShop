<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link href="css/priceFilter.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="Menu.jsp"></jsp:include>
            <div class="container">
                <!-- search Price -->
            <jsp:include page="searchByPrice.jsp"></jsp:include>
            </div>
        </div>


        <div class="container">
            <div class="row">
            <jsp:include page="Left.jsp"></jsp:include>

                <!------ Display product ---------->
                <div class="col-sm-9">
                    <div class="row">
                    <c:if test="${empty Products}">
                        <h4>No product was found!</h4>
                    </c:if>
                    <c:forEach items="${Products}" var="p">
                        <div class="col-12 col-md-6 col-lg-4">
                            <div class="card bg-light" style="margin-bottom: 20px">
                                <img class="card-img-top" src="${p.image}" alt="${p.name}" style="height: 250px">
                                <div class="card-body">
                                    <h4 class="card-title show_txt"><a href="ServletDispatcher?btAction=viewProduct&pid=${p.id}" title="View Product">${p.name}</a></h4>
                                    <p class=" card-text show_txt">${p.description}
                                    </p>
                                    <div class="row">
                                        <div class="col">
                                            <!--<p class="btn btn-danger btn-block">${p.price} VND</p>-->
                                            <p class="price">PRICE: ${p.price} VND</p>
                                        </div>
                                        <div class="col">
<!--                                            <a href="cart?action=add to cart&pid=${p.id}" class="btn btn-success btn-block">Add to cart</a>-->
                                            <a href="cart?action=add to cart&pid=${p.id}" class="btn btn-lg btn-outline-primary text-uppercase"> <i class="fa fa-cart-plus"></i> Add to cart </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>

    <!-- Paging -->
    <div style="margin-top: 80px"></div>

    <jsp:include page="Paging.jsp"></jsp:include>


        <!-- end paging -->
    <jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>

