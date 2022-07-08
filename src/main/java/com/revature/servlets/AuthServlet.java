package com.revature.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthServlet extends HttpServlet {
    /*
    This is going to be our method to handle login requests sent by our HTML form
    We are overriding the doPost method here, whenever an HTTP request comes in to our server for the
    resource ".../login" tomcat will be invoking one of the methods here. Which one depends on the method in the request.
    We only have a POST method here, doPost(), and so when a POST method comes in to the webserver for this resource,
    it invokes the method below.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
        /*
        Note that the return type is void. This is because we aren't returning anything to tomcat. Tomcat has already
        supplied us with two objects (in the parameter list above) a request and a response. All we need to do is prepare
        the response. Once this method has completed executing, that response object will get sent back by tomcat.
         */

        /*
        Here we are grabbing the data out of the request. HTML forms submit the data in the URL as a parameter list.
        ex: www.something.com/context/path/to/resource/something?username=kyleplummer&password=youdontknowmysecretpass
        In the URL the "?" denotes the beginning of a parameter list, a list of key value pairs. The pairs are separated
        by "&" and keys are separated from values with "=".

        Calling the request object's getParameter() method will take in a key name and return the associated value from
        the parameter list. Below we grab the two parameters "username" and "password" getting the values sent in the form.
        If we go by the URL example above, we should get the values "kyleplummer" and "youdontknowmysecretpass"
         */
        String email = req.getParameter("username");
        String password = req.getParameter("password");

        //let's add some breadcrumbs about the current state of the applicaiton
        System.out.println("Username from form: " + email);
        System.out.println("Password from form: " + password);

        /*
        Here I will fake a more robust login process. In the real world we would preform complex validation and checking
        on our datasource verifying the credentials supplied here match those given when the user registered. Instead,
        we will just use a simple if/then statement with hard coded strings.
         */
        if(email.equals("kyle.plummer@revature.com") && password.equals("password!")) {
            resp.setStatus(200);
            resp.getWriter().write("Access granted!");
        } else {
            resp.setStatus(403);
            resp.getWriter().write("Access denied!");
        }

        /*
        Again, we don't need to return anything. The response object will be transmitted for us by the webserver. You might
        hear this sort of pattern called an "output parameter", that is one of the objects passed-by-reference in the
        parameter list exists for you to output necessary data. So, no need to return anything, tomcat is just expecting
        us to have filled in the response object with everything it needs.
         */

    }
}
