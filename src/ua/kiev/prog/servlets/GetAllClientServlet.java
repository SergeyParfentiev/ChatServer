package ua.kiev.prog.servlets;

import ua.kiev.prog.clients.ClientList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetAllClientServlet extends HttpServlet {

    private ClientList clients = ClientList.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(clients.getAllClients());
    }
}
