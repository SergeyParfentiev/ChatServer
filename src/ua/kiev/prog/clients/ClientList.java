package ua.kiev.prog.clients;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ClientList {

    private static final ClientList clientList = new ClientList();

    public static ClientList getInstance() {return clientList;}

    private final List<Client> clients = new CopyOnWriteArrayList<>();

    private ClientList(){}

    public synchronized void addClient(String loginName, String chatRoom) {
        clients.add(new Client(loginName, chatRoom));
    }

    public synchronized boolean isTheClientAlreadyOnline(String loginName) {
        boolean result = false;
        for(Client client : clients) {
            if(client.getLoginName().equals(loginName) && client.isLoginStatus()) {
                result = true;
            }
        }
        return result;
    }

    public synchronized Client getClient(String loginName) {
        Client result = null;
        for(Client client : clients) {
            if(client.getLoginName().equals(loginName)) {
                result = client;
            }
        }
        return result;
    }

     public synchronized String getAllClients() {
        StringBuilder clientsList = new StringBuilder();
        clientsList.append("List of clients: ");

       for(Iterator<Client> clientItem = clients.iterator(); clientItem.hasNext();) {
           Client client = clientItem.next();
           if(clientItem.hasNext()) {
               clientsList.append(client.getLoginName()).append(", ");
           } else {
               clientsList.append(client.getLoginName()).append(".");
           }
       }
        return clientsList.toString();
    }

    public synchronized String getClientStatus(String loginName) {
        String result;

        Client client;
        if((client = getClient(loginName)) != null) {
            result = "Client: " + client.getLoginName() + " Status: " + client.toStringLoginStatus();
        } else {
            result = "The client " + loginName + " does not exist";
        }
        return result;
    }
}
