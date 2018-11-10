package com.example.springmall.sample.controller;

import java.util.List;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springmall.sample.service.SampleService;
import com.example.springmall.sample.vo.Sample;

@Controller
public class SampleController {
	@Autowired
	private SampleService sampleService;
	// 1. 샘플전체목록 + 검색
	@RequestMapping(value="/sample/sampleList", method = {RequestMethod.GET, RequestMethod.POST})
	public String sampleList(@RequestParam(value="currentPage", defaultValue = "1") int currentPage, Model model, @RequestParam(value="searchQuery", defaultValue = "")String searchQuery, @RequestParam(value="searchType", defaultValue = "")String searchType) { //Model model = new Model();
		HashMap<String, Object> pagingInfo = new HashMap<String, Object>();
		List<Sample> sampleList;
		if(!searchQuery.equals("")) {
			
			pagingInfo.put("searchQuery", searchQuery);			
			pagingInfo.put("searchType", searchType);
		}
		sampleList = sampleService.getSelectSample(pagingInfo, currentPage, 10, 10);
		model.addAttribute("searchQuery", searchQuery);
		model.addAttribute("searchType", searchType);
		model.addAttribute("sampleList", sampleList);
		model.addAttribute("pagingInfo", pagingInfo);
		return "/sample/sampleList";
	}
	// 2. 삭제
	@RequestMapping(value="/sample/removeSample", method = RequestMethod.GET)
	public String removeSample(@RequestParam(value = "sampleNo") int sampleNo, @RequestParam(value="currentPage",defaultValue = "1") int currentPage) {
		if(sampleService.removeSample(sampleNo) == 1) {
			System.out.println(sampleNo + "번 데이터 삭제 성공");
		}
		return "redirect:/sample/sampleList?currentPage=" + currentPage;
	}
	// 3-1. 입력 폼
	@RequestMapping(value = "/sample/addSample", method = RequestMethod.GET)
	public String addSample() {
		return "/sample/addSample";
		// jquery, bootstrap, Sample command객체
	}
	// 3-2. 입력 액션
	@RequestMapping(value = "/sample/addSample", method = RequestMethod.POST)
	public String addSample(Sample sample /*커맨드 객체*/) {
		// 커맨드 객체의 멤버 변수 == input태그 name속성 ->표준 setter존재해야된다
		int row = sampleService.addSample(sample);
		if(row != 0) {
			System.out.println("sample 등록 성공!");
		}
		return "redirect:/sample/sampleList";
	}

	// 4-1 수정폼
	@RequestMapping(value = "/sample/modifySample", method = RequestMethod.GET)
	public String modifySample(Model model, @RequestParam(value = "sampleNo") int sampleNo) {
		Sample sample = sampleService.getSample(sampleNo);
		model.addAttribute("sample", sample);
		return "/sample/modifySample";
	}
	// 4-2 수정 액션
	@RequestMapping(value = "/sample/modifySample", method = RequestMethod.POST)
	public String modifySample(Sample sample) {
		sampleService.modifySample(sample);
		return "redirect:/sample/sampleList";
	}
}
