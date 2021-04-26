<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order</title>
        <link rel="stylesheet" href="resources/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="resources/css/main.css"/>
    </head>
    <body>
        <%@include file="../jspf/navigation.jspf" %>
        <c:set var="order" value="${requestScope.ORDER}"/>
        <div class="container page-content">
            <div class="card mt-3">
                <div class="card-header">
                    <h5 class="card-title">Order No ${order.id}</h5>
                </div>
                <div class="card-body">
                    <p class="card-text">Receiver: ${order.fullname}</p>
                    <p class="card-text">Address: ${order.address}</p>
                    <p class="card-text">Phone: ${order.phone}</p>
                    <p class="card-text">Discount: <fmt:formatNumber type="percent" value="${order.discount.discount}" /></p>
                    <c:if test="${order.status == true}">
                        <p class="text-primary card-text">In progress</p>
                    </c:if>
                    <c:if test="${order.status == false}">
                        <p class="text-danger card-text">Cancelled</p>
                    </c:if>
                    <p class="text-muted">Date: ${order.createDate}</p>
                    <h5>Price: <fmt:formatNumber type="number" value="${order.price}" /> VNĐ</h5>
                </div>
            </div>
            <hr class="mt-3 mb-3" />
            <c:forEach items="${order.orderDetails}" var="item">
                <div class="row p-2 m-2 bg-white border rounded">
                    <div class="col-md-3 mt-1">
                        <img class="img-fluid img-responsive rounded product-image" 
                             src="/files/car-rental/${item.car.image}">
                    </div>
                    <div class="col-md-6 mt-1">
                        <h5 class="display-5">${item.car.name}</h5>
                        <div class="mt-1 mb-1 spec-1">
                            <b>Branch</b>: ${item.car.branch}
                        </div>
                        <div class="mt-1 mb-1 spec-1">
                            <b>Year</b>: ${item.car.year}
                        </div>
                        <div class="mt-1 mb-1 spec-1 text-muted">
                            Color: ${item.car.color}<br>
                        </div>
                        <div class="mt-1 mb-1 spec-1 text-muted">
                            Quantity ${item.quantity}<br>
                        </div>
                        <div class="mt-1 mb-1 spec-1">
                            Price <b><fmt:formatNumber type="number" value="${item.quantity * item.car.price}" /> VNĐ</b>
                        </div>
                    </div>
                    <c:if test="${order.status == true}">
                        <div class="col-md-12 mt-1">
                            <form action="updateRating" method="POST">
                                <input type="hidden" name="id" value="${order.id}" />
                                <input type="hidden" name="detail_id" value="${item.id}" />
                                <label for="rating">Rating: </label>
                                <input type="number" min="0" max="10" step="1" 
                                       name="rating" id="rating" required
                                       value="${item.rating}"/>
                                <input type="submit" class="btn btn-outline-primary" value="Feedback" />
                            </form>
                        </div>                        
                    </c:if>
                </div>
            </c:forEach>
            <c:if test="${order.status == true}">
                <form action="cancelOrder" method="POST" 
                      onsubmit="return confirm('Do you want to cancel the order?')">
                    <input type="hidden" name="id" value="${order.id}" />
                    <input type="submit" value="Cancel" class="btn btn-danger" />
                </form>
            </c:if>
        </div>
    </body>
    <script src="resources/js/jquery-3.6.0.min.js"></script>
    <script src="resources/js/bootstrap.min.js" /></script>
</html>
