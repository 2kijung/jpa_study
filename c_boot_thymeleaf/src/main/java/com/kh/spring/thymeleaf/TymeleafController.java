package com.kh.spring.thymeleaf;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kh.spring.member.Member;

@Controller
public class TymeleafController {
	@GetMapping("thymeleaf/thymeleaf")
	public void sendDataForThymeleaf(Model model, HttpSession session) {
	
		//DTO
		Member member = new Member();
		member.setUserId("P-Student");
		member.setPassword("1234");
		member.setGrade("일반");
		member.setTell("010-0000-1111");
		
		//MAP
		Map<String,Object> commandMap = new LinkedHashMap();
		commandMap.put("java", 100);
		commandMap.put("html", 88);
		commandMap.put("css", 65);
		commandMap.put("js", 91);
		commandMap.put("servlet", 77);
		commandMap.put("spring", 100);
		commandMap.put("avg", 87);
		
		model.addAttribute("score",commandMap);
		model.addAttribute("color","blue");
		session.setAttribute("userInfo", member);
		
	}
	
}
