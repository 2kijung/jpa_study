package com.kh.toy.member;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kh.toy.member.validator.JoinForm;

public interface MemberRepository extends JpaRepository<Member, String>{

}
