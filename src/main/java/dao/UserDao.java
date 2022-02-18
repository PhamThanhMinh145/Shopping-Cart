/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author thanh
 */
public class UserDao {
    
    private Connection con;
    private String query;
    private PreparedStatement pst;
    private ResultSet rs;

    public UserDao(Connection con) {
        this.con = con;
    }
    
    public User userLogin(String email, String password){
        User user = null;
        
        try {
              query = "select * from users where email =? and password = ?";
              pst = this.con.prepareStatement(query);
              pst.setString(1, email);
              pst.setString(2, password);
              rs = pst.executeQuery();
              
              if(rs.next()){
                  return new User(
                          rs.getInt(1),
                          rs.getString(2), 
                          rs.getString(3), 
                          rs.getString(4));
                  
                  
              }
                  
              
              
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return user;
            
    }
    
    
    
    
}
