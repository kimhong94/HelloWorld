package com.hong.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.hong.domain.BoardVO;
import com.hong.domain.Criteria;
import com.hong.domain.SearchCriteria;

@Repository
public class BoardDAOImpl implements BoardDAO{
	
	@Inject
	private SqlSession session;
	
	private static final String namespace = "com.hong.mapper.BoardMapper";
	
	@Override
	public void create(BoardVO bvo) throws Exception {
		session.insert(namespace+".create", bvo);
	}

	@Override
	public BoardVO read(Integer bno) throws Exception {
		return session.selectOne(namespace+".read", bno);
	}

	@Override
	public void update(BoardVO bvo) throws Exception {
		session.update(namespace+".update", bvo);
	}

	@Override
	public void delete(Integer bno) throws Exception {
		session.delete(namespace+".delete", bno);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		return session.selectList(namespace+".listAll");
	}

	@Override
	public List<BoardVO> listPage(Integer page) throws Exception {
		
		if(page <= 0){
			page =1;
		}
		page = (page -1) * 10 + 1;
		return session.selectList(namespace+".listPage", page);
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		return session.selectList(namespace+".listCriteria", cri);
	}

	@Override
	public int countPaging(Criteria cri) throws Exception {
		return session.selectOne(namespace+".countPaging", cri);
	}

	@Override
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception {
		return session.selectList(namespace+".listSearch", cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		return session.selectOne(namespace+".listSearchCount", cri);
	}

	@Override
	public void updateReplyCnt(Integer bno, int amount) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("bno", bno);
		paramMap.put("amount", amount);
		
		session.update(namespace+".updateReplyCnt", paramMap);
	}

	@Override
	public void updateViewCnt(Integer bno) throws Exception {
		
		session.update(namespace+".updateViewCnt", bno);
	}

	@Override
	public void addAttach(String fullName) throws Exception {
		session.insert(namespace + ".addAttach", fullName);
	}

	@Override
	public List<String> getAttach(Integer bno) throws Exception {
		return session.selectList(namespace+".getAttach", bno);
	}

	@Override
	public void replaceAttach(String fullName, Integer bno) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fullName", fullName);
		paramMap.put("bno", bno);
		
		session.insert(namespace+".replaceAttach", paramMap);
	}

	@Override
	public void deleteAttach(Integer bno) throws Exception {
		session.delete(namespace+".deleteAttach", bno);
	}

	
}
