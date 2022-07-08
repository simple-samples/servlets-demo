package com.revature.servlets;

import com.revature.ObjectStore;
import com.revature.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/*
This servlet class responds to requests to the /login endpoint. This isn't very RESTFul, but demonstrates what we
talked about. Instead of a /login endpoint we would probably want an endpoint that represents a resource. So instead,
we might have /users. A POST to /users would create a new individual user, a GET to the resource should return either all users, or
one individual identified as part of the request. The way Kyle likes to write a login is to have a header on POST
requests that tells the servlet method how to proceed. Something like "mode"="register" and "mode"="login"
 */
public class UserInfo extends HttpServlet {

    /*
    Because this is just a demo, we are simply responding with a hard coded JSON string that pretends to be a user with
    info in our system.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Just testing out some HttpSession API stuff
//        HttpSession session = req.getSession();
//        Object test = session.getAttribute("test");
//        if (test == null) {
//            System.out.println("attribute is null.");
//            session.setAttribute("test", "test");
//        }


        //let's create a cookie for session management:
        Cookie userCookie = new Cookie("user", "Kyle");
        resp.addCookie(userCookie);

        Cookie testCookie = new Cookie("test", "Test");
        resp.addCookie(testCookie);


        resp.setStatus(200);
        /*
        We prepare the body of our response with the following. Note that we are getting the writer that writes to the
        response body, then invoke it's write() method with our string as the parameter.
         */
        resp.getWriter().write("{\"firstName\": \"Kyle\", " +
                                "\"lastName\": \"Plummer\"," +
                                "\"age\": 37 " +
                                "}"
                            );
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("first-name");
        String lastName = req.getParameter("last-name");
        Integer age = Integer.parseInt(req.getParameter("age"));

        User user = new User(firstName, lastName, age);

        ObjectStore.add("user", user);

        resp.setStatus(201);
        resp.getWriter().write(ObjectStore.get("user").toString());
    }
}
