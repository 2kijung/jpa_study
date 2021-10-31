package com.kh.bookmanager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.tool.schema.spi.JpaTargetAndSourceDescriptor;

import com.kh.bookmanager.common.jpa.JpaTemplate;
import com.kh.bookmanager.view.Index;

public class Run {

	public static void main(String[] args) {
		
		JpaTemplate.init();
		new Index().startMenu();
	}

}
