package com.hong.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hong.domain.BoardVO;
import com.hong.domain.PageMarker;
import com.hong.domain.SearchCriteria;
import com.hong.service.BoardService;

@Controller
@RequestMapping("/sboard/*")
public class SearchBoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(SearchBoardController.class);
	
	@Inject
	private BoardService service;
	
	@RequestMapping(value = "/list", method=RequestMethod.GET)
	public void listPage(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception{
		
		logger.info(cri.toString());
		//model.addAttribute("list", service.listCriteria(cri));
		model.addAttribute("list",service.listSearchCriteria(cri));
	
		PageMarker pagemarker = new PageMarker();
		pagemarker.setCri(cri);
		
		//pagemarker.setTotalCount(service.listCountCriteria(cri));
		pagemarker.setTotalCount(service.listSearchCount(cri));
		
		model.addAttribute("pageMarker", pagemarker);
	}
	
	@RequestMapping(value="/readPage", method=RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, @ModelAttribute("cri")SearchCriteria cri,
			Model model) throws Exception{
		model.addAttribute(service.read(bno));
	}
	@RequestMapping(value="/removePage", method=RequestMethod.POST)
	public String remove(@RequestParam("bno")int bno, SearchCriteria cri,
			RedirectAttributes rttr) throws Exception{
		service.remove(bno);
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		rttr.addFlashAttribute("msg","SUCCESS");
		return "redirect:/sboard/list";
	}
	@RequestMapping(value="/modifyPage", method=RequestMethod.GET)
	public void modifyPagingGET(int bno, @ModelAttribute("cri")SearchCriteria cri,
			Model model) throws Exception{
		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value="/modifyPage", method=RequestMethod.POST)
	public String modifyPagingPOST(BoardVO board, SearchCriteria cri,
			RedirectAttributes rttr) throws Exception{
		service.modify(board);
		logger.info(cri.toString());
		
		//리다이렉트는 값을 add해줘야한다.?
		rttr.addAttribute(cri.getPage());
		rttr.addAttribute(cri.getPerPageNum());
		rttr.addAttribute(cri.getKeyword());
		rttr.addAttribute(cri.getSearchType());
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		logger.info(rttr.toString());
		
		return "redirect:/sboard/list";
	}
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public void registGET() throws Exception{
	}
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registPOST(BoardVO board, RedirectAttributes rttr)throws Exception{
		logger.info(board.toString());
		service.regist(board);

		rttr.addFlashAttribute("msg","SUCCESS");
		return "redirect:/sboard/list";
	}
	
	@ResponseBody
	@RequestMapping(value="/getAttach/{bno}")
	public List<String> getAttach(@PathVariable("bno")Integer bno) throws Exception{
		// PathVariable
		return service.getAttach(bno);
	}
}
