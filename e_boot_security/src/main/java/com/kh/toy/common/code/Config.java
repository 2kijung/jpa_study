package com.kh.toy.common.code;

public enum Config {
	//DOMAIN("https://pclass.ga")
	DOMAIN("http://localhost:9090"),
	COMPANY_EMAIL("dlrlwjd1313@naver.com"),
	SMTP_AUTHENTICATION_ID("dlrlwjd1313@gmail.com"),
	SMTP_AUTHENTICATION_PASSWORD("asdf7797"),
	//UPLOAD_PATH("C:\\CODE\\");배포경로 미리 생각해두기.
	UPLOAD_PATH("C:\\CODE\\upload\\");
	
	public final String DESC;
	
	Config(String desc){
		this.DESC=desc;
	}
}
