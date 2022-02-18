/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Cart;
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
public class ProductDao implements Serializable {

    private Connection con;
    private String query;
    private PreparedStatement pst;
    private ResultSet rs;

    public ProductDao(Connection con) {
        this.con = con;
    }

    public List<Product> getAllProduct() {
        List<Product> products = new ArrayList<Product>();

        try {
            query = "select * from products";
            pst = this.con.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                products.add(new Product(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }

    public List<Cart> getCartProducts(ArrayList<Cart> cartList) {
        List<Cart> products = new ArrayList<>();
        try {
            if (cartList.size() > 0) {
                for (Cart item : cartList) {
                    query = "select * from products where id=?";
                    pst = this.con.prepareStatement(query);
                    pst.setInt(1, item.getId());
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        products.add(new Cart(
                                rs.getInt(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getDouble(4) * item.getQuatity(),
                                rs.getString(5),
                                item.getQuatity()));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return products;
    }

    public double getTotalCartPrice(ArrayList<Cart> cartList) {
        double sum = 0;

        try {
            if (cartList.size() > 0) {
                for (Cart item : cartList) {
                    query = "select price from products where id=?";
                    pst = this.con.prepareStatement(query);
                    pst.setInt(1, item.getId());
                    rs = pst.executeQuery();
                    while(rs.next()){
                        sum += rs.getDouble("price")*item.getQuatity();
                        
                    }

                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return sum;
    }
    
    public Product getSingleProduct(int id){
        
        try {
            query = "select * from products where id =?";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, id);
            rs  = pst.executeQuery();
            
            while(rs.next()){
               return new Product(
                       rs.getInt(1), 
                       rs.getString(2), 
                       rs.getString(3),
                       rs.getDouble(4),
                       rs.getString(5));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
