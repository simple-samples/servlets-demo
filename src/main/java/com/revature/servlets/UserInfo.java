package com.revature.servlets;

import com.revature.ObjectStore;
import com.revature.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.Parameter;
import java.util.Enumeration;

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


        String firstName = req.getParameter("first-name");
        Enumeration<String> parameters = req.getParameterNames();
        boolean paramFound = false;
        while(parameters.hasMoreElements()) {
            String param = parameters.nextElement();
            System.out.println("Paremeter search: " + param.toString());
            if(param.equals("first-name")) {
                System.out.println("Parameter found!");
                paramFound = true;
            }
        }

        User user = null;

        if(paramFound) {
            user = (User) ObjectStore.get(firstName);
        } else {
            System.out.println("Parameter not found, initiating cookie search...");
            Cookie[] cookies = req.getCookies();
            System.out.println(cookies.length + " cookies found");
            for(Cookie cookie : cookies) {
                System.out.println("Cookie search: " + cookie.getName() + " - " + cookie.getValue());
                if(cookie.getName().equals("user-name")) {
                    System.out.println("user-name cookie found! (" + cookie.getValue() + ").");
                    user = (User) ObjectStore.get(cookie.getValue());
                }
            }
        }

        if(user == null) {
            resp.setStatus(404);
            resp.getWriter().write("User not found");
        } else {
            resp.setStatus(200);
            /*
            We prepare the body of our response with the following. Note that we are getting the writer that writes to the
            response body, then invoke it's write() method with our string as the parameter.
             */
            resp.getWriter().write(user.toString());
        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("first-name");
        String lastName = req.getParameter("last-name");
        Integer age = Integer.parseInt(req.getParameter("age"));

        //let's create a cookie for session management:
        Cookie userCookie = new Cookie("user-name", firstName);
        resp.addCookie(userCookie);

//        Cookie testCookie = new Cookie("test", "Test");
//        resp.addCookie(testCookie);

        User user = new User(firstName, lastName, age);

        ObjectStore.add(firstName, user);

        resp.setStatus(201);
        resp.getWriter().write(ObjectStore.get(firstName).toString());
    }
}
