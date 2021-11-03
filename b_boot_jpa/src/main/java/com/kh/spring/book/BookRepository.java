package com.kh.spring.book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
	void deleteByTitle(String title);
}
