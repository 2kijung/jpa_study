package com.kh.bookmanager.book;

import java.util.List;


public class BookController {
	
	private BookService bookService =  new BookService();

	public List<Book> searchBookByTitle(String keyword) {
		return bookService.findBookByTitle(keyword);
	}

	public List<Book> searchBookWithRank() {
		return bookService.findBookWithRank();
	}

	public List<Book> searchAllBooks() {
		return bookService.findAllBooks();
	}

	public boolean registBook(Book book) {
		return bookService.persistBook(book);
	}

	public boolean modifyBook(Long bkidx, String info) {
		
		return bookService.modifyBook(bkidx,info);
	}

	public boolean removeBook(Long bkidx) {
		return bookService.removeBook(bkidx);
	}


	
	
}
