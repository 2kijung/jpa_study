package com.kh.spring.springdata;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.kh.spring.book.Book;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
public class SpringDataTest {
   
   @Autowired
   private MockMvc mockMvc;
   @Autowired
   private SpringDataRepository springDataRepository;
   
   @Test
   @DisplayName("모든 도서 검색 테스트")
   public void findAllBook() {
      springDataRepository.findAll().forEach(e -> log.info(e.toString()));
   }
   
   @Test
   @DisplayName("도서번호로 도서 조회")
   public void findById() {
      log.info(springDataRepository.findById(1001L).get().toString());
   }
   
   @Test
   @DisplayName("도서명 또는 작가로 도서 검색")
   public void findByTitleOrAuthor() {
      springDataRepository.findByTitleOrAuthor("비행운", "황정은").forEach(e -> log.info(e.toString()));
   }
   
   @Test
   @DisplayName("카테고리가 문학이고, 도서 재고가 3권 이상이면서 도서명이 디로 시작하는 도서를 조회")
   public void test01() {
      springDataRepository.findByCategoryAndBookAmtGreaterThanEqualAndTitleStartingWith("문학", 3, "디")
      .forEach(e -> log.info(e.toString()));
   }
   
   @Test
   @DisplayName("반지의 제왕 도서 등록")
   public void save() {
      Book book = new Book();
      book.setTitle("반지의 제왕");
      book.setCategory("문학");
      book.setInfo("1234");
      book.setAuthor("톨킨");
      book.setBookAmt(3);
      
      springDataRepository.save(book);
   }
   
   @Test
   @DisplayName("전체 도서 권수 조회")
   public void count() {
      log.info("전체 도서 조회 : " + springDataRepository.count());
   }
   
   @Test
   @DisplayName("도서 재고가 2권 이상인 도서의 수량 조회")
   public void countByBookAmtGreaterThanEqual() {
      log.info("도서 재고가 2권 이상인 도서의 수량 : " + springDataRepository.countByBookAmtGreaterThanEqual(2));
   }
   
   @Test
   @DisplayName("도서 재고가 10권 이상인 도서가 존재하는 지 확인")
   public void existsBy() {
      log.info("도서 재고가 10권 이상인 도서가 존재 ? : " + springDataRepository.existsByBookAmtGreaterThanEqual(10));
   }
   
   @Test
   @DisplayName("도서명이 반지의 제왕인 도서 삭제")
   public void deleteBy() throws Exception {
      mockMvc.perform(get("/delete-book")
            .param("title", "반지의제왕"))
      .andDo(print());
   }
   
   

}