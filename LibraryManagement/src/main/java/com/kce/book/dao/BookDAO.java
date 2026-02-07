package com.kce.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kce.book.util.DBUtil;
import com.kce.book.bean.BookBean;

public class BookDAO {

    public int createBook(BookBean bookbean) {
        int rowsInserted = 0;
        String sql = "INSERT INTO book_tbl (isbn, Booktitle, Booktype, Authorcode, Bookcost) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBUtil.getConnection();
        	     PreparedStatement ps = con.prepareStatement(sql)) {

        	    ps.setString(1, bookbean.getIsbn());
        	    ps.setString(2, bookbean.getBookname());
        	    ps.setString(3, String.valueOf(bookbean.getBookType()));
                ps.setInt(4, bookbean.getAuthor().getAuthorCode());
        	    ps.setDouble(5, bookbean.getCost());

        	    rowsInserted = ps.executeUpdate();

        	} catch (Exception e) {
        	    e.printStackTrace();
        	}


        return rowsInserted; 
    }
   
    public BookBean fetchBook(String isbn) {
        BookBean book = null;

       /* String sql = "SELECT b.isbn, b.book_title, b.book_type, b.book_cost, " +
                     "a.author_code, a.author_name " +
                     "FROM book_tbl b JOIN author_tbl a " +
                     "ON b.author_code = a.author_code " +
                     "WHERE b.isbn = ?";*/
        String sql="SELECT *FROM book_tbl where isbn=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
        	
            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                book = new BookBean();
                book.setIsbn(rs.getString("ISBN"));
                book.setBookname(rs.getString("BOOKTITLE"));
                book.setBookType(rs.getString("BOOKTYPE").charAt(0));
                book.setCost(rs.getFloat("BOOKCOST"));
                book.setAuthor(AuthorDAO.getAuthor(rs.getInt("AUTHORCODE")));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return book;
    }

    
}