<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row ">
    <div class="col-md-4, col-sm-4, col-xs-4 ">
        <div class="form-inline">
            <div class="form-group">
                <form  action="ServletDispatcher" id="price">
                    <c:if test="${not empty requestScope.ERROR}">
                        <p> ${requestScope.ERROR} </p>
                    </c:if>
                    <div class="input-group input-group-sm">
                        <div class="input-group-append">
                            <input
                                type="number"
                                min="10000"
                                name="minPrice"
                                value="${param.minPrice}"
                                maxlength="12"
                                size="12"
                                placeholder="Min Price..."
                                />
                            <button type="submit" class="btn btn-secondary btn-number" name="btAction" value="searchByPrice">
                                <i class="fa fa-filter"></i>
                            </button>
                            <input
                                type="number"
                                min="10000"
                                name="maxPrice"
                                value="${param.maxPrice}"
                                maxlength="12"
                                size="12"
                                placeholder="Max Price..."
                                />
                        </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- end form Search Price -->
