package com.hong.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
		model.addAttribute("list", service.listCriteria(cri));
	
		PageMarker pagemarker = new PageMarker();
		pagemarker.setCri(cri);
		
		pagemarker.setTotalCount(service.listCountCriteria(cri));
		
		model.addAttribute("pageMarker", pagemarker);
	}

}
