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
                </div>
            </c:forEach>
        </div>
    </body>
    <script src="resources/js/jquery-3.6.0.min.js"></script>
    <script src="resources/js/bootstrap.min.js" /></script>
</html>
