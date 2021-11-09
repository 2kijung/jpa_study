package com.kh.toy.member.validator;

import com.kh.toy.member.Member;
import lombok.Data;

@Data
public class JoinForm {
	
	private String userId;
	private String password;
	private String tell;
	private String email;
	
	public Member convertToMember() {
		Member member = new Member();
		member.setUserId(userId);
		member.setTell(tell);
		member.setPassword(password);
		member.setEmail(email);
		return member;
	}
}
