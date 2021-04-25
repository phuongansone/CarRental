<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="resources/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="resources/css/main.css"/>
    </head>
    <body>
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <c:if test="${sessionScope.REGISTERED == true}">
                        <div class="alert alert-success" role="alert">
                            You registered successfully!
                        </div>
                    </c:if>
                    <c:if test="${sessionScope.LOGGED_IN == false}">
                        <div class="alert alert-danger" role="alert">
                            Email or password is invalid. Please try again.
                        </div>
                    </c:if>
                    <c:if test="${sessionScope.LOGGED_OUT == true}">
                        <div class="alert alert-success" role="alert">
                            Goodbye. See you again
                        </div>
                    </c:if>
                    <c:remove var="REGISTERED" scope="session" />
                    <c:remove var="LOGGED_IN" scope="session" />
                    <c:remove var="LOGGED_OUT" scope="session" />
                    
                    <div class="card">
                        <header class="card-header">
                            <h4 class="card-title mt-2">Log in</h4>
                        </header>
                        <article class="card-body">
                            <form method="POST" action="login" class="form-signin">
                                <div class="form-group">
                                    <label for="email">Email address</label>
                                    <input type="email" class="form-control" id="email" 
                                           name="email" placeholder="Enter email" 
                                           value="${param.email}"
                                           autocomplete="off" required>
                                </div>
                                <div class="form-group">
                                    <label for="password">Password</label>
                                    <input type="password" class="form-control" 
                                           id="password" name="password" 
                                           placeholder="Enter password" required>
                                </div>
                                <div class="form-group">
                                    <button type="submit" 
                                            class="btn btn-secondary btn-block">
                                        Log In
                                    </button>
                                </div>
                            </form>                
                        </article>
                        <div class="border-top card-body text-center">
                            Do not have an account? <a href="register">Register</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script src="resources/js/jquery-3.6.0.min.js"></script>
    <script src="resources/js/bootstrap.min.js" /></script>
</html>
