/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Admin
 */
public class usercredentials {
    private String username, password ;
    
    public usercredentials()
    {
        
    }
    public usercredentials(String username, String password){
        this.username = username ;
        this.password = password ;
    }
    public String getusername(){
        return this.username ;
    }
    public String getpassword(){
        return this.password ;
    }
    
}
