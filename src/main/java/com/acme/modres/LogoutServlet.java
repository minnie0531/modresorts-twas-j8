package com.acme.modres;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.websphere.security.WSSecurityHelper;

import java.io.IOException;

@WebServlet({"/logout"})
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Handles HTTP GET requests for the logout functionality.
     *
     * This method is called when a GET request is made to the logout endpoint.
     * It revokes any existing Single Sign-On (SSO) cookies and then redirects
     * the user to the login page.
     *
     * @param request the incoming HTTP request
     * @param response the outgoing HTTP response
     * @throws IOException if an I/O error occurs while sending the response
     */
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {

        try {
            WSSecurityHelper.revokeSSOCookies(request, response);
        } catch (Exception e) {
            System.err.println("[ERROR] Error logging out");
            e.printStackTrace();
        }

        response.sendRedirect("login.jsp");
    }
}
