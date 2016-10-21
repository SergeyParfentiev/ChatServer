package ua.kiev.prog.servlets;

import ua.kiev.prog.chats.Chat;
import ua.kiev.prog.chats.ChatList;
import ua.kiev.prog.clients.ClientList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EnterChatRoomServlet extends HttpServlet {

    private ClientList clients = ClientList.getInstance();
    private ChatList chats = ChatList.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loginName = req.getParameter("loginName");
        String room = req.getParameter("room");

        Chat chat;
        if ((chat = chats.isChatAlreadyExist(room)) != null) {
            chat.addClient(loginName);
            clients.getClient(loginName).setChatRoom(room);
            resp.setContentType("You entered the " + room + " room");
        } else {
            resp.setContentType("You didn`t enter the " + room + " room");
        }
    }
}
