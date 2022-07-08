package com.revature.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*
This class extends HttpServlet and offers a number of methods to respond to incoming HTTP requests. Unlike
the ServletContextListener, this is not an interface we can override any of the "do" methods, like "doGet()"
and doPost() to write implementations that handle
    those requests.
 */
public class PingServlet extends HttpServlet {
    /*
    This will take a simple GET request and respond with "Pong!" and status 200, indicating the request was accepted.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        resp.setStatus(200);
        resp.getWriter().print("Pong!");
    }

}

