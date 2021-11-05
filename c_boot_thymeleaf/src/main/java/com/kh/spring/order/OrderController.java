package com.kh.spring.order;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;

	@GetMapping("order-form")
	public void orderForm() {
	}

	@PostMapping("order")
	public String order(@RequestParam(required = false, name = "food")
									Optional<List<String>> foods,
									Model model) {
		//Optional을 사용하지않고 List<String>으로 매개변수를 전달 받을 때 코드
		//foods = foods == null> new ArrayList<String>() : foods;
		Map<String, Object> commandMap = orderService.order(foods.orElseGet(() -> List.of()));
		model.addAllAttributes(commandMap);
		return "order/receipt";
	}

}