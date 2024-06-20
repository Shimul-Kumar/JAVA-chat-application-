/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

//import model.UserDetail;
import model.user;
import model.usercredentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author Admin
 */
public class userLoginController {
    private usercredentials uc ;
    public boolean loginIndicator ;
    public String name;
    public String usr ;
    public String email;
    public String phone;
    public String pass ;
    public user userDetail2;
    public userLoginController(){
    }
    
    public userLoginController(usercredentials uc) throws IOException{
        this.uc = uc ;
        verifyCredentials();
    }
    public void setusercredentials(usercredentials uc) throws IOException{
        this.uc = uc ;
        verifyCredentials();
    }
    void verifyCredentials() throws IOException{
        boolean flag = false ;
        fileHandler fh = new fileHandler("userInfo.txt") ;
        ArrayList<String> tmp  = fh.readFile() ;
        for(int i = 0 ; i < tmp.size() ; i++){
            String line = tmp.get(i) ;


            StringTokenizer stk = new StringTokenizer(line, "#") ;
             name = stk.nextToken() ;
             usr = stk.nextToken() ;
             email = stk.nextToken();
             phone = stk.nextToken();
             pass = stk.nextToken() ;
            
            if(usr.equals(this.uc.getusername()) && pass.equals(this.uc.getpassword())){
                flag  = true ;

                userDetail2 = new user();
                userDetail2.setName(name);
                userDetail2.setUsername(usr);
                userDetail2.setEmail(email);
                userDetail2.setPhone(phone);
                break ;
                //System.out.println("Login Successful");
            }
        }
        if(flag){
           loginIndicator = flag;

        }
        else{
            System.out.println("Username Password mismatch");
        }
    }
}
