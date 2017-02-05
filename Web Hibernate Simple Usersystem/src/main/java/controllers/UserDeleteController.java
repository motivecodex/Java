package controllers;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import models.User;

public class UserDeleteController extends HttpServlet {

    /* HTTP GET request */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // If id is given, delete user with that id.
        if (request.getParameter("id") != null) {
            // Retrieve parameter id out of request.
            long id = Long.parseLong(request.getParameter("id"));

            // Retrieve session object out of request.
            HttpSession sessie = request.getSession();

            // Retrieve user list out of session.
            List<User> user = (List) sessie.getAttribute("user");

            // If user list == null, create new list.
            if (user == null) {
                user = new LinkedList<User>();
                sessie.setAttribute("user", user);
            }

            // Checks which user has same userId as parameter id.
            for (int i = 0; i < user.size(); i++) {
                User userTemp = (User) user.get(i);
                if (userTemp.getUserId() == id) {
                    user.remove(i);
                }
            }

            // Redirect client to user page.
            response.sendRedirect("../user");
        }
    }
}
