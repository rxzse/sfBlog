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
import models.PostFilter;
import db.IndexDAO;
import java.util.ArrayList;
/**
 *
 * @author RxZ
 */
@WebServlet(name = "IndexController", urlPatterns = {"/index"})
public class IndexController extends HttpServlet {
    private IndexDAO indexService = new IndexDAO();
    
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
        String pageAction = "index";
        String postParam = request.getParameter("post");
        request.setAttribute("categories", indexService.getCategories());
        if (postParam == null) {
            request.setAttribute("posts", this.getPosts(request));
        } else {
            pageAction = "post";
            request.setAttribute("content", indexService.getPostByAlias(postParam));
        }
        request.setAttribute("action", pageAction);
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
//        processRequest(request, response);
    }
    
    private ArrayList<Post> getPosts(HttpServletRequest request) {
        int cateId = -1;
        String title = request.getParameter("title");
        
        // process for all
        title = title != null && title.equals("") ? null : title;
        try {
            cateId = Integer.parseInt(request.getParameter("category"));
        } catch (Exception e) {}
        PostFilter pf = new PostFilter(cateId, title, false, false, null, null);
        System.out.println(title);
        return indexService.getPosts(pf);
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
