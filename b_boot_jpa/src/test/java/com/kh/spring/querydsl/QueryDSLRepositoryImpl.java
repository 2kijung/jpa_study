package com.kh.spring.querydsl;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.kh.spring.book.Book;
import com.kh.spring.book.QBook;
import com.kh.spring.member.Member;
import com.kh.spring.member.QMember;
import com.kh.spring.rent.QRent;
import com.kh.spring.rent.QRentBook;
import com.kh.spring.rent.Rent;
import com.kh.spring.rent.RentBook;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

//QueryDSLRepository + impl 형태로 인터페이스명을 작성
public class QueryDSLRepositoryImpl implements QueryDSLRepositoryCustom{

	private final JPAQueryFactory query;
	private QRent rent = QRent.rent;
	private QRentBook rentBook = QRentBook.rentBook;
	private QMember member = QMember.member;
	private QBook book = QBook.book;
	
	@Autowired
	public QueryDSLRepositoryImpl(EntityManager em) {
		this.query = new JPAQueryFactory(em);
		
	}
	
	public List<Rent> whereAnd(String title, String userId){
		
		List<Rent> rents = query.select(rent)
								.from(rent)
								.where(rent.title.startsWith("디디")
										.and(rent.member.userId.eq("test")))
								.fetch();
		
		/*
		 * List<Rent> rents = query.select(rent) .from(rent)
		 * .where(rent.title.startsWith(title) ,rent.member.userId.eq(userId)) .fetch();
		 */
		return rents;
	}
	
	@Override
	public List<Rent> whereOr(String title, String userId){
		List<Rent> rents = query.select(rent).from(rent)
				.where(rent.title.startsWith(title)
				.or(rent.member.userId.eq(userId))) .fetch();
		return rents;
	}

	@Override
	public List<RentBook> fetchJoin() {
		List<RentBook> rentBooks = query.select(rentBook)
				.from(rentBook)
				.innerJoin(rentBook.book).fetchJoin()
				.innerJoin(rentBook.rent).fetchJoin()
				.where(rentBook.regDate.eq(rentBook.book.regDate))
				.fetch();
		return rentBooks;
	}

	@Override
	public List<Rent> Projections(String userId) {
		//대출자 아이디가 test인 모든 대출건의 대출건 제목과 대출번호 조회
		List<Rent> rents = query.select(Projections.fields(Rent.class, rent.title,rent.rmIdx))
								.from(rent)
								.where(rent.member.userId.eq(userId))
								.fetch();
		return rents;
	}
	@Override
	public List<Tuple> tuple (String userId){
		List<Tuple> tuple = query.select(rent.title,rent.rmIdx)
								.from(rent)
								.where(rent.member.userId.eq(userId))
								.fetch();
		return tuple;
	}

	@Override
	public List<RentBook> thetaJoin() {
		List<RentBook> rentBooks = query.select(rentBook)
				.distinct()
				.from(rentBook,member)
				.where(rentBook.regDate.eq(member.regDate))
				.fetch();
		return rentBooks;
	}

	@Override
	public List<Book> orderBy() {
		List<Book> books = query.select(book)
								.from(book)
								.orderBy(book.bookAmt.desc())
								.limit(2)
								.fetch();
		return books;
	}

	@Override
	public List<Tuple> groupBy() {
		//카테고리별 도서들의 최대 재고,평균 재고, 평균 대출 횟수
		List<Tuple> tuple = query.select(book.category,book.bookAmt.max(),book.bookAmt.avg(),book.rentCnt.avg())
								.from(book)
								.groupBy(book.category)
								.fetch();
		return tuple;
	}

	@Override
	public List<Member> subQuery() {
		//대출도서의 상태가 '대출'인 대출도서가 한 권이라도 존재하는 회원을 조회
		List<Member> members = query.select(member)
							.from(member)
							.where(member.userId.in(
									JPAExpressions.select(rent.member.userId)
													.from(rent)
													.innerJoin(rent.rentBooks,rentBook)
													.where(rentBook.state.eq("대출"))
									)).fetch();
		return members;
	}


	@Override
	public List<Book> dynamicBook(Book b) {
		//도서재고가 매개변수로 전달받은 값보다 크거나 같으면서
		//도서대출 횟수가 매개변수로 전달받은 값보다, 작거나 같은 도서를 조회
		//만약 도서 재고나 대출횟수가 0으로 전달되면 조건에서 제외
		List<Book> books = query.select(book)
								.from(book)
								.where(bookAmtGoe(b.getBookAmt())
										,rentCntLoe(b.getRentCnt()))
								.fetch();
		return books;
	}
	
	@Override
	public List<Member> dynamicMember(String keyword, String tell) {
		//사용자가 입력한 키워드가 이메일 또는 아이디 이면서
		//전화번호가 tell과 같은 회원을 조회
		
		List<Member> members = query.select(member)
									.from(member)
									.where(emailOrUserIdEq(keyword),
											member.tell.eq(tell))
									.fetch();
		return members;
	}
	
	
	public BooleanExpression bookAmtGoe(int bookAmt) {
		return bookAmt == 0?null//where절의 파라미터에 null이 들어오면 무시하고 다음 조건절부터 쿼리를 작성
				:book.bookAmt.goe(bookAmt);
	}
	public BooleanExpression rentCntLoe(int rentCnt) {
		return rentCnt == 0 ? null
							: book.rentCnt.loe(rentCnt);
	}
	
	public BooleanExpression emailOrUserIdEq(String keyword) {
		return keyword.isEmpty() ? null
								 : member.userId.eq(keyword).or(member.email.eq(keyword));
	}
	
}
