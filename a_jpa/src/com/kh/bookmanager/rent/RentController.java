package com.kh.bookmanager.rent;

import java.util.List;

public class RentController {
	
	private RentService rentService = new RentService();
	
	public boolean registRent(String userId, List<Long> bkIdxs) {
		return rentService.persistRentInfo(userId, bkIdxs);
	}

	public void returnBook(long rbIdx) {
		rentService.returnBook(rbIdx);
	}

	public void extendsBook(long rbIdx) {
		rentService.enxtendsBook(rbIdx);
		
	}

	public List<Rent> findAllRentByUserId(String userId) {
		return rentService.findAllRentByUserId(userId);
	}
	
	
	
	
	
	
	
	
	
	
	
}
