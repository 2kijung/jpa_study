package com.kh.toy.admin.member;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.kh.toy.common.util.paging.Paging;
import com.kh.toy.member.Member;

@Service
public class AdminMemberService {
	
	@Autowired
	private AdminMemberRepository adminMemberRepository;

	public Map<String,Object> findllMembers(int pageNumber) {
		
		int cntPerPage = 3;
		List<Member> members = adminMemberRepository
							.findAll(
				PageRequest.of(pageNumber-1, cntPerPage, Direction.DESC, "userId")).getContent();
							
		Paging paging = Paging.builder()
					.url("/admin/member/member-list")
					.cntPerPage(cntPerPage)
					.blockCnt(3)
					.currentPage(pageNumber)
					.total((int)adminMemberRepository.count())
					.build();
					
		return Map.of("members", members, "paging", paging);
	}
	
	
	
	
	
	
	

}
