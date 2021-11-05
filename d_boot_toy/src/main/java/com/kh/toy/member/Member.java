package com.kh.toy.member;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLock;

import lombok.Data;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class Member {
	
	   @Id
	   private String userId;
	   private String password;
	   private String email;
	   private String grade;
	   private String tell;
	   
	   @Column(columnDefinition = "date default sysdate")
	   private LocalDate rentableDate;
	   
	   @Column(columnDefinition = "date default sysdate")
	   private LocalDate regDate;
	   
	   @Column(columnDefinition = "number default 0")
	   private Boolean isLeave;
	   
	   
	   
	   
	   
	   
	   
	   

}
