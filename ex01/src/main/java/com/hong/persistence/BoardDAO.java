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
	 * 2017�� 6�� 3��
	 * ���� SQL�� �ۼ��� ���� �޼ҵ� ���� 
	 * @param cri
	 * @return
	 * @throws Exception
	 */
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception;
	public int listSearchCount(SearchCriteria cri) throws Exception;
}
