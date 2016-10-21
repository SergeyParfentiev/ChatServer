package ua.kiev.prog.messages;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class PrivateMessageList {

    private static final PrivateMessageList privateMessageList = new PrivateMessageList();

    public static PrivateMessageList getInstance() {
        return privateMessageList;
    }

    private final Map<String, List<Message>> messages = new ConcurrentHashMap<>();

    public void addMessage(String to, Message message) {
        if (!messages.containsKey(to)) {
            messages.put(to, new CopyOnWriteArrayList<>());
        }
        messages.get(to).add(message);
    }

    public String getMessages(String loginName) {
        List<Message> res = messages.get(loginName);

        if (res != null && res.size() > 0) {
            Gson gson = new GsonBuilder().create();
            String json = gson.toJson(res.toArray());
            res.clear();
            return json;
        } else
            return null;
    }
}
