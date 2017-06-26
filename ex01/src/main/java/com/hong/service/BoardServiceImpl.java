package com.hong.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.hong.domain.BoardVO;
import com.hong.domain.Criteria;
import com.hong.domain.SearchCriteria;
import com.hong.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO boardDAO;
	
	@Transactional
	@Override
	public void regist(BoardVO bvo) throws Exception {
		boardDAO.create(bvo);
		
		String files[] = bvo.getFiles();
		
		if(files==null)
			return ;
		
		for(String fileName : files){
			boardDAO.addAttach(fileName);
		}
		
	}

	@Transactional(isolation=Isolation.READ_COMMITTED)
	@Override
	public BoardVO read(Integer bno) throws Exception {
		boardDAO.updateViewCnt(bno);
		return boardDAO.read(bno);
	}

	@Transactional
	@Override
	public void modify(BoardVO bvo) throws Exception {
		boardDAO.update(bvo);
		
		Integer bno = bvo.getBno();
		boardDAO.deleteAttach(bno);
		String[] files = bvo.getFiles();
		
		if(files==null)
			return;
		
		for(String fileName : files){
			boardDAO.replaceAttach(fileName, bno);
		}
	}

	@Override
	public void remove(Integer bno) throws Exception {
		boardDAO.deleteAttach(bno);
		boardDAO.delete(bno);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		return boardDAO.listAll();
	}

	@Override
	public List<BoardVO> listPage(Integer page) throws Exception {
		return boardDAO.listPage(page);
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		return boardDAO.listCriteria(cri);
	}

	@Override
	public int listCountCriteria(Criteria cri) throws Exception {
		return boardDAO.countPaging(cri);
	}

	@Override
	public List<BoardVO> listSearchCriteria(SearchCriteria cri) throws Exception {
		return boardDAO.listSearch(cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		return boardDAO.listSearchCount(cri);
	}

	@Override
	public List<String> getAttach(Integer bno) throws Exception {
		return boardDAO.getAttach(bno);
	}

}
