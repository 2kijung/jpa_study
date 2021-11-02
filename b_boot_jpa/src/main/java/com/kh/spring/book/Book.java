package com.kh.spring.book;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;
@Entity
@DynamicUpdate//변경이 감지된 속성만 쿼리에 반영
@DynamicInsert//값이 null아닌 속성만 쿼리에 반영해준다.
@Data
public class Book {
	
	@Id
	@GeneratedValue//JPA 정책에 따라 식별자를 자동으로 생성
	private Long bkIdx;
	private String isbn;
	private String category;
	private String title;
	private String author;
	private String info;
	@Column(columnDefinition = "number default 1")
	private Integer bookAmt;
	@Column(columnDefinition = "date default  sysdate")
	private LocalDateTime regDate;
	@Column(columnDefinition = "number default 0")
	private Integer rentCnt;
	
	
}
