<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Car</title>
        <link rel="stylesheet" href="resources/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="resources/css/main.css"/>
    </head>
    <body>
        <c:set var="user" value="${sessionScope.USER}" />
        <c:if test="${sessionScope.LOGGED_IN == true}">
            <div class="alert alert-success" role="alert">
                Welcome, ${user.name}
            </div>
        </c:if>
        <c:remove var="LOGGED_IN" scope="session" />
        
        <c:if test="${sessionScope.ADD_TO_CART == true}">
            <div class="alert alert-success" role="alert">
                Car is added to cart.
            </div>
        </c:if>
        <c:if test="${sessionScope.ADD_TO_CART == false}">
            <div class="alert alert-danger" role="alert">
                Something is wrong. Please try again.
            </div>
        </c:if>
        <c:remove var="ADD_TO_CART" scope="session" />
        
        <c:if test="${sessionScope.SAVE_ORDER == true}">
            <div class="alert alert-success" role="alert">
                Order successfully!
            </div>
        </c:if>
        <c:remove var="SAVE_ORDER" scope="session" />
        
        <%@include file="../jspf/navigation.jspf" %>
        
        <div class="container page-content">
            <h1>Search car</h1>
            <form action="searchCar" method="GET">
                <div class="input-group mt-2 mb-2">
                    <div class="input-group-prepend">
                        <label class="input-group-text width-100px" 
                               for="keyword">
                            Keyword
                        </label>
                    </div>
                    <input type="text" class="form-control" 
                           placeholder="Name contains..." 
                           id="keyword" name="keyword"
                           value="${param.keyword}"/>
                </div>
                
                <div class="input-group mt-2 mb-2">
                    <div class="input-group-prepend">
                        <label class="input-group-text width-100px" 
                               for="category_id">
                            Category
                        </label>
                    </div>
                    <select name="category_id" id="category_id" 
                            class="form-control">
                        <option value="">Select category...</option>
                        <c:forEach var="category" items="${requestScope.CATEGORIES}">
                            <option value="${category.id}" 
                                    ${param.category_id == category.id ? 'selected' : ''}>
                                ${category.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                
                <input type="submit" class="btn btn-outline-secondary" value="Search" />
            </form>
            <div class="d-flex justify-content-center row">
                <div class="col-md-10">
                    <c:forEach items="${requestScope.CARS}" var="car" >
                        <div class="row p-2 m-2 bg-white border rounded">
                            <div class="col-md-3 mt-1">
                                <img class="img-fluid img-responsive rounded product-image" 
                                     src="/files/car-rental/${car.image}">
                            </div>
                            <div class="col-md-6 mt-1">
                                <h5 class="display-5">${car.name}</h5>
                                <div class="mt-1 mb-1 spec-1">
                                    <b>Branch</b>: ${car.branch}
                                </div>
                                <div class="mt-1 mb-1 spec-1">
                                    <b>Category</b>: ${car.category.name}
                                </div>
                                <div class="mt-1 mb-1 spec-1">
                                    <b>Year</b>: ${car.year}
                                </div>
                                <div class="mt-1 mb-1 spec-1 text-muted">
                                    Color: ${car.color}<br>
                                    In stock: ${car.quantity} cars
                                </div>
                            </div>
                            <div class=" col-md-3 border-left mt-1 
                                 align-items-center align-content-center">
                                <div class="d-flex flex-row align-items-center">
                                    <h5 class="mr-1">
                                        <fmt:formatNumber type="number" value="${car.price}" /> VNĐ / day
                                    </h5>
                                </div>
                                <c:if test="${user.role.id != Role.ADMIN.roleId}">
                                    <form action="addToCart" method="POST">
                                        <div class="d-flex flex-column mt-4">
                                            <div class="mb-1">
                                                <input type="hidden" name="car_id" 
                                                       value="${car.id}" />
                                                <input type="number" name="quantity" 
                                                       value="1" step="1"
                                                       min="1"
                                                       class="form-control"/>
                                            </div>
                                            <div class="mt-1">
                                                <button class="btn btn-primary btn-sm btn-block"  
                                                        type="submit" class="form-control">
                                                    Add to cart
                                                </button>
                                            </div>
                                        </div>
                                    </form>
                                </c:if>
                            </div>
                        </div>
                    </c:forEach>
                </div> 
            </div>
                
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <c:forEach items="${requestScope.PAGES}" var="pageNo">
                        <li class="page-item ${pageNo == requestScope.PAGE ? 'active' : ''}">
                            <a class="page-link" 
                               href="searchCar?page=${pageNo}&keyword=${param.keyword}&category_id=${param.category_id}">
                                ${pageNo}
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </nav>
        </div>
    </body>
    <script src="resources/js/jquery-3.6.0.min.js"></script>
    <script src="resources/js/bootstrap.min.js" /></script>
    <script>
        // selector 
        var KEYWORD = '#keyword';
        var CATEGORY_ID = '#category_id';
        
        $(KEYWORD).on('click', function() {
            $(CATEGORY_ID).val('');
        });
        
        $(CATEGORY_ID).on('click', function() {
            $(KEYWORD).val('');
        });
    </script>
</html>
