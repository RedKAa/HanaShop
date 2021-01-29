

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-sm-3 fixed-left">
    
    <!--LOAD CATEGORY-->
    <div class="card bg-light mb-3">
        <div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i> Categories</div>
        <ul class="list-group category_block">
            <c:forEach items="${Category}" var="c">
                <c:url var="urlRewriting" value="ServletDispatcher">
                    <c:param name="btAction" value="searchByCategory"/>
                    <c:param name="cid" value="${c.id}"/>
                </c:url>
                <li class="list-group-item ${cateID == c.id ? "active":""}"><a href="${urlRewriting}">${c.name}</a></li>
                </c:forEach>

        </ul>
    </div>
    <!--END LOAD CATEGORY-->
    
    <!--BEST SELLER-->
    <div class="card bg-light mb-3">
        <c:if test="${not empty top}">
            <div class="card-header bg-success text-white text-uppercase">BEST SELLER THIS MONTH</div>
            <div class="card-body">
                <img class="img-fluid" src="${top.image}" />
                <h5 class="card-title"><a href="viewProduct?pid=${top.id}" title="View Product">${top.name}</a></h5>
                <p class="card-text">${top.description}</p>
                <p class="bloc_left_price">${top.price} VND</p>
            </div>
        </c:if>
    </div>
    <!--END BEST SELLER-->


</div>