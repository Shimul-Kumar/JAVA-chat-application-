/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.userLoginController;
import model.usercredentials;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shimul kumar Shoishob
 */
public class loginPage implements ActionListener{
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, regButton;
    JFrame frame ;

    public loginPage() {
        frame = new JFrame();
        frame.setTitle("User Login Page");
        frame.setSize(600, 535);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(211, 211, 211));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(usernameLabel, gbc);

        usernameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(passwordField, gbc);

        loginButton = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        loginButton.addActionListener(this);
        panel.add(loginButton, gbc);


        regButton = new JButton("Registration");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(regButton, gbc);


        regButton.addActionListener(this);
        //loginButton.addActionListener(this);

        frame.add(panel);
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginButton){


            System.out.println("login");
            usercredentials uc = new usercredentials(usernameField.getText(), passwordField.getText()) ;
            try {
                userLoginController ulc = new userLoginController(uc) ;
                if(ulc.loginIndicator){
                    System.out.println("Login Successful");

                    frame.setVisible(false);

                    new SimpleChatGUI();

                }else {
                    System.out.println("Username Password mismatch");
                    passwordField.setText("");
                }
            } catch (IOException ex) {
                Logger.getLogger(loginPage.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        else if(e.getSource() == regButton)
        {
            System.out.println("regbutton");
            frame.setVisible(false);
            registration r = new registration() ;

        }
    }
}
