package com.kh.spring.member;

import java.time.LocalDateTime;

public class Member {	
	
	private String userId;
	private String password;
	private String email;
	private String grade;
	private String tell;
	private LocalDateTime rentableDate;
	private LocalDateTime regDate;
	private Boolean isLeave;
	
	public int printHashCode() {
		System.out.println("printHashCode 메서드를 호출했습니다.");
		return this.hashCode();
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getTell() {
		return tell;
	}
	public void setTell(String tell) {
		this.tell = tell;
	}
	public LocalDateTime getRentableDate() {
		return rentableDate;
	}
	public void setRentableDate(LocalDateTime rentableDate) {
		this.rentableDate = rentableDate;
	}
	public LocalDateTime getRegDate() {
		return regDate;
	}
	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}
	public Boolean getIsLeave() {
		return isLeave;
	}
	public void setIsLeave(Boolean isLeave) {
		this.isLeave = isLeave;
	}
	@Override
	public String toString() {
		return "Member [userId=" + userId + ", password=" + password + ", email=" + email + ", grade=" + grade
				+ ", tell=" + tell + ", rentableDate=" + rentableDate + ", regDate=" + regDate + ", isLeave=" + isLeave
				+ "]";
	}
	
	
}
