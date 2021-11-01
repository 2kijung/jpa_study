package com.kh.bookmanager.rent;

import java.util.List;

import javax.persistence.EntityManager;

public class RentRepository {

	public List<Rent> findAllRentByUserId(EntityManager em, String userId) {
		return em.createQuery("from Rent r where r.member.userId =: userId",Rent.class)
				.setParameter("userId", userId).getResultList();
				
	}
	
	
}
