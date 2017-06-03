package com.hong.domain;

public class Criteria {
	private int page;
	private int perPageNum;
	
	public Criteria() {
		this.page = 1;
		this.perPageNum = 10; 
	}
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if(page <= 0){
			this.page = 1;
			return ;
		}
		this.page = page;
	}


	public void setPerPageNum(int perPageNum) {
		if(perPageNum <= 0 || perPageNum > 100){
			this.perPageNum = 10-1;
			return;
		}
		this.perPageNum = perPageNum;
	}

	
	
	//method for mybatis sql mapper
	public int getPerPageNum() {
		return perPageNum;
	}
	
	//method for mybatis sql mapper
	public int getPageStart(){
		
		return (this.page-1)*perPageNum+1;
		
	}
	


	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}
	
}
