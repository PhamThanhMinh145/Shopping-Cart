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
public class Cart extends Product implements Serializable{
    
    private int quatity;

    public Cart() {
        super();
    }

    public Cart( int id, String name, String category, double price, String image) {
        super(id, name, category, price, image);
        
    }

    public Cart(int id, String name, String category, double price, String image,int quatity) {
        super(id, name, category, price, image);
        this.quatity = quatity;
    }
    
    
    
    

    public int getQuatity() {
        return quatity;
    }

    public void setQuatity(int quatity) {
        this.quatity = quatity;
    }
    
    
    
    
    
}
