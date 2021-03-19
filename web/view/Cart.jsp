   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@page contentType="text/html" pageEncoding="UTF-8" %> 
  <!DOCTYPE html>
  <html>

  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Your Cart</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
      integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
  </head>

  <body>
    <jsp:include page="Menu.jsp"></jsp:include>
    <div class="shopping-cart">
      <div class="px-4 px-lg-0">

        <div class="pb-5">
          <div class="container">
            <div class="row">
              <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">
                <c:if test="${empty requestScope.cart}">
                  <h4>Empty!</h4>
                </c:if>
                <!-- Shopping cart table -->
                <c:if test="${not empty requestScope.cart}">
                  <div class="table-responsive">
                    <table class="table">
                      <thead>
                        <tr>
                          <th scope="col" class="border-0 bg-light">
                            <div class="p-2 px-3 text-uppercase">Sản Phẩm</div>
                          </th>
                          <th scope="col" class="border-0 bg-light">
                            <div class="py-2 text-uppercase">Đơn Giá</div>
                          </th>
                          <th scope="col" class="border-0 bg-light">
                            <div class="py-2 text-uppercase">Số Lượng</div>
                          </th>
                          <th scope="col" class="border-0 bg-light">
                            <div class="py-2 text-uppercase">Xóa</div>
                          </th>
                        </tr>
                      </thead>
                      <tbody>

                        <c:forEach items="${requestScope.cart}" var="item">
                          <tr>
                            <th scope="row">
                              <div class="p-2">
                                <img src="${item.key.image}" alt="" width="70" class="img-fluid rounded shadow-sm">
                                <div class="ml-3 d-inline-block align-middle">
                                  <h5 class="mb-0"> <a href="ServletDispatcher?btAction=viewProduct&pid=${item.key.id}"
                                      class="text-dark d-inline-block">${item.key.name}</a></h5><span
                                    class="text-muted font-weight-normal font-italic"></span>
                                </div>
                              </div>
                            </th>
                            <td class="align-middle"><strong>${item.key.price}</strong></td>
                            <td class="align-middle">
                              <a href="cart?action=update cart&pid=${item.key.id}&updateQuantity=decrease"><button
                                  class="btnSub">-</button></a>
                              <strong>${item.value}</strong>
                              <a href="cart?action=update cart&pid=${item.key.id}&updateQuantity=increase"><button
                                  class="btnAdd">+</button></a>
                            </td>
                            <td class="align-middle"><a href="cart?action=delete item&pid=${item.key.id}"
                                class="text-dark">
                                <button type="button" class="btn btn-danger">Delete</button>
                              </a>
                            </td>
                          </tr>
                        </c:forEach>
                      </tbody>
                    </table>
                  </div>
                </c:if>
                <!-- End -->
              </div>
            </div>

            <div class="row py-5 p-4 bg-white rounded shadow-sm">
              <div class="col-lg-6">
                <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Mã khuyến mãi</div>
                <div class="p-4">
                  <div class="input-group mb-4 border rounded-pill p-2">
                    <input type="text" placeholder="Nhập mã..." aria-describedby="button-addon3"
                      class="form-control border-0">
                    <div class="input-group-append border-0">
                      <button id="button-addon3" type="button" class="btn btn-dark px-4 rounded-pill"><i
                          class="fa fa-gift mr-2"></i>Sử dụng</button>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-lg-6">
                <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Thành tiền</div>
                <div class="p-4">
                  <ul class="list-unstyled mb-4">
                    <li class="d-flex justify-content-between py-3 border-bottom currency"><strong
                        class="text-muted">Tổng tiền hàng</strong><strong>${requestScope.total} VND</strong></li>
                    <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Phí vận
                        chuyển</strong><strong>Free ship</strong></li>
                    <li class="d-flex justify-content-between py-3 border-bottom curency"><strong
                        class="text-muted">VAT</strong><strong>${requestScope.total/10} VND</strong></li>
                    <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Tổng
                        thanh toán</strong>
                      <h5 class="currency font-weight-bold ">${requestScope.total*1.1} VND</h5>
                    </li>
                  </ul><a href="buy" class="btn btn-dark rounded-pill py-2 btn-block">Mua hàng</a>
                </div>
              </div>
            </div>

          </div>
        </div>
      </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
      integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
      crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
      integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
      crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
      integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
      crossorigin="anonymous"></script>
  </body>

  </html>