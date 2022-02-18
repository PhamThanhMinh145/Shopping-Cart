<%@page import="java.util.List"%>
<%@page import="entity.Order"%>
<%@page import="dao.OrderDao"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entity.Cart"%>
<%@page import="entity.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="connection.DbConn"%>
<%@page import="entity.User"%>

<%
     DecimalFormat dcf = new DecimalFormat("#.##");
   request.setAttribute("dcf", dcf);
    
   User auth= (User) request.getSession().getAttribute("auth");
   List<Order> orders =null;
   if(auth != null){
       request.setAttribute("auth", auth);
       OrderDao orderDao =  new OrderDao(DbConn.getConnection());
       orders = orderDao.userOrders(auth.getId());   
       
   }else {
       response.sendRedirect("login.jsp");
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
        <title>Orders</title>

        <%@include file="includes/head.jsp"%>
    </head>
    <body>
       <%@include file="includes/navbar.jsp"%>
        
       <div class="container">
           <div class="card-header my-3">All Order</div>
               <table class="table table-light">
                   <thead>
                       <tr>
                           <th class="col">Date</th>
                           <th class="col">Name</th>
                           <th class="col">Category</th>
                           <th class="col">Quatity</th>
                           <th class="col">Price</th>
                           <th class="col">Cancel</th>
                       </tr>
                   </thead>
                   <tbody>
                       <%
                           if(orders != null){
                               for (Order o : orders) {
                                       %>
                                       <tr>
                                            
                                            <td><%= o.getDate() %></td>
                                            <td><%= o.getName()%></td>
                                            <td><%= o.getCategory() %></td>
                                            <td><%= o.getQuatity() %></td> 
                                            <td><%= dcf.format(o.getPrice()) %></td>
                                            <td><a class="btn btn-sm btn-danger" href="cancel-order?id=<%=o.getOrderId()%>">Cancel</a></td>
                                       </tr>
                                       
                                       <%
                                   }
                                   
                               }
                           
                       %>
                   </tbody>
               </table>

       </div>
        
       <%@include file="includes/footer.jsp"%>
    </body>
</html>
