<%@page import="java.util.ArrayList"%>
<%@page import="entity.Cart"%>
<%@page import="connection.DbConn"%>
<%@page import="entity.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
   User auth= (User) request.getSession().getAttribute("auth");
   if(auth != null){
       response.sendRedirect("index.jsp");
   }
   
ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
   
   if(cart_list != null){
       
       request.setAttribute("cart_list", cart_list);
   }
   
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>

        <%@include file="includes/head.jsp"%>
    </head>
    <body>
        <%@include file="includes/navbar.jsp"%>
        
        <div class="container">
            <div class="card w-50 mx-auto my-5">
                <div class="card-header text-center">User Login</div>
                <div class="card-body">
                    <form action="user-login" method="POST">
                        <div class="form-group">
                            <label for="1">Email Address</label>
                            <input value="${email}"  id="1" class="form-control" type="email" name="login-email" placeholder="Enter Your Email" required>

                        </div>

                        <div class="form-group">
                            <label for="2">Password</label>
                            <input  value="${pass}" id="2" class="form-control" type="password" name="login-password" placeholder="********" required>

                        </div>

                        <div style="margin-top: 20px" class="text-center">
                        <button type="submit" class="btn btn-primary">Login</button>
                    </div>

                    </form>
                </div>
            </div> 
        </div>
        
        
       <%@include file="includes/footer.jsp"%>
    </body>
</html>
