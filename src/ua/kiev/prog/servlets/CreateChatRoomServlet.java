package ua.kiev.prog.servlets;

import ua.kiev.prog.chats.ChatList;
import ua.kiev.prog.clients.ClientList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateChatRoomServlet extends HttpServlet {

    private ClientList clients = ClientList.getInstance();
    private ChatList chats = ChatList.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loginName = req.getParameter("loginName");
        String room = req.getParameter("room");

        if (chats.isChatAlreadyExist(room) == null) {
            chats.addChat(room);
            chats.addClientToChat(chats.isChatAlreadyExist(room), loginName);
            clients.getClient(loginName).setChatRoom(room);
            resp.setContentType("You create and entered the " + room + " room");
        } else {
            resp.setContentType("You didn`t create and enter the " + room + " room");
        }
    }
}
