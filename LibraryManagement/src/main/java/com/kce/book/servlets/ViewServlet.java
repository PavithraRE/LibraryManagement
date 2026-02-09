package com.kce.book.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.kce.book.bean.BookBean;

@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        BookBean book = (BookBean) session.getAttribute("book");

        out.println("<html><body>");
        out.println("<h3>Book Details</h3>");
        out.println("Title : " + book.getBookName() + "<br>");
        out.println("Author : " + book.getAuthor().getAuthorName() + "<br>");
        out.println("Contact : " + book.getAuthor().getContactNo() + "<br>");
        out.println("Cost : " + book.getCost() + "<br>");
        out.println("ISBN : " + book.getIsbn());
        out.println("</body></html>");
    }
}
