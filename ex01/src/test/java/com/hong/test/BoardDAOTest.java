package com.hong.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.hong.domain.BoardVO;
import com.hong.domain.Criteria;
import com.hong.domain.SearchCriteria;
import com.hong.persistence.BoardDAO;
import com.hong.service.BoardService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class BoardDAOTest {
	
	@Inject
	private BoardDAO dao;
	
	@Inject
	private BoardService bs;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);
	
	@Test
	public void testCreate() throws Exception {
		BoardVO bvo = new BoardVO();
		bvo.setContent("TestContext");
		bvo.setTitle("TestTitle");
		bvo.setWriter("TestUser");
		dao.create(bvo);
	}
	
	
	@Test
	public void testRead() throws Exception {
		//BoardVO bvo = dao.read(4);
		//Oracle CROB타입을 잘 읽어온다. String 형태로
		
		bs.read(4);
	}
	
	@Test
	public void testUpdate() throws Exception {
		
		BoardVO bvo = dao.read(4);
		bvo.setContent("hongseokKim");
		dao.update(bvo);
	}
	
	@Test
	public void testDelete() throws Exception {
		//dao.delete(1);
		bs.remove(2);
	}
	
	@Test
	public void testListAll() throws Exception {
		//dao.listAll();
		bs.listAll();
	}
	
	@Test
	public void testBoard_Sequence_bno_currentValue() throws Exception {
	}
	
	@Test
	public void testListPage() throws Exception {
		
		List<BoardVO> list = dao.listPage(2);
		for(BoardVO bvo : list){
			logger.info("testListPage ::::::::/"+bvo.toString());
		}
	}
	
	@Test
	public void testListCriteria() throws Exception {
		Criteria cri = new Criteria();
		cri.setPerPageNum(20);
		cri.setPage(-1);
		
		List<BoardVO> list = dao.listCriteria(cri);
		for(BoardVO bvo : list){
			logger.info("CriteriaTest..............."+bvo);
		}
		
	}
	
	@Test
	public void testUri_UsingUriComponentBuilder() {
		UriComponents uricomponents = UriComponentsBuilder.newInstance()
				.path("/{module}/{page}")
				.queryParam("bno", 12)
				.queryParam("perPageNum", 20)
				.build()
				.expand("board","read")
				.encode();
		
		logger.info("/board/read?bno=12&perPageNum=20");
		logger.info(uricomponents.toString());
	}
	
	/*
	 * MyBatis + 오라클에서의 like처리는  like '%'||#{keyword}||'%' 형태 
	 */
	@Test
	public void testDynamicSqlUsingBasicSql() throws Exception {
		SearchCriteria cri = new SearchCriteria();
		cri.setPage(1);
		cri.setKeyword("한샘");
		cri.setSearchType("tcw");
		
		logger.info("================");
		
		List<BoardVO> bvo = dao.listSearch(cri);
	}
	
	@Test
	public void testInsertAttach_And_checkOracleSequenceCurrval() throws Exception {
		BoardVO bvo = new BoardVO();
		bvo.setContent("TestAttachTransaction");
		bvo.setTitle("TestAttach");
		bvo.setWriter("TestAttachUser");
		
		// 파일 이름은 primary key이다.
		String files[] = {"TestPath2"};
		bvo.setFiles(files);
		
		bs.regist(bvo);
	}
}
