/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author thanh
 */
public class Order extends Product implements  Serializable{
     private int orderId;
     private int uid;
     private int quatity;
     private String date;

    public Order() {
        super();
    }

    public Order(int orderId, int uid, int quatity, String date) {
        this.orderId = orderId;
        this.uid = uid;
        this.quatity = quatity;
        this.date = date;
    }

    public Order(int orderId, int uid, int quatity, String date, int id, String name, String category, double price, String image) {
        super(id, name, category, price, image);
        this.orderId = orderId;
        this.uid = uid;
        this.quatity = quatity;
        this.date = date;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getQuatity() {
        return quatity;
    }

    public void setQuatity(int quatity) {
        this.quatity = quatity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", uid=" + uid + ", quatity=" + quatity + ", date=" + date + '}';
    }
    
    
    
    
     
     
             
    
    
}
