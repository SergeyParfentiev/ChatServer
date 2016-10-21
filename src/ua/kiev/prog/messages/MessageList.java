package ua.kiev.prog.messages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MessageList {

    private final List<Message> list = new CopyOnWriteArrayList<>();

    public MessageList() {
    }

    public synchronized void add(Message m) {
        list.add(m);
        System.out.println("Add message");
    }

    public synchronized Map<String, Integer> toJSON(int number) {
        Map<String, Integer> messagesAndLastNumber = new HashMap<>();
        List<Message> res = new ArrayList<>();
        int lustNumber = number;
        for (int i = number; i < list.size(); i++) {
            res.add(list.get(i));
            lustNumber++;
        }

        if (res.size() > 0) {
            Gson gson = new GsonBuilder().create();
            messagesAndLastNumber.put(gson.toJson(res.toArray()), lustNumber);
            return messagesAndLastNumber;
        } else
            return null;
    }
}
