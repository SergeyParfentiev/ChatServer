package ua.kiev.prog.chats;

import ua.kiev.prog.clients.ClientList;
import ua.kiev.prog.messages.Message;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ChatList {

    private String defaultChat = "common";

    private static final ChatList chatList = new ChatList();

    private final List<Chat> chats = new CopyOnWriteArrayList<>();

    private final ClientList clients = ClientList.getInstance();

    private ChatList() {
        addChat(defaultChat);
    }

    public static ChatList getInstance() {
        return chatList;
    }

    public synchronized void addChat(String name) {
        chats.add(new Chat(name));
    }

    public synchronized Chat isChatAlreadyExist(String name) {
        Chat result = null;
        for (Chat chat : chats) {
            if (chat.getName().equals(name)) {
                result = chat;
            }
        }
        return result;
    }

    public synchronized void addClientToChat(Chat chat, String clientName) {
        chat.addClient(clientName);
        clients.getClient(clientName).setChatRoom(chat.getName());
    }

    public synchronized void addMessageToChat(String chatName, Message message) {
        isChatAlreadyExist(chatName).addMessage(message);
    }

    public synchronized String getMessagesFromChat(String chatName, String loginName) {
        return isChatAlreadyExist(chatName).getMessages(loginName);
    }

    public String getDefaultChat() {
        return defaultChat;
    }
}
