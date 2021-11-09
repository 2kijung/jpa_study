package com.kh.toy.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String>{

	Optional<Member> findByUserIdAndIsLeave(String userId, boolean isLeave);
	
}
