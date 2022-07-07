package servlets;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/*
This is a CORS Filter. CORS, or, Cross-Origin Resource Sharing is a browser security feature that stops some types of
attack. For now it just annoys us and gets in the way. In the real world you would want to use this as part of
a robust security strategy. For now I am just opening it up and not securing anything.

This gets mapped in the web.xml file much like a servlet. When a cross-origin request is sent, the browser does
what is called a pre-flight check, that is it makes a "fake" HTTP request that just asks the server what should and
should not be allowed.
 */
public class CorsFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Cors Filter Initializing...");
    }

    /*
    Below is where the work gets done. This basically puts a header on the response to the pre-flight
    check that tells the client what origins, methods, and headers are allowed. You will note that we have
    used the "*" character as a wild card. We are saying the allowed origins, methods, and headers are any and all.
     */

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println("CORSFilter HTTP Request: " + request.getMethod());

        // Authorize (allow) all domains to consume the content
        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Origin", "*");
        // Authorize (allow) all methods to our servlets
        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Methods","*");
        // Authorize (allow) all headers on the request
        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Headers","*");

        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        // For HTTP OPTIONS verb/method reply with ACCEPTED status code -- per CORS handshake
        if (request.getMethod().equals("OPTIONS")) {
            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
            return;
        }

        // pass the request along the filter chain
        chain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("Cors Filter Destroying...");
    }
}
