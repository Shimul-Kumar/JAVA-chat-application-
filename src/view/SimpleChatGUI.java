package view;

import model.IPportUsername;
import model.user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleChatGUI {
    private JFrame frame;
    private JTextArea chatArea;
    private JTextField messageField;
    private JButton sendButton;
    private JButton connectButton;
    private JButton disconnectButton;
    private JButton clearButton;
    private JButton onlineUsersButton;
    private JButton aboutAppButton;
    private JButton logoutButton;
    private JButton viewProfileButton;
    private JTextField ipAddressField;
    private JTextField portField;
    private JTextField usernameField;
    private IPportUsername IPU = new IPportUsername();
    private user userDetail1 = new user();
    ArrayList<String> users = new ArrayList();
    BufferedReader reader;
    PrintWriter writer;
    Boolean isConnected = false;
    String username, address = "localhost";
    int port = 6000;
    Socket sock;

    public void changetxtareafontsize(JTextArea txtarea){
        Font font1 = new Font("SansSerif", Font.BOLD, 11);
        txtarea.setFont(font1);
    }

    public SimpleChatGUI() {
        // Create the main frame
        frame = new JFrame("Simple Chat");
        frame.setSize(600, 565);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        // Create the chat area
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        // Create a panel for chat area and message field
        JPanel chatPanel = new JPanel();
        chatPanel.setLayout(new BorderLayout());
        chatPanel.add(scrollPane, BorderLayout.CENTER);

        // Create the bottom panel with message field and send button using GridBagLayout
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        messageField = new JTextField();
        messageField.setPreferredSize(new Dimension(400, 60));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        bottomPanel.add(messageField, gbc);

        sendButton = new JButton("Send");
        sendButton.setPreferredSize(new Dimension(60,60));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0;
        bottomPanel.add(sendButton, gbc);

        chatPanel.add(bottomPanel, BorderLayout.SOUTH);
        frame.add(chatPanel, BorderLayout.CENTER);
        //add extra panel

        // Create the left panel with buttons and text fields using GridBagLayout
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(600 / 3, 535));
        leftPanel.setBackground(new Color(211, 211, 211));
        leftPanel.setLayout(new GridBagLayout());
        GridBagConstraints leftGbc = new GridBagConstraints();
        leftGbc.insets = new Insets(5, 5, 5, 5);

        // Username Label and Field
        JLabel usernameLabel = new JLabel("Nick Name:");
        leftGbc.gridx = 0;
        leftGbc.gridy = 0;
        leftGbc.anchor = GridBagConstraints.WEST;
        leftPanel.add(usernameLabel, leftGbc);

        usernameField = new JTextField(15);
        usernameField.setText(userDetail1.getUsername());
        leftGbc.gridx = 0;
        leftGbc.gridy = 1;
        leftGbc.anchor = GridBagConstraints.WEST;
        leftPanel.add(usernameField, leftGbc);

        // IP Address Label and Field
        JLabel ipAddressLabel = new JLabel("IP Address:");
        leftGbc.gridx = 0;
        leftGbc.gridy = 2;
        leftGbc.anchor = GridBagConstraints.WEST;
        leftPanel.add(ipAddressLabel, leftGbc);

        ipAddressField = new JTextField(15);
        ipAddressField.setText("localhost");
        leftGbc.gridx = 0;
        leftGbc.gridy = 3;
        leftGbc.anchor = GridBagConstraints.WEST;
        leftPanel.add(ipAddressField, leftGbc);

        // Port Number Label and Field
        JLabel portLabel = new JLabel("Port Number:");
        leftGbc.gridx = 0;
        leftGbc.gridy = 4;
        leftGbc.anchor = GridBagConstraints.WEST;
        leftPanel.add(portLabel, leftGbc);

        portField = new JTextField(15);
        portField.setText("6000");
        leftGbc.gridx = 0;
        leftGbc.gridy = 5;
        leftGbc.anchor = GridBagConstraints.WEST;
        leftPanel.add(portField, leftGbc);

        // Buttons spanning two columns for double width
        connectButton = new JButton("Connect");
        leftGbc.gridx = 0;
        leftGbc.gridy = 6;
        leftGbc.gridwidth = 2;
        leftGbc.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(connectButton, leftGbc);

        disconnectButton = new JButton("Disconnect");
        leftGbc.gridx = 0;
        leftGbc.gridy = 7;
        leftGbc.gridwidth = 2;
        leftGbc.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(disconnectButton, leftGbc);

        onlineUsersButton = new JButton("Online Users");
        leftGbc.gridx = 0;
        leftGbc.gridy = 8;
        leftGbc.gridwidth = 2;
        leftGbc.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(onlineUsersButton, leftGbc);

        clearButton = new JButton("Clear Chat");
        leftGbc.gridx = 0;
        leftGbc.gridy = 9;
        leftGbc.gridwidth = 2;
        leftGbc.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(clearButton, leftGbc);

        viewProfileButton = new JButton("View Profile");
        leftGbc.gridx = 0;
        leftGbc.gridy = 10;
        leftGbc.gridwidth = 2;
        leftGbc.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(viewProfileButton, leftGbc);

        aboutAppButton = new JButton("About App");
        leftGbc.gridx = 0;
        leftGbc.gridy = 11;
        leftGbc.gridwidth = 2;
        leftGbc.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(aboutAppButton, leftGbc);

        logoutButton = new JButton("Logout");
        leftGbc.gridx = 0;
        leftGbc.gridy = 12;
        leftGbc.gridwidth = 2;
        leftGbc.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(logoutButton, leftGbc);


        frame.add(leftPanel, BorderLayout.WEST);

        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!isConnected)
                {
                    if(!usernameField.getText().isEmpty()){

                       if(!users.contains(usernameField.getText())){

                            username = usernameField.getText();
                            usernameField.setEditable(false);
                            portField.setEditable(false);
                            ipAddressField.setEditable(false);
                            chatArea.setEditable(false);

                            try
                            {
                                sock = new Socket(address, port);
                                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                                reader = new BufferedReader(streamreader);
                                writer = new PrintWriter(sock.getOutputStream());
                                writer.println(username + ": has connected :Connect");
                                writer.flush();
                                isConnected = true;
                                changetxtareafontsize(chatArea) ;
                            }
                            catch (Exception ex)
                            {
                                chatArea.append("Cannot Connect! Try Again. \n");
                                usernameField.setEditable(true);
                            }

                            ListenThread();

                        } else{
                            JOptionPane.showMessageDialog(null, "Write another name for your Client, this name is already taken", "Duplicate name found !!!", JOptionPane.ERROR_MESSAGE);

                        }
                    } else{
                        JOptionPane.showMessageDialog(null, "Write a name for your Client first", "Missing Field !!!", JOptionPane.ERROR_MESSAGE);

                    }

                }else if (isConnected == true)
                {
                    chatArea.append("You are already connected. \n");
                }
            }
        });

        disconnectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                sendDisconnect();
                Disconnect();
            }
        });


        onlineUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                chatArea.append("\n Online users : \n");
                if(!users.isEmpty())
                {


                    for (String current_user : users)
                    {
                        chatArea.append(current_user + ", With ID = " + getid(current_user) );
                        chatArea.append("\n");
                    }
                }else {
                    chatArea.append(" No one is online ...");
                }

            }
        });

        // Add action listeners
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        messageField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearChat();
            }
        });

        aboutAppButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAboutApp();
            }
        });

        viewProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewProfile();
            }
        });

        ipAddressField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                IPU.setIP( ipAddressLabel.getText());

            }
        });

        portField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int x = Integer.parseInt(portField.getText());
                IPU.setPort(x);
            }
        });

        usernameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                IPU.setUsername(usernameField.getText());
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.setVisible(false);
                loginPage lp = new loginPage();
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }

    public int getid(String data){

        int userid = users.indexOf(data);

        return userid ;
    }


    private void sendMessage() {
        String nothing = "";
        String mydata = messageField.getText().trim();
        Pattern pattern = Pattern.compile("@(\\w+)\\s(.+)");
        Matcher matcher = pattern.matcher(mydata);

        if (mydata.equals(nothing)) {
            messageField.requestFocus();
        } else if (matcher.find()) {
            String receiver = matcher.group(1); // Username of the receiver
            String message = matcher.group(2);  // The actual message

            try {
                writer.println(username + ":" + message + ":" + "private" + ":" + receiver);
                writer.flush();
                chatArea.append("You have sent {" + message + "} as a private message to: '" + receiver + "'\n");
            } catch (Exception ex) {
                chatArea.append("Message was not sent. \nNo online users found by that name.\n");
            }
        } else {
            try {
                writer.println(username + ":" + mydata + ":" + "Chat");
                writer.flush();
            } catch (Exception ex) {
                chatArea.append("Message was not sent. \n");
            }
        }

        messageField.setText("");
        messageField.requestFocus();
    }


    private void clearChat() {
        chatArea.setText("clear all statement % shimul kumar shoishob % :");
    }

    private void showAboutApp() {
        JOptionPane.showMessageDialog(frame,
                "This is a Simple Chat GUI created using Swing in Java.\n" +
                        "Developed by shimul Kumar Shoishob\n"+
                        "Dept. of Computer Science and Engineering\n"+
                        "ID:2023-3-60-157",
                "About App",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void viewProfile() {
        // For now, just show a simple message dialog
        user userDetail1 = new user();
        JOptionPane.showMessageDialog(frame,
                "Your Information Here: "+
                        "\nName: "+ userDetail1.getName()+
                        "\nUserName: "+ userDetail1.getName()+
                        "\nEmail: "+ userDetail1.getName()+
                        "\nPhone: "+ userDetail1.getName(),
                "View Profile",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public class IncomingReader implements Runnable
    {

        @Override
        public void run()
        {
            String[] data;
            String stream, connect = "Connect", disconnect = "Disconnect", chat = "Chat" , privatemsg = "private";

            try
            {
                while ((stream = reader.readLine()) != null)
                {
                    data = stream.split(":");

                    if (data[2].equals(chat))
                    {
                        chatArea.append(data[0] + ": " + data[1] + "\n");
                        chatArea.setCaretPosition(chatArea.getDocument().getLength());
                    }
                    else if (data[2].equals(connect))
                    {
                        chatArea.removeAll();
                        userAdd(data[0]);

                    }
                    else if (data[2].equals(disconnect))
                    {
                        userRemove(data[0]);
                    }
                    else if(data[2].equals(privatemsg)){
                        chatArea.append("Private Message from " + data[0] + " : " + data[1] +"\n");
                        chatArea.setCaretPosition(chatArea.getDocument().getLength());

                    }
                    else if(data[2].equals("request"))
                    {
                        chatArea.append(" Server replied " + "\n" + data[1] +"\n");
                        chatArea.setCaretPosition(chatArea.getDocument().getLength());
                    }
                }
            }catch(Exception ex) { }
        }
    }

    public void ListenThread()
    {
        Thread IncomingReader = new Thread(new IncomingReader());
        IncomingReader.start();
    }

    public void userAdd(String data)
    {
        users.add(data);
    }

    public void userRemove(String data)
    {

        users.remove(data);

    }

    public void sendDisconnect()
    {
        String Disconnect = (userDetail1.getUsername() + ": :Disconnect");
        try
        {

            writer.println(Disconnect);
            writer.flush();


        } catch (Exception e)
        {
            chatArea.append("Could not send Disconnect message.\n");
        }
    }

    public void Disconnect()
    {
        try
        {
            chatArea.append("Disconnected.\n");


            isConnected = false;
            usernameField.setEditable(true);
        } catch(Exception ex) {
            chatArea.append("Failed to disconnect. \n");
        }


    }

//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new SimpleChatGUI();
//            }
//        });
//    }
}
