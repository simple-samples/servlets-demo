package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        HttpSession session = req.getSession();
        Object test = session.getAttribute("test");
        if (test == null) {
            System.out.println("attribute is null.");
            session.setAttribute("test", "test");
        }


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
}
