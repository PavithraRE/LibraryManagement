package com.kce.book.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import com.kce.book.bean.BookBean;

@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        BookBean book = (BookBean) session.getAttribute("book");

        out.println("<html><body>");
        out.println("<h2>Book Details</h2>");
        out.println("ISBN: " + book.getIsbn() + "<br>");
        out.println("Title: " + book.getBookname() + "<br>");
        out.println("Type: " + book.getBookType() + "<br>");
        out.println("Cost: " + book.getCost() + "<br>");
        out.println("Author: " + book.getAuthor().getAuthorName() + "<br>");
        out.println("Contact: " + book.getAuthor().getContactNo());
        out.println("</body></html>");
    }
}
