package com.kh.toy.common.util.paging;

import lombok.Getter;

@Getter
public class Paging {
	//페이지 url
	private String url;
	//전체 게시물 개수
	private int total;
	//현재 페이지 
	private int currentPage;
	//페이지당 그릴 게시물 개수
	private int cntPerPage;
	//페이지 블록에 들어갈 페이지의 개수
	private int blockCnt;
	
	//이전 페이지
	private int prev;
	//다음 페이지 
	private int next;
	//마지막 페이지
	private int lastPage;
	//블록 시작 값
	private int blockStart;
	//블록 끝 값
	private int blockEnd;
	
	public Paging(PagingBuilder builder) {
		this.url = builder.url;
		this.total = builder.total;
		this.currentPage = builder.currentPage;
		this.blockCnt = builder.blockCnt;
		this.cntPerPage = builder.cntPerPage;
		this.lastPage = (int)Math.ceil((double)total/cntPerPage);
		this.prev = currentPage > 1 ? currentPage -1 : 1;
		this.next = currentPage < lastPage ? currentPage +1 : lastPage;
		calBlockStartAndEnd();
	}
	
	private void calBlockStartAndEnd() {
		this.blockStart = (currentPage-1)/blockCnt * blockCnt + 1;
		int end = blockStart + blockCnt-1;
		this.blockEnd = end > lastPage ? lastPage : end;
	}

	public static PagingBuilder builder() {
		return new PagingBuilder();
	}
	
	public static class PagingBuilder{
		private String url; 
		private int total; //전체 게시물 개수
		private int currentPage; //현재 페이지 
		private int blockCnt; //페이지 블록에 들어갈 페이지의 개수
		private int cntPerPage; //페이지당 그릴 게시물 개수
		
		public PagingBuilder url(String url) {
			this.url = url;
			return this;
		}
		
		public PagingBuilder total(int total) {
			this.total = total;
			return this;
		}
		
		public PagingBuilder currentPage(int currentPage) {
			this.currentPage = currentPage;
			return this;
		}
		
		public PagingBuilder blockCnt(int blockCnt) {
			this.blockCnt = blockCnt;
			return this;
		}
		
		public PagingBuilder cntPerPage(int cntPerPage) {
			this.cntPerPage = cntPerPage;
			return this;
		}
		
		public Paging build() {
			return new Paging(this);
		}
	}
	
	
	
	
	
	
	
	
	
	

}
