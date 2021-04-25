<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View cart</title>
        <link rel="stylesheet" href="resources/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="resources/css/main.css"/>
    </head>
    <body>
        <%@include file="../jspf/navigation.jspf" %>
        <c:if test="${sessionScope.UPDATED == true}">
            <div class="alert alert-success" role="alert">
                Updated
            </div>
        </c:if>
        <c:if test="${sessionScope.UPDATED == false}">
            <div class="alert alert-danger" role="alert">
                Something is wrong. Please try again.
            </div>
        </c:if>
        <c:remove var="UPDATED" scope="session" />
        <div class="container page-content">
            <h1>Cart</h1>
            <div class="d-flex justify-content-center row">
                <div class="col-md-10">
                    <c:forEach items="${sessionScope.CART}" var="cartItem">
                        <div class="row p-2 m-2 bg-white border rounded">
                            <div class="col-md-3 mt-1">
                                <img class="img-fluid img-responsive rounded product-image" 
                                     src="/files/car-rental/${cartItem.car.image}">
                            </div>
                            <div class="col-md-7 mt-1">
                                    <h5>${cartItem.car.name}</h5>
                                    <div class="mt-1 mb-1 spec-1">
                                        <b>Type</b>: ${cartItem.car.category.name}
                                    </div>
                                    <div class="mt-1 mb-1 spec-1">
                                        <b>Price</b>: <fmt:formatNumber value="${cartItem.car.price}" type="number" />
                                    </div>
                                    <div class="mt-1 mb-1 spec-1">
                                        <b>Total</b>: <fmt:formatNumber value="${cartItem.price}" type="number" />
                                    </div>
                                    <c:if test="${cartItem.outOfStock 
                                                  and cartItem.quantity > cartItem.car.quantity}">
                                        <div class="alert alert-warning" role="alert">
                                            There is only ${cartItem.car.quantity} cars remained. Please choose again.
                                        </div>                                             
                                    </c:if>

                                    <div class="d-flex justify-content-between">
                                        <!-- Update form -->
                                        <form class="d-flex justify-content-between" action="updateCart" method="POST">
                                            <input type="hidden" name="car_id"
                                                   value="${cartItem.car.id}"/>
                                            <input type="number" step="1" 
                                                   name="quantity"
                                                   value="${cartItem.quantity}"
                                                   min="1"
                                                   class="form-control mr-2"/>
                                            <button type="submit" class="btn btn-outline-success form-control mr-2">Update</button>
                                        </form>
                                        <!-- Delete form -->
                                        <form action="removeFromCart" method="POST" 
                                              onsubmit="return confirm('Selected car will be removed. Do you want to continue?')">
                                            <input type="hidden" name="car_id"
                                                   value="${cartItem.car.id}"/>
                                            <button type="submit" class="btn btn-outline-danger">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                                                <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"></path>
                                                <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"></path>
                                                </svg>
                                                Delete
                                            </button>
                                        </form>
                                    </div>
                            </div>
                        </div>
                    </c:forEach>
                    <hr class="mb-4">
                    <div class="d-flex justify-content-between">
                        <div>
                            <h4>Total Price</h4>
                        </div>
                        <div>
                            <h3><fmt:formatNumber value="${sessionScope.TOTAL_PRICE}" type="number" /> <small class="text-muted">VNĐ</small></h3>
                        </div>
                    </div>
                    <div class="mb-4">
                        <a href="saveOrder" 
                           class="btn btn-success form-control">
                            Order
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
