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

import models.Category;
import models.Post;
import db.AdminDAO;

/**
 *
 * @author RxZ
 */
@WebServlet(name = "AdminController", urlPatterns = {"/admin"})
public class AdminController extends HttpServlet {
    
    private AdminDAO adminService = new AdminDAO();

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
        request.getRequestDispatcher("/views/admin.jsp").forward(request, response);
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
        String actionName = request.getParameter("action");
        String result = "failed_request";
        if ("create_category".equals(actionName)) {
            result = this.createNewCate(request);
        }
        
        response.getWriter().print(result);
    }

    private String createNewCate(HttpServletRequest request) {
        String cateName = request.getParameter("name");
        String cateAlias = request.getParameter("alias");
        int cateSeq = Integer.parseInt(request.getParameter("sequence"));
        Category newCate = new Category(-1, cateName, cateAlias, cateSeq, null, null);
        if (adminService.newCategory(newCate)) return "success";
        return "failed <please check parameters or alias>";
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
