package com.kce.book.servlets;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import com.kce.book.bean.BookBean;
import com.kce.book.dao.AuthorDAO;
import com.kce.book.service.Administrator;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String operation = request.getParameter("operation");
        if ("AddBook".equals(operation)) {

            String isbn = request.getParameter("isbn");
            String bookName = request.getParameter("bookname");
            String bookTypeStr = request.getParameter("booktype");
            String authorName = request.getParameter("authorname");
            String costStr = request.getParameter("cost");

            System.out.println("ISBN = " + isbn);
            System.out.println("BookName = " + bookName);
            System.out.println("BookType = " + bookTypeStr);
            System.out.println("AuthorName = " + authorName);
            System.out.println("Cost = " + costStr);

            BookBean book = new BookBean();
            book.setIsbn(isbn);
            book.setBookname(bookName);

            if (bookTypeStr != null && !bookTypeStr.isEmpty()) {
                book.setBookType(bookTypeStr.charAt(0));
            }

            book.setCost(Float.parseFloat(costStr));
            book.setAuthor(AuthorDAO.getAuthor(authorName));

            String res = new Administrator().addBook(book);
            response.sendRedirect(res + ".html");
        }


        if ("Search".equals(operation)) {
            String isbn = request.getParameter("isbn");
            BookBean book = new Administrator().viewBook(isbn);

            if (book == null) {
                response.sendRedirect("Invalid.html");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("book", book);
                response.sendRedirect("ViewServlet");
            }
        }
    }
}
