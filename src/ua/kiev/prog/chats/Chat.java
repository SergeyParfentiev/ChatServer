package ua.kiev.prog.chats;

import ua.kiev.prog.messages.Message;
import ua.kiev.prog.messages.MessageList;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Chat {

    private String name;
    private MessageList messages = new MessageList();
    private ConcurrentHashMap<String, Integer> messageAndTheLastNumber = new ConcurrentHashMap<>();

    Chat(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    public void addClient(String loginName) {
        if (!messageAndTheLastNumber.containsKey(loginName)) {
            messageAndTheLastNumber.put(loginName, 0);
        }
    }

    void addMessage(Message message) {
        messages.add(message);
    }

    String getMessages(String loginName) {
        String text = null;
        Map<String, Integer> messagesAndLastNumber = messages.toJSON(messageAndTheLastNumber.get(loginName));
        if (messagesAndLastNumber != null) {
            for (Map.Entry<String, Integer> map : messagesAndLastNumber.entrySet()) {
                text = map.getKey();
                messageAndTheLastNumber.put(loginName, map.getValue());
            }
        }
        return text;
    }
}
