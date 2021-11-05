package com.kh.toy.member;

import javax.transaction.Transactional;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.kh.toy.common.code.Config;
import com.kh.toy.common.mail.EmailSender;
import com.kh.toy.member.validator.JoinForm;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberRepository memberRepository;
	private final EmailSender mailSender;
	private final RestTemplate http;
	private final PasswordEncoder passwordEncoder;
	
	@Transactional
	public void persistMember(JoinForm form) {
		Member member = form.convertToMember();
		member.setGrade("일반");
		member.setPassword(passwordEncoder.encode(form.getPassword()));
		memberRepository.save(member);
	}

	public Member authenticateUser(Member member) {
		Member memberEntity = memberRepository.findById(member.getUserId()).orElse(null);
		
		if(memberEntity == null) return null;
		if(passwordEncoder.matches(member.getPassword(), memberEntity.getPassword())) {
			return memberEntity;
		}
		return null;
	}

	public boolean extistsMemberById(String userId) {
		return memberRepository.existsById(userId);
	}

	public void authenticateByEmail(JoinForm form, String token) {
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("mailTemplate", "join-auth-mail");
		body.add("userId", form.getUserId());
		body.add("persistToken", token);
		
		//RestTemplate의 기본 Content-type : application/json
		RequestEntity<MultiValueMap<String, String>> request =
				RequestEntity.post(Config.DOMAIN.DESC+"/mail")
				.accept(MediaType.APPLICATION_FORM_URLENCODED)
				.body(body);
		
		String htmlTxt = http.exchange(request, String.class).getBody();
		mailSender.send(form.getEmail(), "회원가입을 축하합니다.", htmlTxt);
	}
	

}
