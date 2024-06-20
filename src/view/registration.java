/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.userRegController;
import model.user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Admin
 */
public class registration implements ActionListener{
    JFrame frame;
    JTextField usernameTxt, nameTxt, phoneTxt, emailTxt;
    JButton reg, can, back;
    JPasswordField passwordTxt,passwordTxt1;
    public registration()
    {


        frame = new JFrame() ;
        frame.setTitle("User Registration Page");
        frame.setSize(600, 535);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(211, 211, 211));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); //pading = gaping

        JLabel name = new JLabel("Name:");// show string
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(name, gbc);

        nameTxt = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(nameTxt, gbc);

        JLabel username = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(username, gbc);

        usernameTxt = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(usernameTxt, gbc);

        JLabel phone = new JLabel("Phone:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(phone, gbc);

        phoneTxt = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(phoneTxt, gbc);


        JLabel email = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(email, gbc);

        emailTxt = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(emailTxt, gbc);

        JLabel password = new JLabel("Password:");
        gbc.gridx=0;
        gbc.gridy=4;
        panel.add(password,gbc);

        passwordTxt = new JPasswordField(15);
        gbc.gridx=1;
        gbc.gridy=4;
        panel.add(passwordTxt,gbc);

        JLabel password1 = new JLabel("Retype password:");
        gbc.gridx=0;
        gbc.gridy=5;
        panel.add(password1,gbc);

        passwordTxt1 = new JPasswordField(15);
        gbc.gridx=1;
        gbc.gridy=5;
        panel.add(passwordTxt1,gbc);


        reg = new JButton("Registration");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(reg, gbc);

        can = new JButton("Clear All");
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(can, gbc);

        back = new JButton("Back to login page");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(back, gbc);


        reg.addActionListener(this);
        can.addActionListener(this);
        back.addActionListener(this);

        

        frame.add(panel);
        frame.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        //System.out.println("Registration done") ;

        if( e.getSource() == reg)
        {
            if(new String(passwordTxt.getPassword()).equals(new String(passwordTxt1.getPassword()))){

                user u = new user(nameTxt.getText(),usernameTxt.getText(), phoneTxt.getText(),emailTxt.getText(),passwordTxt.getText(),passwordTxt1.getText()) ;
                userRegController ur = new userRegController(u) ;
                System.out.println("Registration done");

                frame.setVisible(false);
                loginPage lp = new loginPage() ;
            }else {
                System.out.println("Retype password !!!");
            }
        } else if (e.getSource()== back) {
            frame.setVisible(false);
            loginPage lp = new loginPage() ;
        } else if (e.getSource()==can) {
            nameTxt.setText("");
            usernameTxt.setText("");
            phoneTxt.setText("");
            emailTxt.setText("");
            passwordTxt.setText("");
            passwordTxt1.setText("");
        }


    }
}
