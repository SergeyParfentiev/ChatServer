package ua.kiev.prog.servlets;

import ua.kiev.prog.chats.ChatList;
import ua.kiev.prog.clients.ClientList;
import ua.kiev.prog.messages.PrivateMessageList;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetMessagesServlet extends HttpServlet {

    private ChatList chats = ChatList.getInstance();
    private ClientList clients = ClientList.getInstance();
    private PrivateMessageList allPrivateMessages = PrivateMessageList.getInstance();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {OutputStream os = resp.getOutputStream();
        String loginName = req.getParameter("loginName");
        if(clients.getClient(loginName).isLoginStatus()) {
            String chatName = clients.getClient(loginName).getChatRoom();

            String messages, privateMessage;

            if ((messages = chats.getMessagesFromChat(chatName, loginName)) != null) {
                os.write(messages.getBytes());
            }
            if ((privateMessage = allPrivateMessages.getMessages(loginName)) != null) {
                os.write(privateMessage.getBytes());
            }
        } else {
            os.write(null);
        }
    }
}
