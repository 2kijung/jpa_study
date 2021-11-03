package com.kh.spring.querydsl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring.book.Book;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class QueryDSLTest {

	@Autowired
	private QueryDSLRepository queryDSLRepository;

	@Test
	@DisplayName("대출건 제목이 '디디'로 시작하고 대출자 ID가 test이 대출건 조회")
	public void whereAnd() {
		queryDSLRepository.whereAnd("디디", "test").forEach(e -> log.info(e.toString()));
	}

	@Test
	@DisplayName("대출건 제목이 '디디'로 시작하거나 대출자 ID가 jpa이 대출건 조회")
	public void whereOr() {
		queryDSLRepository.whereAnd("디디", "jpa").forEach(e -> log.info(e.toString()));
	}

	@Test
	@DisplayName("대출도서등록일자와 도서등록일자가 일치하는 도서를 조회")
	public void fetchJoin() {
		queryDSLRepository.fetchJoin().forEach(e -> log.info(e.toString()));
	}

	@Test
	@DisplayName("대출자 아이디가 test인 모든 대출건의 대출건 제목과 대출번호 조회")
	public void innerJoinProjections() {
		queryDSLRepository.Projections("test").forEach(e -> log.info(e.toString()));
	}
	
	
	@Test
	@DisplayName("대출자 아이디가 test인 모든 대출건의 대출건 제목과 대출번호 조회")
	public void tuple() {
		queryDSLRepository.tuple("test").forEach(e -> log.info(e.get(0,String.class)));
	}
	
	@Test
	@DisplayName("대충등록일자와 가입일자가 같은 회원이 존재하는 대출도서")
	public void thetaJoin() {
		queryDSLRepository.thetaJoin().forEach(e -> log.info(e.toString()));
	}
	@Test
	@DisplayName("도서 재고 수량을 기준으로 내림차순으로 2권까지 조회")
	public void orderBy() {
		queryDSLRepository.orderBy().forEach(e->log.info(e.toString()));
	}
	
	@Test
	@DisplayName("카테고리별 도서들의 최대 재고,평균 재고, 평균 대출 횟수")
	public void groupBy() {
		queryDSLRepository.groupBy().forEach(e->log.info(e.toString()));
	}
	@Test
	@DisplayName("대출도서의 상태가 '대출'인 대출도서가 한 권이라도 존재하는 회원을 조회")
	public void subQuery() {
		queryDSLRepository.subQuery().forEach(e -> log.info(e.toString()));
	}
	
	@Test
	@DisplayName("도서 동적쿼리")
	public void dynamicQueryWithBook() {
		//도서재고가 매개변수로 전달받은 값보다 크거나 같으면서
		//도서대출 횟수가 매개변수로 전달받은 값보다, 작거나 같은 도서를 조회
		//만약 도서 재고나 대출횟수가 0으로 전달되면 조건에서 제외
		Book book = new Book();
		book.setBookAmt(2);
		book.setRentCnt(0);
		queryDSLRepository.dynamicBook(book).forEach(e -> log.info(e.toString()));
	}
	@Test
	@DisplayName("회원 동적쿼리")
	public void dynamicQueryWithMember() {
		//사용자가 입력한 키워드가 이메일 또는 아이디 이면서
		//전화번호가 tell과 같은 회원을 조회
		queryDSLRepository.dynamicMember("test","010-0112-0122").forEach(e -> log.info(e.toString()));
	}
}
