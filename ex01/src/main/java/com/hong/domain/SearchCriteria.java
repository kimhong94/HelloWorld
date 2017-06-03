package com.hong.domain;

/**
 * 검색에 사용되는 VO
 * Criteria 상송, perPageNum 과 page를 그대로 사용
 * @author khs
 *
 */
public class SearchCriteria extends Criteria{
	
	private String searchType;
	private String keyword;
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	@Override
	public String toString() {
		return "SearchCriteria [searchType=" + searchType + ", keyword=" + keyword + "]";
	}
	

}
