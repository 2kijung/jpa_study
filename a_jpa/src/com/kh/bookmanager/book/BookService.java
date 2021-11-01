package com.kh.bookmanager.book;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.kh.bookmanager.common.jpa.JpaTemplate;

public class BookService {
	private BookRepository bookRepository = new BookRepository();
	
	public List<Book> findBookByTitle(String keyword) {
		EntityManager em = JpaTemplate.createEntityManager();
		List<Book> books = new ArrayList<>();
		try {
			books = bookRepository.findBooksByTitle(em,keyword);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return books;
	}

	public List<Book> findBookWithRank() {
		EntityManager em = JpaTemplate.createEntityManager();
		List<Book> books = new ArrayList<>();
		try {
			books = bookRepository.findBooksWithRank(em);
		}finally {
			em.close();
		}
		return books;
	}
	public List<Book> findAllBooks() {

		EntityManager em = JpaTemplate.createEntityManager();
		List<Book> books = new ArrayList<>();

		try {
			books = bookRepository.findAllBooks(em);
		} finally {
			em.close();
		}

		return books;
	}

	public boolean persistBook(Book book) {
		EntityManager em = JpaTemplate.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		int res = 0;
		tx.begin();
		try {
			em.persist(book);
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		return false;
	}

	public boolean modifyBook(Long bkIdx,String info) {
		EntityManager em = JpaTemplate.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			Book book = em.find(Book.class, bkIdx);
			book.setInfo(info);
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		return false;
	}

	public boolean removeBook(Long bkidx) {
		EntityManager em = JpaTemplate.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			em.remove(em.find(Book.class,bkidx));
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			em.close();
		}
		
		return false;
	}

}
