package com.kce.book.servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.kce.book.bean.BookBean;
import com.kce.book.dao.AuthorDAO;
import com.kce.book.service.Administrator;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String operation = request.getParameter("operation");

        if ("AddBook".equals(operation)) {

            String result = addBook(request);

            if ("SUCCESS".equals(result))
                response.sendRedirect("Menu.html");
            else if ("INVALID".equals(result))
                response.sendRedirect("Invalid.html");
            else
                response.sendRedirect("Failure.html");

        } else if ("Search".equals(operation)) {

            String isbn = request.getParameter("isbn");
            BookBean book = new Administrator().viewBook(isbn);

            if (book == null) {
                response.sendRedirect("Invalid.html");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("book", book);
                RequestDispatcher rd = request.getRequestDispatcher("ViewServlet");
                rd.forward(request, response);
            }
        }
    }

    private String addBook(HttpServletRequest request) {

        String isbn = request.getParameter("isbn");
        String bookName = request.getParameter("bookName");
        String bookType = request.getParameter("bookType");
        String authorName = request.getParameter("authorName");
        String cost = request.getParameter("cost");

        if (isbn == null || bookName == null || bookType == null ||
            authorName == null || cost == null)
            return "INVALID";

        BookBean book = new BookBean();
        book.setIsbn(isbn);
        book.setBookName(bookName);
        book.setBookType(bookType.charAt(0));
        book.setCost(Float.parseFloat(cost));

        AuthorDAO dao = new AuthorDAO();
        book.setAuthor(dao.getAuthor(authorName));

        return new Administrator().addBook(book);
    }
}
