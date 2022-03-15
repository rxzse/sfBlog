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
import db.AdminDAO;
import java.util.ArrayList;

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
        String pageAction = "index";
        String postParam = request.getParameter("post");
        request.setAttribute("categories", adminService.getCategories());
        if (postParam == null) {
            request.setAttribute("posts", this.getPosts(request));
        } else {
            pageAction = "post";
            request.setAttribute("content", adminService.getPostByAlias(postParam));
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
        String actionName = request.getParameter("action");
        String result = "failed_request";
        
        if ("create_category".equals(actionName)) {
            result = this.createNewCate(request);
        }
        
        if ("edit_category".equals(actionName)) {
            result = this.editCate(request);
        }
        
        if ("delete_category".equals(actionName)) {
            result = this.deleteCate(request);
        }
        
        if ("create_post".equals(actionName)) {
            result = this.createNewPost(request);
        }
        
        if ("edit_post".equals(actionName)) {
            result = this.editPost(request);
        }
        
        
        if ("post_markdown".equals(actionName)) {
            result = this.getPostMarkdownById(request);
        }
        
        if ("delete_post".equals(actionName)) {
            result = this.deletePost(request);
        }
        
        response.getWriter().print(result);
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
        return adminService.getPosts(pf);
    }
    
    private String createNewPost(HttpServletRequest request) {
        try {
            String title = request.getParameter("title");
            String alias = request.getParameter("alias");
            String markdown = request.getParameter("markdown");
            String html = request.getParameter("html");
            boolean isActive = "true".equals(request.getParameter("isActive"));
            int category = Integer.parseInt(request.getParameter("category"));
            Post newPost = new Post(-1, category, title, alias, html, markdown, false, isActive, null, null, null);
            
            if (adminService.newPost(newPost)) return "success";
            return "failed [please check parameters or alias]";
        } catch (Exception e) {
            return "failed [message: " + e.getMessage() + "]";
        }
    }
    
    private String editPost(HttpServletRequest request) {
        try {
            String title = request.getParameter("title");
            String alias = request.getParameter("alias");
            String markdown = request.getParameter("markdown");
            System.out.println(markdown);
            String html = request.getParameter("html");
            boolean isActive = "true".equals(request.getParameter("isActive"));
            int category = Integer.parseInt(request.getParameter("category"));
            Post newPost = new Post(-1, category, title, alias, html, markdown, false, isActive, null, null, null);
            if (adminService.editPost(Integer.parseInt(request.getParameter("post_id")), newPost)) return "success";
            return "failed [please check parameters or alias]";
        } catch (Exception e) {
            return "failed [message: " + e.getMessage() + "]";
        }
    }
    
    private String deletePost(HttpServletRequest request) {
        try {
            int cateId = Integer.parseInt(request.getParameter("id"));
            if (adminService.deletePost(cateId)) return "success";
            return "failed [please check parameters or id]";
        } catch (Exception e) {
            return "failed [message: " + e.getMessage() + "]";
        }
    }
    
    private String getPostMarkdownById(HttpServletRequest request) {
        try {
            int cateId = Integer.parseInt(request.getParameter("id"));
            Post fPost = adminService.getPostById(cateId);
            return fPost.getMarkdown();
        } catch (Exception e) {
            return "failed [message: " + e.getMessage() + "]";
        }
    }
    

    private String createNewCate(HttpServletRequest request) {
        try {
            String cateName = request.getParameter("name");
            String cateAlias = request.getParameter("alias");
            int cateSeq = Integer.parseInt(request.getParameter("sequence"));
            Category newCate = new Category(-1, cateName, cateAlias, cateSeq, null, null);
            if (adminService.newCategory(newCate)) return "success";
            return "failed [please check parameters or alias]";
        } catch (Exception e) {
            return "failed [message: " + e.getMessage() + "]";
        }
    }
    
    private String editCate(HttpServletRequest request) {
        try {
            String cateName = request.getParameter("name");
            String cateAlias = request.getParameter("alias");
            int cateSeq = Integer.parseInt(request.getParameter("sequence"));
            Category newCate = new Category(-1, cateName, cateAlias, cateSeq, null, null);
            if (adminService.editCategory(Integer.parseInt(request.getParameter("cate_id")), newCate)) return "success";
            return "failed [please check parameters or alias]";
        } catch (Exception e) {
            return "failed [message: " + e.getMessage() + "]";
        }
    }
    
    private String deleteCate(HttpServletRequest request) {
        try {
            int cateId = Integer.parseInt(request.getParameter("id"));
            if (adminService.deleteCategory(cateId)) return "success";
            return "failed [please check parameters or id]";
        } catch (Exception e) {
            return "failed [message: " + e.getMessage() + "]";
        }
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
