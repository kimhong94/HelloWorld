package com.hong.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.hong.domain.Criteria;
import com.hong.domain.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {
	
	@Inject
	private SqlSession session;
	
	private static String namespace = "com.hong.mapper.ReplyMapper";
	@Override
	public List<ReplyVO> list(Integer bno) throws Exception {
		return session.selectList(namespace+".list", bno);
	}

	@Override
	public void create(ReplyVO vo) throws Exception {
		session.insert(namespace+".create", vo);
	}

	@Override
	public void update(ReplyVO vo) throws Exception {
		session.update(namespace+".update", vo);
	}

	@Override
	public void delete(Integer rno) throws Exception {
		session.delete(namespace+".delete", rno);
	}

	@Override
	public List<ReplyVO> listPage(Integer bno, Criteria cri) throws Exception {
		
		// mapper에 인자를 여러개 넘겨줄 시에  Map 사용
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("cri", cri);
		paramMap.put("bno", bno);
		
		return session.selectList(namespace+".listPage", paramMap); 
	}

	@Override
	public int count(Integer bno) throws Exception {
		
		return session.selectOne(namespace+".count", bno); 
	}

}
