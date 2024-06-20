package model;

public class IPportUsername {

    private String IP;
    private int port ;
    private String Username;
    public IPportUsername(){

    }

    public String getUsername() {
        return Username;
    }

    public int getPort() {
        return port;
    }

    public String getIP() {
        return IP;
    }


    public void setUsername(String username) {
        Username = username;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
