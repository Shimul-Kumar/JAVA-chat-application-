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
public class user {
    private String name, username, password, phone, password1, email ;
    public user(String name, String username,String phone,String email, String password, String password1)
    {
        this.name = name ;
        this.username = username ;
        this.phone = phone;
        this.email = email;
        this.password = password ;
        this.password1 = password1 ;

    }

    public user() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public String getname(){
//        return this.name ;
//    }
//    public String getusername(){
//        return this.username ;
//    }
//    public String getPhone(){return this.phone;}
//    public String getEmail(){return this.email;}
//    public String getpassword(){
//        return this.password ;
//    }
}
