package server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class Server {
    private JFrame frame;
    private JTextArea chatArea;
    JButton startServerButton;
    JButton onlineUsersButton;
    JButton clearChatButton;
    JButton aboutAppButton;
    JButton closeServerButton;
    ArrayList<PrintWriter> clientOutputStreams = new ArrayList<>();
    ArrayList<String> users = new ArrayList<>();
    BufferedReader reader;
    Socket sock;
    PrintWriter client;

    public class ClientHandler implements Runnable
    {
        BufferedReader reader;
        Socket sock;
        PrintWriter client;
        int id_privateChat ;

        public ClientHandler(Socket clientSocket, PrintWriter user)//, int useridnumber)
        {
            client = user;
            //  id_privateChat = useridnumber ;
            try
            {
                sock = clientSocket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);
            }
            catch (Exception ex)
            {
                chatArea.append("Unexpected error... \n");
            }

        }

        @Override
        public void run()
        {
            String message, connect = "Connect", disconnect = "Disconnect", chat = "Chat" , privatemsg = "private" ;
            String[] data;

            try
            {
                while ((message = reader.readLine()) != null)
                {

                    chatArea.append("\nReceived: " + message + "\n");
                    data = message.split(":");
//                    int counter2steps = 0;
//                    for (String token:data)
//                    {
//                        if(counter2steps!=2) {
//                            chatArea.append(token + " ");
//                        }
//                        counter2steps++;
//                    }

                    if (data[2].equals(connect))
                    {
                        tellEveryone((data[0] + ":" + data[1] + ":" + chat));
                        userAdd(data[0]);
                    }
                    else if (data[2].equals(disconnect))
                    {
                        tellEveryone((data[0] + "  has :Disconnected" + ":" + chat));

                        clientOutputStreams.remove(getid(data[0]));
                        userRemove(data[0]);

                    }
                    else if (data[2].equals(chat))
                    {
                        tellEveryone(message);
                    }
                    else if (data[2].equals(privatemsg)) {

                        int recievedID  = getid(data[3]);

                        if (recievedID != -1) {
                            tellthispersononly(message, recievedID, data[3]);
                        } else {
                            tellthispersononly(message, recievedID, data[0]);

                        }


                    }else if (data[2].equals("request")) {

                        int recievedID  ;

                        StringBuilder stringBuilder = new StringBuilder();
                        for (String current_user : users)
                        {
                            recievedID  = getid(current_user);
                            stringBuilder.append(current_user).append(", With ID = ").append(recievedID);

                            stringBuilder.append(".   ");
                        }
                        recievedID  = getid(data[0]);
                        String finalString = stringBuilder.toString();
                        finalString = data[0]+ ":" +finalString+ ":" + "request" ;
                        tellthispersononly(finalString, recievedID, data[0]); // d[0] here is the reciever person which is the in this case the sender itself

                    }
                    else
                    {
                        chatArea.append("No Conditions were met. \n");
                    }
                }
            }
            catch (Exception ex)
            {
                chatArea.append("Lost the connection. \n");
            }
        }
    }

    public void tellEveryone(String message)
    {
        @SuppressWarnings("rawtypes")
        Iterator it = clientOutputStreams.iterator();

        while (it.hasNext())

        {


            try
            {
                PrintWriter writer = (PrintWriter) it.next();
                writer.println(message);

                writer.flush();
                chatArea.setCaretPosition(chatArea.getDocument().getLength());

            }
            catch (Exception ex)
            {
                chatArea.append("Error telling everyone. \n");
            }

        }
    }



    public void userAdd (String data)
    {
        String message, s = ": :Connect", done = "Server: :Done", name = data;
        users.add(name);
        for (String token:users)
        {
            message = (token + s);
            tellEveryone(message);
        }
        tellEveryone(done);
    }

    public void userRemove (String data)
    {
        @SuppressWarnings("unused")
        String message, s = ": :Disconnect", done = "Server: :Done", name = data;

        users.remove(name);

        for (String UserName:users)
        {
            message = (UserName + s);
            tellEveryone(message);
        }

    }

    public int getid(String data){

        int userid = users.indexOf(data);

        return userid ;
    }


    public void tellthispersononly(String msg , int personid, String recievername){

   //     if (personid == -1) {
//
//            msg =  "The Server ... : The User is Not Found in the online users your message has not been deliverd  :private";
//
//            personid=getid(recievername);
//            try
//            {
//                PrintWriter writer =   clientOutputStreams.get(personid);   //(PrintWriter) it.next();
//                writer.println(msg);
//                writer.flush();
//                chatArea.append("Sending to {"+recievername+"} only this msg : Message not sent because the User not found in the online Users \n");
//                chatArea.setCaretPosition(chatArea.getDocument().getLength());
//            }catch (Exception ex)
//            {
//                chatArea.append("Error in telling this to "+ recievername +"." +"\n");
//            }
//
//        } else {
//
//
//
//            if (clientOutputStreams.get(personid)!= null) {

//                try
//                {
//                    PrintWriter writer =    clientOutputStreams.get(personid); //clientOutputStreams[personid];   //(PrintWriter) it.next();
//                    writer.println(msg);
//                    writer.flush();
//                    chatArea.append("Sending to {"+recievername+"} only this msg :  " + msg + "\n");
//                    chatArea.setCaretPosition(chatArea.getDocument().getLength());
//                }catch (Exception ex)
//                {
//                    chatArea.append("Error in telling this "+ recievername +"." +"\n");
////                }
//            }
//            else
//            {
//               // chatArea.append("Error in telling this ... his ID not found OR His outputstream is null "+ recievername +"." +"\n");
//            }
//        }
    }


    public void changetxtareafontsize(JTextArea txtarea){
        Font font1 = new Font("SansSerif", Font.BOLD, 12);
        txtarea.setFont(font1);
    }


    public Server() {
        // Create the main frame
        frame = new JFrame("Simple Chat - Server");
        frame.setSize(600, 535);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        // Create the left panel with buttons
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(600 / 3, 535));
        leftPanel.setBackground(new Color(211, 211, 211));
        leftPanel.setLayout(new GridLayout(5, 1, 5, 5));


        // Add buttons to the left panel
         startServerButton = new JButton("Start Server");
         onlineUsersButton = new JButton("Online Users");
         clearChatButton = new JButton("Clear Chat");
         aboutAppButton = new JButton("About App");
         closeServerButton = new JButton("Close Server");

        leftPanel.add(startServerButton);
        leftPanel.add(onlineUsersButton);
        leftPanel.add(clearChatButton);
        leftPanel.add(aboutAppButton);
        leftPanel.add(closeServerButton);

        frame.add(leftPanel, BorderLayout.WEST);

        // Create the chat area
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        startServerButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Thread starter = new Thread(new ServerStart());
                starter.start();
                chatArea.setEditable(false);
                chatArea.append(" Server has been started \n Waiting for connection ...");

                changetxtareafontsize(chatArea) ;
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

        clearChatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                chatArea.setText("clear all statement % shimul kumar shoishob % :");
            }
        });

        aboutAppButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(frame,
                        "This is a Simple Chat GUI created using Swing in Java.\n" +
                                "Developed by shimul Kumar Shoishob\n"+
                        "Dept. of Computer Science and Engineering\n"+
                        "ID:2023-3-60-157",
                        "About App",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        closeServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try
                {
                    // Thread.sleep(500);                 //4000 milliseconds is five second.
                    chatArea.append("\n");
                    chatArea.append("\n");
                    chatArea.append("\n");
                    chatArea.append("\n");
                    tellEveryone("Server: is stopping and all users will be disconnected:Chat");
                    chatArea.append("Server stopping ... \n");
                    //  Thread.sleep(500);
                    chatArea.setText("");
                    chatArea.setText("closing everything ...");
                    Thread.sleep(500);
                    System.exit(0);

                }
                catch(InterruptedException ex) {Thread.currentThread().interrupt();}

                tellEveryone("Server:is stopping and all users will be disconnected.\n:Chat");
                chatArea.append("Server stopping... \n");

                chatArea.setText("");
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Server();
            }
        });
    }


    public class ServerStart implements Runnable
    {

        @Override
        public void run()
        {


            try
            {
                @SuppressWarnings("resource")
                ServerSocket serverSock = new ServerSocket(6000);

                while (true)
                {
                    Socket clientSock = serverSock.accept();
                    PrintWriter writer = new PrintWriter(clientSock.getOutputStream());

                    clientOutputStreams.add(writer);
                    Thread listener = new Thread(new ClientHandler(clientSock, writer)); //, id));
                    listener.start();
                    chatArea.append("Got a connection. \n");

                }
            }
            catch (Exception ex)
            {
                chatArea.append("Error making a connection. \n");
            }
        }
    }



}
