/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.AuthDAO;
import javax.servlet.http.HttpSession;

/**
 *
 * @author RxZ
 */
@WebServlet(name = "auth", urlPatterns = {"/auth"})
public class AuthController extends HttpServlet {
    
    private AuthDAO authService = new AuthDAO();

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("isCreated", authService.existsAccount() ? "yes" : "no");
        request.getRequestDispatcher("/views/auth.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String authType = request.getParameter("type");
        String password = request.getParameter("password");
        System.out.println("Auth> " + authType);
        
        HttpSession session = request.getSession(false);
        // Login
        if ("login".equals(authType)) {
            if (authService.validate(password)) {
                
                session.setAttribute("user", "administrator");
                response.sendRedirect("/");
            } else {
                response.sendRedirect("auth");
            }
        }
        
        // New setup
        if ("new".equals(authType)) {
            if (authService.newAccount(password)) {
   
                session.setAttribute("user", "administrator");
                response.sendRedirect("/");
            } else {
                response.sendRedirect("auth");
            }
        }
        
        // Change password
        if ("change".equals(authType)) {
            String oldPass = request.getParameter("old_pass");
            String newPass = request.getParameter("new_pass");
            if (session != null && session.getAttribute("user") != null && authService.changePassword(oldPass, newPass)) {
                session.setAttribute("user", "administrator");
                response.sendRedirect("/");
            } else {
                response.sendRedirect("auth");
            }
        }
        
        // Logout
        if ("logout".equals(authType)) {
            session.invalidate();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "LoginController";
    }// </editor-fold>

}
