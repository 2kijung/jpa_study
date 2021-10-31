package com.kh.bookmanager.member;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Entity
@DynamicUpdate//변경이 감지된 속성만 쿼리에 반영
@DynamicInsert//값이 null아닌 속성만 쿼리에 반영해준다.
@Data
public class Member {	
	
	@Id
	private String userId;
	private String password;
	private String email;
	private String grade;
	private String tell;
	@Column(columnDefinition = "date default sysdate")
	private Date rentableDate;
	@Column(columnDefinition = "date default sysdate")
	private Date regDate;
	@Column(columnDefinition = "number default 0")
	private Boolean isLeave;
	
}
