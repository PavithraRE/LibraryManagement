package com.kce.book.service;

import com.kce.book.bean.BookBean;

import com.kce.book.dao.BookDAO;



public class Administrator {

	

	  private BookDAO bookDAO = new BookDAO();

	  public String addBook(BookBean bookBean) {

		    if (bookBean == null ||
		        bookBean.getBookname() == null || bookBean.getBookname().isEmpty() ||
		        bookBean.getIsbn() == null || bookBean.getIsbn().isEmpty() ||
		        !(bookBean.getBookType() == 'G' || bookBean.getBookType() == 'T') ||
		        bookBean.getCost() <= 0 ||
		        bookBean.getAuthor() == null ||
		        bookBean.getAuthor().getAuthorName() == null ||
		        bookBean.getAuthor().getAuthorName().isEmpty()) {

		        return "Invalid";
		    }

		    int res = bookDAO.createBook(bookBean);

		    return (res == 1) ? "Success" : "Failure";
		}



	public BookBean viewBook(String isbn) {

		BookBean bookBean=new BookDAO().fetchBook(isbn);

		return bookBean;

	
		

	}

	

}