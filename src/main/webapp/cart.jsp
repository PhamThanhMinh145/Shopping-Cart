<%@page import="java.text.DecimalFormat"%>
<%@page import="dao.ProductDao"%>
<%@page import="java.util.*"%>
<%@page import="entity.Cart"%>
<%@page import="connection.DbConn"%>
<%@page import="entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

 
<%
    // có thể đưa vào ViewCartServlet
   DecimalFormat dcf = new DecimalFormat("#.##");
   request.setAttribute("dcf", dcf);
   
     
   User auth= (User) request.getSession().getAttribute("auth");
   if(auth != null){
       request.setAttribute("auth", auth);
   }
   
    
   ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
   List<Cart> cartProduct = null;
   if(cart_list != null){
       ProductDao pdao = new ProductDao(DbConn.getConnection());
       cartProduct = pdao.getCartProducts(cart_list);
       double total =   pdao.getTotalCartPrice(cart_list);
       request.setAttribute("cart_list", cart_list);
       request.setAttribute("total", total);
   }

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>

        <%@include file="includes/head.jsp"%>
        <style type="text/css">
            .table tbody td{
                vertical-align: middle;
            }

            .btn-incre, .btn-decre{
                box-shadow: none;
                font-size: 25px;
                color: chartreuse;
                
            }
        </style>
    </head>
    <body>
       <%@include file="includes/navbar.jsp"%>
        
       <div class="container">
           <div class="d-flex py-3">
               <!--nếu có item thì total có số không có item total =0-->
               <h3>Total Price: $ ${(total>0) ? dcf.format(total):0}</h3>
            <a href="cart-checkout" class="mx-3 btn btn-primary">Check Out</a>
            </div>

            <table class="table table-loght">
                <thead>
                    <tr>
                        <th scope="col">Name</th>
                        <th scope="col">Category</th>
                        <th scope="col">Price</th>
                        <th scope="col">Buy Now</th>
                        <th scope="col">Cancel</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        if(cart_list != null){
                            for(Cart c: cartProduct){
                                %> 
                        <tr>
                        <td><%=c.getName() %></td>                        
                        <td><%=c.getCategory() %></td>
                        <td><%= dcf.format(c.getPrice()) %> $</td>
                        <td>
                            <form action="order-now" method="POST" class="form-inline">
                                <input type="hidden" name="id" value="1" class="form-input">
                                <div class="form-group d-flex justify-content-left">
                                    <a href="quatity-inc-dec?action=dec&id=<%= c.getId() %>" class="btn btn-sm btn-decre"><i class="fas fa-minus-square"></i></a>
                                    <input style="width: 50%; " type="text" name="quatity" class="form-control" value="<%= c.getQuatity() %>" readonly>
                                    <a href="quatity-inc-dec?action=inc&id=<%=c.getId()%>" class="btn btn-sm btn-incre"><i class="fas fa-plus-square"></i></a>
                                    <button type="submit"class="btn btn-primary btn-sm">Buy</button>
                                </div>
                                
                                
                            </form>
                        </td>
                        <td>
                            <a href="remove-from-cart?id=<%= c.getId() %> " class="btn btn-sm btn-danger">Cancel</a>
                        </td>
                    </tr>
                    
                            <%
                            }// end foreach
                        } // end if
                    %>
                   
                </tbody>
            </table>
       </div> 

       <%@include file="includes/footer.jsp"%>
    </body>
</html>
