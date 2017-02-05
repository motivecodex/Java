package controllers;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import models.User;

public class UserController extends HttpServlet {

    /* HTTP GET request */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession sessie = request.getSession();
        List<User> userList;

        // If users are saved in the session, will retrieve users.
        if (sessie.getAttribute("user") != null) {
            userList = (List) sessie.getAttribute("user");
        } else {
            // Else create new empty LinkedList.
            userList = new LinkedList();
        }

        request.setAttribute("user", userList);
        request.setAttribute("userCount", userList.size());

        // Send back results of user.jsp back to client.
        String address = "/user.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}
