package com.kce.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kce.book.bean.BookBean;
import com.kce.book.util.DBUtil;

public class BookDAO {

    public int createBook(BookBean book) {

        String query = "INSERT INTO Book_Table VALUES (?,?,?,?,?)";

        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, book.getIsbn());
            ps.setString(2, book.getBookName());
            ps.setString(3, String.valueOf(book.getBookType()));
            ps.setInt(4, book.getAuthor().getAuthorCode());
            ps.setFloat(5, book.getCost());

            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public BookBean fetchBook(String isbn) {

        BookBean book = null;
        String query = "SELECT * FROM Book_Table WHERE ISBN=?";

        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                book = new BookBean();
                book.setIsbn(rs.getString("ISBN"));
                book.setBookName(rs.getString("Book_Title"));
                book.setBookType(rs.getString("Book_type").charAt(0));
                book.setCost(rs.getFloat("Book_Cost"));
                book.setAuthor(new AuthorDAO().getAuthor(rs.getInt("Author_code")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return book;
    }
}
