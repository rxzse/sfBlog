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
public class AdminDAO {

    private static DBContext db = new DBContext();

    // Region for category management
    public static ArrayList<Category> getCategories() {
        ArrayList<Category> categories = new ArrayList<>();
        try {
            Connection conn = db.getConnection();

            PreparedStatement ps = conn.prepareStatement(
//                    "select category.id, category.name, category.alias, category.sequence, category.createTime, category.modifyTime, count(post.id) as postCount from (category left join post on category.id = post.category) group by id order by sequence");
                        "select category.id, category.name, category.alias, category.sequence, category.createTime, category.modifyTime, count(post.id) as postCount from \n" +
"(category left join post on category.id = post.category) \n" +
"group by category.id, category.name, category.alias, category.sequence, category.createTime, category.modifyTime order by category.sequence");
                    ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                categories.add(new Category(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("alias"),
                        rs.getInt("sequence"),
                        rs.getDate("createTime"),
                        rs.getDate("modifyTime"),
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

    public static boolean newCategory(Category cartInsert) {
        try {
            Date cur = Date.valueOf(LocalDateTime.now().toLocalDate());
            Connection conn = db.getConnection();

            String sql = "insert into category (name, alias, sequence, createTime, modifyTime) values (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cartInsert.getName());
            ps.setString(2, cartInsert.getAlias());
            ps.setInt(3, cartInsert.getSequence());
            ps.setDate(4, cur);
            ps.setDate(5, cur);
            boolean result = ps.executeUpdate() != 0;

            conn.close();

            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    public static boolean editCategory(int id, Category cartUpd) {
        try {
            Date cur = Date.valueOf(LocalDateTime.now().toLocalDate());
            Connection conn = db.getConnection();

            String sql = "update category set name = ?, alias = ?, sequence = ?, modifyTime = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cartUpd.getName());
            ps.setString(2, cartUpd.getAlias());
            ps.setInt(3, cartUpd.getSequence());
            ps.setDate(4, cur);
            ps.setInt(5, id);
            boolean result = ps.executeUpdate() != 0;

            conn.close();

            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    public static boolean deleteCategory(int id) {
        boolean isSuccess = false;
        int otherCateId = 1;
        try {
            Date cur = Date.valueOf(LocalDateTime.now().toLocalDate());
            Connection conn = db.getConnection();

            // Update all post in current category to others
            String sql_posts = "update post set category = ? where category = ?";
            PreparedStatement ps = conn.prepareStatement(sql_posts);
            ps.setInt(1, otherCateId);
            ps.setInt(2, id);
            ps.executeUpdate();

            // Delete current category
            String sql_del = "delete from category where id = ?";
            ps = conn.prepareStatement(sql_del);
            ps.setInt(1, id);
            isSuccess = ps.executeUpdate() != 0;

            conn.close();
            return isSuccess;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

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
            if (condition.contains("isActive")) {
                ps.setBoolean(_curIndex, pf.isIsActive());
                _curIndex += 1;
            }
            
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
                        rs.getString("markdown"),
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
                        rs.getString("markdown"),
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
                        rs.getString("markdown"),
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

    public static boolean newPost(Post nPost) {
        try {
            Date cur = Date.valueOf(LocalDateTime.now().toLocalDate());
            Connection conn = db.getConnection();

            String sql = "insert into post (title, alias, html, markdown, isDraft, isActive, createTime, modifyTime, publishTime, category) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nPost.getTitle());
            ps.setString(2, nPost.getAlias());
            ps.setString(3, nPost.getHtml());
            ps.setString(4, nPost.getMarkdown());

//            ps.setString(5, "nothing");
//            ps.setBoolean(6, nPost.isIsDraft());
//            ps.setBoolean(7, nPost.isIsActive());
//            ps.setDate(8, cur);
//            ps.setDate(9, cur);
//            ps.setDate(10, cur);
//            ps.setInt(11, nPost.getCategory());

            ps.setBoolean(5, nPost.isIsDraft());
            ps.setBoolean(6, nPost.isIsActive());
            ps.setDate(7, cur);
            ps.setDate(8, cur);
            ps.setDate(9, cur);
            ps.setInt(10, nPost.getCategory());
            boolean result = ps.executeUpdate() != 0;
            conn.close();
            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    public static boolean editPost(int id, Post uPost) {
        try {
            Date cur = Date.valueOf(LocalDateTime.now().toLocalDate());
            Connection conn = db.getConnection();

            String sql = "update post set title = ?, alias = ?, html = ?, markdown = ?, isDraft = ?, isActive = ?, modifyTime = ?, publishTime = ?, category = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, uPost.getTitle());
            ps.setString(2, uPost.getAlias());
            ps.setString(3, uPost.getHtml());
            ps.setString(4, uPost.getMarkdown());
//            ps.setString(5, "nothing");
//            ps.setBoolean(6, uPost.isIsDraft());
//            ps.setBoolean(7, uPost.isIsActive());
//            ps.setDate(8, cur);
//            ps.setDate(9, cur);
//            ps.setInt(10, uPost.getCategory());
//            ps.setInt(11, id);

            ps.setBoolean(5, uPost.isIsDraft());
            ps.setBoolean(6, uPost.isIsActive());
            ps.setDate(7, cur);
            ps.setDate(8, cur);
            ps.setInt(9, uPost.getCategory());
            ps.setInt(10, id);
            boolean result = ps.executeUpdate() != 0;

            conn.close();

            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    public static boolean deletePost(int id) {
        boolean isSuccess = false;
        try {
            Connection conn = db.getConnection();

            String sql_del = "delete from post where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql_del);
            ps.setInt(1, id);
            isSuccess = ps.executeUpdate() != 0;

            conn.close();
            return isSuccess;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    public static void main(String[] args) {
        String[] aaa = {"nvt", "abc"};
        Post nP = new Post(0, 1, "Edited23", "1232", "1232", "aaa", true, true, null, null, null);
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
