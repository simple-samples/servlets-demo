# servlets-demo
 - See the simple-servlets lab [here](https://github.com/simple-samples/simple-servlets)
 - see the notes [here](https://github.com/220328-Java-Full-Stack-AWS/Curriculum-Notes/tree/main/notes)

Note: The cookie version of the UI is still in the cookie branch, but we refactored this to use localStorage instead on the main branch.


This is a demo of the simple-servlets demo with additional client/server stuff. We have the basic tomcat project 
with ping servlet plus some other demo stuff including a basic UI with GET and form data POST.

In this demo, AuthServlet was pretending to be the servlet where we would check the user's credentials against those 
in a datasource. We just hard coded some values to check. 

The UserInfo servlet represents a resource where we can find info about a user. Again, we just pretended to do this 
and hard coded the necessary stuff.

