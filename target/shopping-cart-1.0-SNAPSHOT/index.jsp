
<%@page import="entity.Cart"%>
<%@page import="java.util.*"%>
<%@page import="entity.Product"%>
<%@page import="dao.ProductDao"%>
<%@page import="entity.User"%>
<%@page import="connection.DbConn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // có thể đưa vào HomeControl
    User auth = (User) request.getSession().getAttribute("auth");
    if (auth != null) {
        request.setAttribute("auth", auth);
    }

    ProductDao pd = new ProductDao(DbConn.getConnection());
    List<Product> products = pd.getAllProduct();
    
 ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
   
   if(cart_list != null){
       
       request.setAttribute("cart_list", cart_list);
   }


%>



<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to shopping cart</title>

        <%@include file="includes/head.jsp"%>
    </head>
    <body>
        <%@include file="includes/navbar.jsp"%>

        <div class="container">
            <div class="card-header my-3">All Products</div>
            <div class="row">
                <%                   
                    if (!products.isEmpty()) {
                        for (Product p : products) {
                %>
                <div class="col-md-3 my-3">
                    <div class="card w-100" style="width: 18rem;">
                        <img src="./product-images/<%= p.getImage() %>" class="card-img-top" alt="Card image cap">
                        <div class="card-body">
                            <h5 class="card-title"><%= p.getName() %></h5>
                            <h6 class="price">Price: <%= p.getPrice() %> </h6>
                            <h6 class="category">Category: <%= p.getCategory() %></h6>
                            <div  class="mt-3 d-flex justify-content-between">
                                <a  style="padding: 10px;" href="add-to-cart?id=<%= p.getId()%>" class="btn btn-dark">Add to Cart</a>
                                <a href="order-now?quatity=1&id=<%= p.getId() %>" class="btn btn-primary">Buy Now</a>
                            </div>

                        </div>
                    </div>
                </div>
                <%
                        }// end foreach
                    } // end if
                %>

            </div>
        </div>

        <%@include file="includes/footer.jsp"%>
    </body>
</html>
