package ua.kiev.prog.listener;

import ua.kiev.prog.clients.ClientList;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

    private int minute = 60;
    private ClientList clients = ClientList.getInstance();

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().setMaxInactiveInterval(5 * minute);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        String loginName = (String) httpSessionEvent.getSession().getAttribute("loginName");
        clients.getClient(loginName).setLoginStatus(false);
    }
}
