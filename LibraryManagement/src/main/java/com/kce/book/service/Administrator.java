package com.kce.book.service;

import com.kce.book.bean.BookBean;
import com.kce.book.dao.BookDAO;

public class Administrator {

    public String addBook(BookBean book) {

        if (book == null)
            return "INVALID";

        if (book.getIsbn() == null || book.getIsbn().isEmpty())
            return "INVALID";

        if (book.getBookName() == null || book.getBookName().isEmpty())
            return "INVALID";

        if (book.getAuthor() == null)
            return "INVALID";

        if (book.getCost() <= 0)
            return "INVALID";

        if (book.getBookType() != 'G' && book.getBookType() != 'T')
            return "INVALID";

        BookDAO dao = new BookDAO();
        return dao.createBook(book) > 0 ? "SUCCESS" : "FAILURE";
    }

    public BookBean viewBook(String isbn) {
        return new BookDAO().fetchBook(isbn);
    }
}
