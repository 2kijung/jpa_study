package com.kh.spring.food;

import lombok.Data;

@Data
public class Food {
	
	private String name;
	private int price;
	
	public static Food createFood(String name) {
		//피자 : 3만원, 햄버거 5천원, 회 2만원, 치킨 18000원
		Food food = new Food();
		food.setName(name);
		switch (name) {
		case "피자": food.setPrice(30000);
			break;
		case "햄버거":food.setPrice(5000);
		break;
		case "회":food.setPrice(20000);
		break;
		case "치킨":food.setPrice(18000);
		break;
		default:throw new RuntimeException();
		}
		
		return food;
	}
}
