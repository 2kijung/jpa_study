package com.kh.spring.querydsl;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.spring.member.Member;

public interface QueryDSLRepository
					extends JpaRepository<Member, String>, QueryDSLRepositoryCustom{

}
