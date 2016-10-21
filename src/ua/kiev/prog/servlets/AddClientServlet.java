package ua.kiev.prog.servlets;

import ua.kiev.prog.chats.ChatList;
import ua.kiev.prog.clients.ClientList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddClientServlet extends HttpServlet {

    private ClientList clients = ClientList.getInstance();
    private ChatList chats = ChatList.getInstance();

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String loginName = req.getParameter("loginName");
        if (!clients.isTheClientAlreadyOnline(loginName)) {
            String defaultChat = chats.getDefaultChat();
            clients.addClient(loginName, defaultChat);
            chats.addClientToChat(chats.isChatAlreadyExist(defaultChat), loginName);
            HttpSession session = req.getSession(true);
            session.setAttribute("loginName", loginName);System.out.println("AddClientServlet false");
            resp.setContentType("false");

        } else {
            resp.setContentType("true");

        }
    }
}
