package ua.kiev.prog.clients;

public class Client {

    private String loginName;
    private boolean loginStatus;
    private String chatRoom;

    Client(String loginName, String chatRoom) {
        this.loginName = loginName;
        this.chatRoom = chatRoom;
        loginStatus = true;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public boolean isLoginStatus() {
        return loginStatus;
    }

    String toStringLoginStatus() {
        String login;
        if (loginStatus) {
            login = "Online";
        } else {
            login = "Offline";
        }
        return login;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(String chatRoom) {
        this.chatRoom = chatRoom;
    }
}
