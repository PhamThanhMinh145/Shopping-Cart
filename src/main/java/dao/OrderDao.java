/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Order;
import entity.Product;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thanh
 */
public class OrderDao  implements Serializable{
    
    private Connection con;
    private String query;
    private PreparedStatement pst;
    private ResultSet rs;

    public OrderDao(Connection con) {
        this.con = con;
    }
    
    public boolean insertOrder(Order model){
        boolean result = false;
        try {
            query = "insert into orders (p_id, u_id, o_quantity, o_date) values(?,?,?,?)";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, model.getId());
            pst.setInt(2, model.getUid());
            pst.setInt(3, model.getQuatity());
            pst.setString(4, model.getDate());
            pst.executeUpdate();
            result = true;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        
        return result;
    }
    
    
    public List<Order> userOrders(int id){
        List<Order> list = new ArrayList<>();
        try {
            query = "select * from orders where u_id =? order by o_id desc";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while(rs.next()){
                Order order = new Order();
                // dùng inner join or dùng cách dưới đây:
                ProductDao pdao = new ProductDao(this.con);
                int pId = rs.getInt("p_id");
                
                Product product =  pdao.getSingleProduct(pId);
                order.setOrderId(rs.getInt("o_id"));
                order.setId(pId);
                order.setName(product.getName());
                order.setCategory(product.getCategory());
                order.setPrice(product.getPrice()*rs.getInt("o_quantity"));
                order.setQuatity(rs.getInt("o_quantity"));
                order.setDate(rs.getString("o_date"));
                
                list.add(order);
            }
        } catch (Exception e) {
        }
        
        return list;
    }
    
    public void cancelOrder(int id){
        try {
            query = "delete from orders where o_id=? ";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, id);
            pst.execute();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
