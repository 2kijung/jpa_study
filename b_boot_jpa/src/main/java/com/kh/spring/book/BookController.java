package com.kh.spring.book;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BookController {
	
	private final BookService bookservice;
	
	@GetMapping("delete-book")
	public String deleteBook(String title) {
		bookservice.deleteByTitle(title);
		return "index";
	}
}
