package com.example.springmall.sample.controller;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.example.springmall.sample.service.SampleService;
import com.example.springmall.sample.vo.Sample;
import com.example.springmall.sample.vo.SampleFile;
import com.example.springmall.sample.vo.SampleRequest;

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
		if(sampleService.removeSample(sampleNo) == 2) {
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
	public String addSample(SampleRequest sampleRequest /*커맨드 객체*/, HttpSession session) {
		// 커맨드 객체의 멤버 변수 == input태그 name속성 ->표준 setter존재해야된다
		String realPath =  session.getServletContext().getRealPath("/uploads");
		int row = sampleService.addSample(sampleRequest, realPath);
		if(row == 2) {
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
	public String modifySample(SampleRequest sampleRequest) {
		sampleService.modifySample(sampleRequest);
		return "redirect:/sample/sampleList";
	}
	@RequestMapping(value="/sample/downloadFile", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> downloadFile(@RequestParam(value="sampleFileNo") int sampleFileNo) throws Exception {
	   SampleFile sampleFile = sampleService.getSampleFileFromSampleNo(sampleFileNo);
	   String filePath = sampleFile.getSampleFilePath();
	   String fileName = sampleFile.getSampleFileName();
	   String realFileName = sampleFile.getSampleFileRealName();
	   String fileExt = sampleFile.getSampleFileExt();
	   File file = new File(filePath + "/" + realFileName + "." + fileExt);
	   InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
	   return ResponseEntity.ok()
			  .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8")  + "." + fileExt)
			  .contentType(MediaType.APPLICATION_OCTET_STREAM).contentLength(file.length())      
			  .body(resource);
	}
	@RequestMapping(value="/sample/uploadList", method = RequestMethod.GET)
	public String uploadList(@RequestParam(value="sampleNo")int sampleNo, Model model) {
		Sample sample = sampleService.getSelectSampleAndFile(sampleNo);
		if(sample != null) { //업로드한 파일이 있으면
			List<SampleFile> sampleFileList = sample.getSampleFile();
			for(SampleFile sampleFile : sampleFileList) {
				long getSize = sampleFile.getSampleFileSize();
				sampleFile.setSampleFileSize((long)Math.round(getSize/(double)1024)); //KB 변환
			}
			model.addAttribute("sampleFileList",sampleFileList);
		}
		return "/sample/uploadList";
	}
	
	@RequestMapping(value="/sample/deleteFile", method = RequestMethod.GET)
	public String removeFile(@RequestParam(value="sampleFileNo")int sampleFileNo, @RequestParam(value="sampleNo")int sampleNo) {
		sampleService.removeFile(sampleFileNo);
		return "redirect:/sample/uploadList?sampleNo=" + sampleNo;
	}
	@RequestMapping(value="/sample/updateFile", method = RequestMethod.POST)
	public String updateFile(SampleRequest sampleRequest, @RequestParam(value="sampleFileNo") int sampleFileNo) {
		sampleService.modifyFile(sampleRequest, sampleFileNo);
		return "redirect:/sample/uploadList?sampleNo=" + sampleRequest.getSampleNo();
	}
	
}
