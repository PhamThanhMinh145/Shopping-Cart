/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import connection.DbConn;
import dao.OrderDao;
import entity.Cart;
import entity.Order;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author thanh
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/cart-checkout"})
public class CheckOutServlet extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            
            Date date = new Date();
            // retrive all cart products
            ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
            // user authentication
            User auth = (User) request.getSession().getAttribute("auth");
            //check auth and cart list
            if(cart_list != null && auth != null ){
                for(Cart c: cart_list){
                    
                    // prepare the order object
                    Order order = new Order();
                    order.setId(c.getId());
                    order.setUid(auth.getId());
                    order.setQuatity(c.getQuatity());
                    order.setDate(formatter.format(date));
                    
                    // instantiate the dao class
                    OrderDao oDao = new OrderDao(DbConn.getConnection());
                    // calling the insert method
                    boolean result = oDao.insertOrder(order);
                     if(!result) break;
                }
                // khi insert sussessful array list của cart sẽ ko còn  
                cart_list.clear();
                response.sendRedirect("orders.jsp");
                
            }else{
                if(auth == null) 
                    response.sendRedirect("login.jsp");
                
                response.sendRedirect("cart.jsp");
            }
            
            
        }catch(Exception e){
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
