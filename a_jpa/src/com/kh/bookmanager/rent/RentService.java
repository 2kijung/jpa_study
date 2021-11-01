package com.kh.bookmanager.rent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.kh.bookmanager.book.Book;
import com.kh.bookmanager.common.jpa.JpaTemplate;
import com.kh.bookmanager.member.Member;

public class RentService {
	private RentRepository rentRepository = new RentRepository();
	public boolean persistRentInfo(String userId, List<Long> bkIdxs) {

		EntityManager em = JpaTemplate.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			Rent rent = new Rent();
			List<RentBook> rentBooks = new ArrayList<RentBook>();
			List<Book> books = new ArrayList<Book>();
			// 대출 회원 entity
			Member member = em.find(Member.class, userId);

			// 대출 제목

			bkIdxs.stream().forEach(e -> books.add(em.find(Book.class, e)));

			String title = books.size() > 1 ? books.get(0).getTitle() + " 외 " + (books.size() - 1) + "권"
					: books.get(0).getTitle();

			rent.setMember(member);
			rent.setTitle(title);
			rent.setRentBookCnt(books.size());

			// RentBook Entity 속성 추가
			books.stream().forEach(e -> {
				RentBook rentBook = new RentBook();
				rentBook.setBook(e);
				rentBook.changeRent(rent);
				rentBook.setState("대출");
				rentBooks.add(rentBook);
			});
			em.persist(rent);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}

		return false;
	}

	public void returnBook(long rbIdx) {
		EntityManager em = JpaTemplate.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			RentBook rentBook = em.find(RentBook.class, rbIdx);
			rentBook.setState("반납");
			rentBook.setReturnDate(LocalDateTime.now());

			boolean flg = true;

			if (rentBook.getRent().getRentBooks().stream().allMatch(e -> e.getState().equals("반납"))) {
				rentBook.getRent().setIsReturn(true);
			}

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}

	}

	public void enxtendsBook(long rbIdx) {
		EntityManager em = JpaTemplate.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			RentBook rentBook = em.find(RentBook.class, rbIdx);
			rentBook.setState("연장");
			rentBook.setReturnDate(rentBook.getReturnDate().plusDays(7));
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	public List<Rent> findAllRentByUserId(String userId) {
		EntityManager em = JpaTemplate.createEntityManager();
		List<Rent> rents = new ArrayList<Rent>();
		try {
			rents = rentRepository.findAllRentByUserId(em,userId);
		} finally {
			em.close();
		}
		return rents;
	}

}