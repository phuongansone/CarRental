<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History</title>
        <link rel="stylesheet" href="resources/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="resources/css/main.css"/>
    </head>
    <body>
        <%@include file="../jspf/navigation.jspf" %>
        
        <div class="container page-content">
            <form action="orderHistory" method="GET">
                <div class="input-group mt-2 mb-2">
                    <div class="input-group-prepend">
                        <label class="input-group-text width-100px" 
                               for="keyword">
                            Receiver
                        </label>
                    </div>
                    <input type="text" class="form-control"
                           id="keyword" name="keyword" value="${param.keyword}"/>
                </div>
                <div class="input-group mt-2 mb-2">
                    <div class="input-group-prepend">
                        <label class="input-group-text width-100px" 
                               for="date">
                            Date
                        </label>
                    </div>
                    <input type="date" class="form-control"
                           id="date" name="date" value="${param.date}"/>
                </div>
                <input type="submit" class="btn btn-secondary" value="Search"/>
            </form>
            
            <c:forEach items="${requestScope.ORDERS}" var="order">
                <div class="card mt-3">
                    <div class="card-header">
                        <h5 class="card-title">Order No ${order.id}</h5>
                    </div>
                    <div class="card-body">
                        <p class="card-text">Receiver: ${order.fullname}</p>
                        <p class="card-text">Address: ${order.address}</p>
                        <p class="card-text">Phone: ${order.phone}</p>
                        <p class="card-text">Rental date: ${order.rentalDate}</p>
                        <p class="card-text">Return date: ${order.returnDate}</p>
                        <c:if test="${order.status == true}">
                            <p class="text-success card-text">In progress</p>
                        </c:if>
                        <c:if test="${order.status == false}">
                            <p class="text-danger card-text">Cancelled</p>
                        </c:if>
                        <p class="text-muted">Date: ${order.createDate}</p>
                        <h5>Price: <fmt:formatNumber type="number" value="${order.price}" /> VNĐ</h5>
                    </div>
                        <a href="order?id=${order.id}" class="btn btn-primary m-3">View detail</a>
                </div>
            </c:forEach>
        </div>
        
    </body>
    <script src="resources/js/jquery-3.6.0.min.js"></script>
    <script src="resources/js/bootstrap.min.js" /></script>
    <script>
            $('#keyword').on('change', function() {
                $('#date').val(undefined);
            });

            $('#date').on('change', function() {
                $('#keyword').val(undefined);
            });
    </script>
</html>
