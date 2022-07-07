package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthServlet extends HttpServlet {
    //This is going to be out method to handle login requests sent by our HTML form
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
        String email = req.getParameter("username");
        String password = req.getParameter("password");

        //let's add some breadcrumbs about the current state of the applicaiton
        System.out.println("Username from form: " + email);
        System.out.println("Password from form: " + password);

        //Here I will fake more robust login process.
        if(email.equals("kyle.plummer@revature.com") && password.equals("password!")) {
            resp.setStatus(200);
            resp.getWriter().write("Access granted!");
        } else {
            resp.setStatus(403);
            resp.getWriter().write("Access denied!");
        }

    }
}
