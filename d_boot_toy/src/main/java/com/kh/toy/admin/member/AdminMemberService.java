package com.kh.toy.admin.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.toy.member.Member;

@Service
public class AdminMemberService {
	
	@Autowired
	private AdminMemberRepository adminMemberRepository;

	public List<Member> selectAllMembers() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
	

}
