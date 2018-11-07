package com.example.springmall.sample;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springmall.sample.service.SampleService;
import com.example.springmall.sample.vo.PageMarker;
import com.example.springmall.sample.vo.Sample;

@Controller
public class SampleController {
	@Autowired
	private SampleService sampleService;

	// 1. 샘플목록
	@RequestMapping(value="/sample/sampleList", method=RequestMethod.GET)
	public String sampleList(@RequestParam(value="currentPage", defaultValue = "1") int currentPage, Model model) { //Model model = new Model();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		int rowPerPage = 10;
		map.put("currentPage", (currentPage-1)*rowPerPage);
		map.put("rowPerPage", rowPerPage);
		PageMarker pageMaker = new PageMarker(currentPage, 10, 10);
		List<Sample> sampleList = sampleService.getSelectSampleAll(map, pageMaker);
		model.addAttribute("sampleList",sampleList);
		model.addAttribute("pageMaker",pageMaker);
		return "/sample/sampleList";
	}
	// 2-1. 삭제
	@RequestMapping(value="/sample/removeSample", method=RequestMethod.GET)
	public String removeSample(@RequestParam(value="sampleNo") int sampleNo, @RequestParam(value="currentPage",defaultValue = "1") int currentPage) {
		if(sampleService.removeSample(sampleNo) == 1) {
			System.out.println(sampleNo + "번 데이터 삭제 성공");
		}
		return "redirect:/sample/sampleList?currentPage="+currentPage;
	}

}
