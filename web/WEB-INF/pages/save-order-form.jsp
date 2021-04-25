<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Purchase</title>
        <link rel="stylesheet" href="resources/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="resources/css/main.css"/>
    </head>
    <body>
        <%@include file="../jspf/navigation.jspf" %>
        <c:set var="cart" value="${sessionScope.CART}" />
        <div class="container page-content">
            <div class="py-5 text-center">
                <h2>Order Information</h2>
            </div>
            <div class="row g-5">
                <div class="col-md-5 col-lg-4 order-md-last">
                    <h4 class="d-flex justify-content-between align-items-center mb-3">
                        <span class="text-primary">Products</span>
                        <span class="badge bg-primary rounded-pill">${fn:length(cart)}</span>
                    </h4>
                    <ul class="list-group mb-3">
                        <c:forEach items="${cart}" var="cartItem">
                            <li class="list-group-item d-flex justify-content-between lh-sm">
                                <div>
                                    <h6 class="my-0">${cartItem.car.name}</h6>
                                </div>
                                <span class="text-muted">
                                    <fmt:formatNumber value="${cartItem.price}" type="number" />
                                </span>
                            </li>                            
                        </c:forEach>

                        <li class="list-group-item d-flex justify-content-between">
                            <span>Total (VNĐ)</span>
                            <strong><fmt:formatNumber value="${sessionScope.TOTAL_PRICE}" type="number" /></strong>
                        </li>
                    </ul>
                </div>
                <div class="col-md-7 col-lg-8">
                    <h4 class="mb-3">Customer Information</h4>
                    <form action="saveOrder" method="POST" 
                          class="needs-validation">
                        <div class="row g-3">
                            <div class="col-12 mt-3">
                                <label for="fullname" class="form-label">Fullname: </label>
                                <input type="text" class="form-control" name="fullname" id="fullname" required>
                                <div class="invalid-feedback">
                                    Please input full name
                                </div>
                            </div>

                            <div class="col-12 mt-3">
                                <label for="email" class="form-label">Email</label>
                                <input type="email" class="form-control" id="email" name="email" required>
                                <div class="invalid-feedback">
                                    Please input valid email address
                                </div>
                            </div>

                            <div class="col-12 mt-3">
                                <label for="address" class="form-label">Address</label>
                                <input type="text" class="form-control" 
                                       id="address" name="address" required>
                                <div class="invalid-feedback">
                                    Please input address
                                </div>
                            </div>
                            
                            <div class="col-12 mt-3">
                                <label for="phone" class="form-label">Phone</label>
                                <input type="tel" class="form-control" 
                                       id="phone" name="phone" required>
                                <div class="invalid-feedback">
                                    Please input telephone number
                                </div>
                            </div>
                            
                            <div class="col-12 mt-3">
                                <label for="rental_date" class="form-label">Rental date</label>
                                <input type="date" class="form-control" 
                                       id="rental_date" name="rental_date" required/>
                            </div>
                            
                            <div class="col-12 mt-3">
                                <label for="return_date" class="form-label">Return date</label>
                                <input type="date" class="form-control" 
                                       id="return_date" name="return_date" required/>
                            </div>
                            
                            <input type="hidden" name="price" value="${sessionScope.TOTAL_PRICE}"/>
                        </div>
                        
                        <hr class="my-4">

                        <h4 class="mb-3">Payment</h4>

                        <div class="my-3">
                            <div class="form-check">
                                <input id="cash" name="payment" type="radio" 
                                       value="1" class="form-check-input" checked required>
                                <label class="form-check-label" for="cash">Thanh toán bằng tiền mặt khi nhận hàng</label>
                            </div>
                        </div>

                        <hr class="my-4">
                        <button class="w-100 btn btn-success" type="submit">Order</button>
                        <hr class="my-4">
                    </form>
                </div>   
            </div>
        </div>
    </body>
    <script src="resources/js/jquery-3.6.0.min.js"></script>
    <script src="resources/js/bootstrap.min.js" /></script>
</html>
