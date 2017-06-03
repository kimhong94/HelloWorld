package com.hong.service;

import java.util.List;

import com.hong.domain.BoardVO;
import com.hong.domain.Criteria;

public interface BoardService {
	
	public void regist(BoardVO bvo) throws Exception;
	public BoardVO read(Integer bno) throws Exception;
	public void modify(BoardVO bvo) throws Exception;
	public void remove(Integer bno) throws Exception;
	public List<BoardVO> listAll() throws Exception;
	public List<BoardVO> listPage(Integer page) throws Exception;
	public List<BoardVO> listCriteria(Criteria cri) throws Exception;
	public int listCountCriteria(Criteria cri) throws Exception;
}
