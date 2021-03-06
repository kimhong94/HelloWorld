package com.hong.persistence;

import java.util.List;

import com.hong.domain.BoardVO;
import com.hong.domain.Criteria;
import com.hong.domain.SearchCriteria;

public interface BoardDAO {
	
	public void create(BoardVO bvo) throws Exception;
	public BoardVO read(Integer bno) throws Exception;
	public void update(BoardVO bvo) throws Exception;
	public void delete(Integer bno) throws Exception;
	public List<BoardVO> listAll() throws Exception;
	public List<BoardVO> listPage(Integer page) throws Exception;
	public List<BoardVO> listCriteria(Criteria cri) throws Exception;
	public int countPaging(Criteria cri) throws Exception;
	/**
	 * 2017년 6월 3일
	 * 동적 SQL문 작성을 위한 메소드 설정 
	 */
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception;
	public int listSearchCount(SearchCriteria cri) throws Exception;
	
	public void updateReplyCnt(Integer bno, int amount) throws Exception;
	public void updateViewCnt(Integer bno) throws Exception;
	
	public void addAttach(String fullName) throws Exception;
	
	public List<String> getAttach(Integer bno) throws Exception;
	public void replaceAttach(String fullName, Integer bno) throws Exception;
	public void deleteAttach(Integer bno) throws Exception;
}
