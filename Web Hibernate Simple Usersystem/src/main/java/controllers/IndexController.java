package controllers;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class IndexController extends HttpServlet {

    /* HTTP GET Request */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Send result of index back to client.
        String address = "/index.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}
