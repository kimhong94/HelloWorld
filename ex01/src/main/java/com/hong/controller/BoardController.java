package com.hong.controller;

import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hong.domain.BoardVO;
import com.hong.domain.Criteria;
import com.hong.domain.PageMarker;
import com.hong.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	BoardService service;
	
	//request주소가  return 주소가 된다
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public void registerGET(BoardVO bvo, Model model){
		logger.info("register get.............");
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registPOST(BoardVO bvo, RedirectAttributes rttr) throws Exception{
		logger.info("register post.........");
		logger.info(bvo.toString());
		service.regist(bvo);
		//model.addAttribute("result", "success");
		rttr.addFlashAttribute("msg", "SUCCESS");
		//return "/board/success";
		return "redirect:/board/listAll";
	}
	
	
	//TODO 페이징처리구현, 검색기능
	//페이징 처리 중간완료 현재는 listPage의 page만 가능 / perPageNum이 url로 전달될 수 있도록 수정
	@RequestMapping(value="/listCri", method=RequestMethod.GET)
	public void listAll(Criteria cri, Model model) throws Exception{
		logger.info("show list Page with Criteria..............");
		model.addAttribute("list", service.listCriteria(cri));
	}
	
	@RequestMapping(value="/listPage", method = RequestMethod.GET)
	public void listPage(Criteria cri, Model model) throws Exception{
		
		logger.info(cri.toString());
		model.addAttribute("list", service.listCriteria(cri));
		PageMarker pageMarker = new PageMarker();
		pageMarker.setCri(cri);
		pageMarker.setTotalCount(service.listCountCriteria(cri));
		
		model.addAttribute("pageMarker", pageMarker);
	}
	
	
	@RequestMapping(value="/read", method=RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, Model model) throws Exception{
		model.addAttribute(service.read(bno));
	}
	@RequestMapping(value="/readPage", method=RequestMethod.GET)
	public void readPage(@RequestParam("bno") int bno, @ModelAttribute("cri")Criteria cri
			,Model model) throws Exception{
		
		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String remove(@RequestParam("bno")int bno, RedirectAttributes rttr)throws Exception{
		service.remove(bno);
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/board/listAll";
	}
	@RequestMapping(value="/removePage", method = RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno,
			Criteria cri, RedirectAttributes rttr) throws Exception{
		service.remove(bno);
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/board/listPage";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyGET(int bno, Model model) throws Exception{
		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value="/modifyPage", method = RequestMethod.GET)
	public void modifyPagingGET(@RequestParam("bno")int bno, 
			@ModelAttribute("cri") Criteria cri, Model model) throws Exception{
		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modifyPOST(BoardVO bvo, RedirectAttributes rttr) throws Exception{
		logger.info("mod post..........");
		service.modify(bvo);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/board/listAll";
	}
	
	@RequestMapping(value="/modifyPage",method=RequestMethod.POST)
	public String modifyPagingPOST(BoardVO board, Criteria cri,
			RedirectAttributes rttr) throws Exception{
		service.modify(board);
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/board/listPage";
		
	}

}
