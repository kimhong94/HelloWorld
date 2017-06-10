package com.hong.persistence;

import java.util.List;

import com.hong.domain.Criteria;
import com.hong.domain.ReplyVO;

public interface ReplyDAO {
	public List<ReplyVO> list(Integer bno) throws Exception;
	public void create(ReplyVO vo) throws Exception;
	public void update(ReplyVO vo) throws Exception;
	public void delete(Integer rno) throws Exception;
	
	// 인자를 두개를 받는다. mapper
	public List<ReplyVO> listPage(Integer bno, Criteria cri) throws Exception;
	public int count(Integer bno) throws Exception;
	
	public int getBno(Integer rno) throws Exception;
}
