package controllers;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import models.User;

public class UserEditController extends HttpServlet {

    private static String newTitle = "New user";
    private static String editTitle = "Edit user";

    /* HTTP GET request */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        if (request.getParameter("id") != null) {
            // If id is given, user data is loaded.
            long id = Long.parseLong(request.getParameter("id"));
            request.setAttribute("id", id);

            // Retrieve session object out request.
            HttpSession sessie = request.getSession();
            LinkedList user = (LinkedList) sessie.getAttribute("user"); // Gets user data and saves it in LinkedList

            for (int i = 0; i < user.size(); i++) {
                User userTemp = (User) user.get(i);

                //If id matches user, form is filled in.
                if (userTemp.getUserId() == id) {
                    request.setAttribute("firstName", userTemp.getFirstName());
                    request.setAttribute("lastName", userTemp.getLastName());
                    request.setAttribute("email", userTemp.getEmail());
                }
            }
            redirect(request, response, editTitle); // Redirects to edit page.
        } else {
            redirect(request, response, newTitle); // Else to new page.
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        // Retrieve session object out of request.
        HttpSession sessie = request.getSession();

        // If parameter 'id' is not null, user update.
        boolean isUserUpdate = request.getParameter("id") != null;

        // Retrieve users
        List<User> user = (List) sessie.getAttribute("user");

        // Check if user list is not null, else create new list with this session.
        if (user == null) {
            user = new LinkedList<User>();
        }

        // Form to user object.
        User formUser = getUserFromRequest(request);

        // If user update, then retrieve user and edit data.
        if (isUserUpdate) {
            for (int i = 0; i < user.size(); i++) {
                User userTemp = (User) user.get(i);

                // If userId == customerId, update user.
                if (userTemp.getUserId() == formUser.getUserId()) {
                    userTemp.setFirstName(formUser.getFirstName());
                    userTemp.setLastName(formUser.getLastName());
                    userTemp.setEmail(formUser.getEmail());
                }
            }
        } else {
            // Else add user with new id to object.
            long uniekId = System.nanoTime();
            formUser.setUserId(uniekId);
            user.add(formUser);
        }

        sessie.setAttribute("user", user);

        // Redirect back to client.
        response.sendRedirect("../user");
    }

    private void redirect(HttpServletRequest request, HttpServletResponse response, String title)
            throws ServletException, IOException {
        request.setAttribute("pageTitle", title);

        // Send the result of edit_user.jsp back to the client
        String address = "/edit_user.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    /**
     * Creates user object with parameters out of HTTP request.
     */
    private User getUserFromRequest(HttpServletRequest request) {
        User user = new User();

        if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
            user.setUserId(Long.parseLong(request.getParameter("id")));
        }
        if (request.getParameter("firstName") != null) {
            user.setFirstName(request.getParameter("firstName"));
        }
        if (request.getParameter("lastName") != null) {
            user.setLastName(request.getParameter("lastName"));
        }
        if (request.getParameter("email") != null) {
            user.setEmail(request.getParameter("email"));
        }

        return user;
    }
}
