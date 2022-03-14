/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.time.LocalDateTime;
import java.sql.Date;
import java.time.LocalDate;

import models.Post;
import models.PostFilter;
import models.Category;

/**
 *
 * @author RxZ
 */
public class IndexDAO {

    private static DBContext db = new DBContext();

    // Region for category management
    public static ArrayList<Category> getCategories() {
        ArrayList<Category> categories = new ArrayList<>();
        try {
            Connection conn = db.getConnection();

            PreparedStatement ps = conn.prepareStatement(
                    "select category.id, category.name, category.alias, category.sequence, category.createTime, category.modifyTime, count(post.id) as postCount from (category left join post on category.id = post.category) group by id order by sequence");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                categories.add(new Category(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("alias"),
                        rs.getInt("sequence"),
                        null,
                        null,
                        rs.getInt("postCount")
                ));
            };

            conn.close();

            return categories;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // Region for posts management
    public static ArrayList<Post> getPosts(PostFilter pf) {
        ArrayList<Post> posts = new ArrayList<>();
        try {
            Connection conn = db.getConnection();
            String condition = "isActive = ?";
               if (!pf.isIsActive() && ! pf.isIsDraft()) condition = "1 = 1"; // search all
            // by category
            if (pf.getCategory() != -1) condition += " and category = ?";
            
            
            // by title
            if (pf.getTitle() != null)
                condition += " and lower(title) like ?";
            
            // by publish time
            if (pf.getCreateTimeStart() != null && pf.getCreateTimeEnd() != null)
                condition += " and (createTime between ? and ?)";
            
            System.out.println(condition);
            PreparedStatement ps = conn.prepareStatement("SELECT post.*, category.name FROM post join category on post.category = category.id where " + condition + " order by publishTime desc");
            
//            ps.setBoolean(2, pf.isIsDraft());
            
            int _curIndex = 1;
//            if (condition.contains("isActive")) {
//                ps.setBoolean(_curIndex, pf.isIsActive());
//                _curIndex += 1;
//            }
            
            if (condition.contains("category")) {
                ps.setInt(_curIndex, pf.getCategory());
                _curIndex += 1;
            }
            
            if (condition.contains("title")) {
                ps.setString(_curIndex, "%" + pf.getTitle().toLowerCase() + "%");
                _curIndex += 1;
            }
            
            if (condition.contains("createTime")) {
                ps.setDate(_curIndex, pf.getCreateTimeStart());
                _curIndex += 1;
                ps.setDate(_curIndex, pf.getCreateTimeEnd());
                _curIndex += 1;
            }
            
            System.out.println(ps.toString());
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Post p = new Post(
                        rs.getInt("id"),
                        rs.getInt("category"),
                        rs.getString("title"),
                        rs.getString("alias"),
                        rs.getString("html"),
                        null,
                        rs.getBoolean("isDraft"),
                        rs.getBoolean("isActive"),
                        rs.getDate("createTime"),
                        rs.getDate("modifyTime"),
                        rs.getDate("publishTime")
                );
                p.setCateName(rs.getString("name"));
                posts.add(p);
            };

            conn.close();

            return posts;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Post getPostById(int id) {
        Post ret = null;
        try {
            Connection conn = db.getConnection();

            PreparedStatement ps = conn.prepareStatement(
                    "select * from post where id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ret = new Post(
                        rs.getInt("id"),
                        rs.getInt("category"),
                        rs.getString("title"),
                        rs.getString("alias"),
                        rs.getString("html"),
                        null,
                        rs.getBoolean("isDraft"),
                        rs.getBoolean("isActive"),
                        rs.getDate("createTime"),
                        rs.getDate("modifyTime"),
                        rs.getDate("publishTime")
                );
            };

            conn.close();

            return ret;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public static Post getPostByAlias(String alias) {
        Post ret = null;
        try {
            Connection conn = db.getConnection();

            PreparedStatement ps = conn.prepareStatement(
                    "select * from post where alias = ?");
            ps.setString(1, alias);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ret = new Post(
                        rs.getInt("id"),
                        rs.getInt("category"),
                        rs.getString("title"),
                        rs.getString("alias"),
                        rs.getString("html"),
                        null,
                        rs.getBoolean("isDraft"),
                        rs.getBoolean("isActive"),
                        rs.getDate("createTime"),
                        rs.getDate("modifyTime"),
                        rs.getDate("publishTime")
                );
            };

            conn.close();

            return ret;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

 

    public static void main(String[] args) {
        String[] aaa = {"nvt", "abc"};
        Post nP = new Post(0, 15, "Edited23", "1232", "1232", "aaa", true, true, null, null, null);
        Category nC = new Category(3, "NvT", "rxzaa", 10023, null, null);
//        AdminDAO.newCategory(nC);
        System.out.println(AdminDAO.newPost(nP));
        ArrayList<Category> ct = AdminDAO.getCategories();
        for (Category i : ct) {
            System.out.println(i);
        }
        
        ArrayList<Post> cc = AdminDAO.getPosts(new PostFilter(1, "it", true, true, null, null));
        for (Post i : cc) {
            System.out.println(i);
        }
    }
}
