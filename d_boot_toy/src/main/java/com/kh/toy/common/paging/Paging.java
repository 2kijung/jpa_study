package com.kh.toy.common.paging;

public class Paging {
   
   //전체 게시물 개수
   private int total;
   //현재 페이지 
   private int currentPage;
   //페이지당 그릴 게시물 개수
   private int cntPerPage;
   //이전 페이지
   private int prev;
   //다음 페이지 
   private int next;
   //마지막 페이지
   private int lastPage;
   //페이지 블록에 들어갈 페이지의 개수
   private int blockCnt;
   //블록 시작 값
   private int blockStart;
   //블록 끝 값
   private int blockEnd;
   
   public Paging(PagingBuilder builder) {
      this.total = builder.total;
      this.currentPage = builder.currentPage;
      this.blockCnt = builder.blockCnt;
      this.cntPerPage = builder.cntPerPage;
   }

   public static PagingBuilder builder() {
      return new PagingBuilder();
   }
   
   private static class PagingBuilder{
      private int total; //전체 게시물 개수
      private int currentPage; //현재 페이지 
      private int blockCnt; //페이지 블록에 들어갈 페이지의 개수
      private int cntPerPage; //페이지당 그릴 게시물 개수
      
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