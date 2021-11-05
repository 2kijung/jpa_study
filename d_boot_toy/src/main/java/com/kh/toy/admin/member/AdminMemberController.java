package com.kh.toy.admin.member;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.toy.member.Member;

@Controller
@RequestMapping("admin")
public class AdminMemberController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AdminMemberService adminMemberService;
	
	@GetMapping("member/member-list")
	public void searchAllMembers(Model model) {
		List<Member> members = adminMemberService.selectAllMembers();
		logger.debug(members.toString());
		model.addAttribute("members", members);
	}
	
	
	
	
	
	
	
	
	
}
