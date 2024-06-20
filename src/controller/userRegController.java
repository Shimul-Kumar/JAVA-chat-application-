/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.user;

/**
 *
 * @author Admin
 */
public class userRegController {
    private user u ;
    private String temp ;
    
    public userRegController()
    {
        
    }
    public userRegController(user u){
        this.u = u ;
        adduser();
    }
    public void setuser(user u){
        this.u = u ;
        adduser();
    }

    public void adduser()
    {
       temp = u.getName()+ "#" + u.getUsername() + "#" + u.getEmail() + "#" + u.getPhone()+ "#" +u.getPassword();
        System.out.println("Writing to file: " + temp);
       fileHandler fh = new fileHandler("userInfo.txt") ;
       fh.fileWrite(temp);
    }
    
}
 