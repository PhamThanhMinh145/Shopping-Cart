 
<nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container">
                <a class="navbar-brand" href="index.jsp">E-Commerce Cart</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse"
                    data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false"
                    aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
            
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>
                        <li class="nav-item"><a class="nav-link" href="cart.jsp">Cart <span style="background: red; color: wheat" class="badge badge-danger">${cart_list.size()}<span></a></li>
                      <% 
                        if(request.getAttribute("auth") != null){  
                            %>
                        <li class="nav-item"><a class="nav-link" href="orders.jsp">Orders</a></li>
                        <li class="nav-item"><a class="nav-link" href="logout">Logout</a></li>
                        <%      
                        }else{
                            %>
                             <li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
                             <%
                        }
                      %>
                        
                        
                        <li class="nav-item" style="margin-left: 100px; text-transform: uppercase; font-size: 20px; color: orange; line-height: 2.2; ">${auth.name}</li>
                        
                       
                    </ul>
                </div>
            </div>
 </nav>
