package com.kce.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kce.book.bean.AuthorBean;
import com.kce.book.util.DBUtil;

public class AuthorDAO {

    public AuthorBean getAuthor(String authorName) {

        AuthorBean author = null;
        String query = "SELECT * FROM Author_Table WHERE Author_name=?";

        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, authorName);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                author = new AuthorBean();
                author.setAuthorCode(rs.getInt("Author_code"));
                author.setAuthorName(rs.getString("Author_name"));
                author.setContactNo(rs.getLong("Contact_no"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return author;
    }

    public AuthorBean getAuthor(int authorCode) {

        AuthorBean author = null;
        String query = "SELECT * FROM Author_Table WHERE Author_code=?";

        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, authorCode);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                author = new AuthorBean();
                author.setAuthorCode(rs.getInt("Author_code"));
                author.setAuthorName(rs.getString("Author_name"));
                author.setContactNo(rs.getLong("Contact_no"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return author;
    }
}
