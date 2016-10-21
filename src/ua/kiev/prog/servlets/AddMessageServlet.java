package ua.kiev.prog.servlets;

import ua.kiev.prog.chats.ChatList;
import ua.kiev.prog.clients.ClientList;
import ua.kiev.prog.messages.Message;
import ua.kiev.prog.messages.PrivateMessageList;

import java.io.*;
import java.util.Scanner;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddMessageServlet extends HttpServlet {

    private ClientList clients = ClientList.getInstance();
    private ChatList chats = ChatList.getInstance();
    private PrivateMessageList privateMessages = PrivateMessageList.getInstance();

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        InputStream is = req.getInputStream();
        Message msg = Message.fromJSON(getStringFromInputStream(is));
        if (msg != null) {
            String to = msg.getTo();
            if (to.equals("all")) {
                String from = msg.getFrom();
                String chatName = clients.getClient(from).getChatRoom();
                chats.addMessageToChat(chatName, msg);
            } else {
                privateMessages.addMessage(to, msg);

            }
        } else {
            resp.setContentType("error"); // Bad request
        }
    }

    private String getStringFromInputStream(InputStream is) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        Scanner scanner = new Scanner(is);
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine());
        }
        return stringBuilder.toString();
    }
}
