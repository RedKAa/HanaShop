<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<nav aria-label="Page navigation">
            <ul class="pagination justify-content-center">
                <c:if test="${requestScope.endPage > 0}">
                    <c:forEach var="page" begin="1" end="${requestScope.endPage}">
                        <c:url var="urlRewriting" value="ServletDispatcher">
                            <c:param name="btAction" value="${requestScope.action}"/>
                            <c:param name="pageIndex" value="${page}"/>
                            <c:if test="${not empty requestScope.cateID}">
                                <c:param name="cid" value="${requestScope.cateID}"/>
                            </c:if>
                            <c:if test="${not empty requestScope.txtSearch}">
                                <c:param name="txtSearch" value="${requestScope.txtSearch}"/>
                            </c:if>
                            <c:if test="${not empty requestScope.minPrice}">
                                <c:param name="minPrice" value="${requestScope.minPrice}"/>
                            </c:if>
                            <c:if test="${not empty requestScope.maxPrice}">
                                <c:param name="maxPrice" value="${requestScope.maxPrice}"/>
                            </c:if>
                        </c:url>
                        <c:if test="${page == requestScope.pageIndex}">
                            <li class="page-item active" style="z-index: -1">
                                <a class="page-link" href="${urlRewriting}">${page}</a>
                            </li>
                        </c:if>
                        <c:if test="${page != requestScope.pageIndex}">
                            <li class="page-item"><a class="page-link" href="${urlRewriting}">${page}</a></li>
                            </c:if>
                        </c:forEach>
                    </c:if>
            </ul>
        </nav>