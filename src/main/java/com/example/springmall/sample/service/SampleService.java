package com.example.springmall.sample.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.springmall.sample.mapper.SampleFileMapper;
import com.example.springmall.sample.mapper.SampleMapper;
import com.example.springmall.sample.vo.Sample;
import com.example.springmall.sample.vo.SampleFile;
import com.example.springmall.sample.vo.SampleRequest;

@Service
@Transactional
public class SampleService {
	@Autowired
	private SampleMapper sampleMapper;
	@Autowired
	private SampleFileMapper sampleFileMapper;
	/**
	 * 현재 페이지와 페이지당 리스트수를 통해 데이터베이스에서 샘플을 가져오고 페이징을 위한 정보를 세팅
	 * 
	 * @param pagingInfo 페이징 데이터
	 * @param currentPage 현재 보여지고 있는 페이지의 번호
	 * @param rowPerPage 한 페이지당 보여지는 리스트의 개수
	 * @param pagePerScreen 한 화면에 보여지는 페이지의 개수(10 페이지씩 보여질때 1~10 페이지를 첫번째화면 11~20 두번째 화면라고 본다)
	 * @return 샘플의 리스트
	 */
	public List<Sample> getSelectSample(HashMap<String, Object> pagingInfo, int currentPage, int rowPerPage, int pagePerScreen) {
	int totalCount; //리스트의 총 개수
	int currentScreen; //현재 화면 번호
	int lastScreen; //마지막 화면 번호
	int currentScreenPage; //현재 화면에 보이는 페이지의 개수
	int startScreenPage; //현재 화면에 보이는 페이지의 시작 번호
	int lastPage; //마지막 페이지번호
	List<Sample> sampleList;
	if(pagingInfo.get("searchQuery") == null) {
		totalCount = sampleMapper.selectSampleCount();
		sampleList = sampleMapper.selectSampleAll((currentPage - 1) * rowPerPage, rowPerPage);
	}
	else {
		String searchQuery = (String)pagingInfo.get("searchQuery");
		String searchType = (String)pagingInfo.get("searchType");
		totalCount = sampleMapper.selectSearchCount(searchQuery, searchType);
		sampleList = sampleMapper.selectSearchSample((currentPage - 1) * rowPerPage, rowPerPage, searchQuery, searchType);
		for(Sample sample:sampleList) {
			if(searchType.equals("sample_id")) {
				int startIndex = sample.getSampleId().toLowerCase().indexOf(searchQuery.toLowerCase());
				String query = sample.getSampleId().substring(startIndex,startIndex + searchQuery.length());
				sample.setSampleId(sample.getSampleId().replaceAll(query, "<b>"+query+"</b>"));
			}
			else if(searchType.equals("sample_pw")) {
				int startIndex = sample.getSamplePw().toLowerCase().indexOf(searchQuery.toLowerCase());
				String query = sample.getSamplePw().substring(startIndex,startIndex + searchQuery.length());
				sample.setSamplePw(sample.getSamplePw().replaceAll(query, "<b>"+query+"</b>"));
			}
		}
	}
	lastPage = (int)Math.ceil((double)totalCount / rowPerPage);
	currentScreen = (int)Math.ceil((double)currentPage / pagePerScreen); // 1~10 1번째 화면, 11~20 2번째 화면 
	lastScreen = (int) Math.ceil((double)totalCount / (rowPerPage * pagePerScreen));// 한 페이지당 보여지는 리스트의 개수 10 * 한 화면에 보여지는 페이지의 개수 10 이면 총 100개가 하나의 화면에서 보여진다  리스트의 총개수를 100으로 나누어서 나누어 떨어지지 않으면 하나의 화면이 더 있다
	startScreenPage = (currentScreen - 1) * pagePerScreen + 1; //한 화면에 10개의 페이지가 보여질때 첫번째 화면의 시작 페이지는 1, 두번째화면은 11..)
	if(currentScreen == lastScreen) {//마지막 화면일때
		if(totalCount % (rowPerPage * pagePerScreen) != 0) { //마지막 화면에 보이는 리스트 개수(rowPerPage * pagePerScreen)가 100개이면 totalCount % (rowPerPage * pagePerScreen)은 0이 되기때문에 pagePerScreen 값을 넣어주어야 한다
			int temp = totalCount % (rowPerPage * pagePerScreen);//마지막 화면에서의 리스트의 개수
			currentScreenPage = (int) Math.ceil((double) temp / rowPerPage);
		}
		else {
			currentScreenPage = pagePerScreen;
		}
	}
	else {
		currentScreenPage = pagePerScreen;
	}
	pagingInfo.put("currentPage", currentPage);
	pagingInfo.put("lastPage", lastPage);
	pagingInfo.put("currentScreen", currentScreen);
	pagingInfo.put("lastScreen", lastScreen);
	pagingInfo.put("pagePerScreen", pagePerScreen);
	pagingInfo.put("currentScreenPage", currentScreenPage);
	pagingInfo.put("startScreenPage", startScreenPage);
	return sampleList;
	}
	/**
	 * 선택한 샘플데이터 번호를 통해 샘플을 삭제
	 *
	 * @param sampleNo 샘플데이터의 번호
	 */
	public int removeSample(int sampleNo) {
		Sample sample = sampleMapper.selectSampleOne(sampleNo);
		String filePath = sample.getSampleFile().getSampleFilePath();
		String fileExt = sample.getSampleFile().getSampleFileExt();
		String fileRealName = sample.getSampleFile().getSampleFileRealName();
		File file = new File(filePath + "\\" + fileRealName + "." + fileExt);
		int transaction1 = sampleFileMapper.deleteSampleFile(sampleNo);
		int transaction2 = sampleMapper.deleteSample(sampleNo);
		file.delete();
		return transaction1 + transaction2;
	}
	/**
	 * 샘플에 대한 정보를 데이터베이스에 등록
	 *
	 * @param sample 입력된 샘플에 대한 정보
	 */
	public int addSample(SampleRequest sampleRequest, String realPath) {
		/*
		 * sampleRequest --> samPle, sampleFIle로 변환 필요
		 * 1. multipartfile 파일데이터 -> 저장
		 * 2. multipartfile 정보-> 새로운정보 추가 ->sampleFile
		 * 3. 
		 */
		//1.
		Sample sample = new Sample();
		sample.setSampleId(sampleRequest.getSampleId());
		sample.setSamplePw(sampleRequest.getSamplePw());
		int transaction1 = sampleMapper.insertSample(sample); // ai -> sampleNo 트랜잭션 1
		//2
		SampleFile sampleFile = new SampleFile();
		MultipartFile[] multiPartFile = sampleRequest.getMultipartFile();
		// 1. sampleFileNo : AutoIntrement
		int transaction2 = 0;
		for(MultipartFile multipart :multiPartFile) {
			if(multipart.getSize() != 0) {
				// 2. SampleNo
				sampleFile.setSampleNo(sample.getSampleNo()); //insertSample(sample) 후에 pk값이 sample변수에 채워진다.
				// 3. sampleFilePath
				sampleFile.setSampleFilePath(realPath);
				String originalFileName = multipart.getOriginalFilename();
				int index = originalFileName.indexOf(".");
				// 4. 이름
				String fileName = originalFileName.substring(0,index);
				sampleFile.setSampleFileName(fileName);
				// 5. 확장자
				String ext = originalFileName.substring(fileName.length()+1, originalFileName.length());
				sampleFile.setSampleFileExt(ext);
				// 6. 타입
				sampleFile.setSampleFileType(multipart.getContentType());
				// 7. 크기
				sampleFile.setSampleFileSize(multipart.getSize());
				
				String realFileName = UUID.randomUUID().toString();
				sampleFile.setSampleFileRealName(realFileName);
				
				//내가 원하는 이름의 빈파일 생성
				File file = new File(realPath + "/" + realFileName + "." + ext);
				transaction2 = sampleFileMapper.insertSampleFile(sampleFile); //트랜잭션 2
				//multipartFile파일을 빈파일로 복사
				try {
					multipart.transferTo(file);
				}
				catch(IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
		}
		// 1 + 2 -> @Transactional
		return transaction1 + transaction2;
	}
	/**
	 * 샘플에 대한 정보하나를 가져온다
	 *
	 * @param sampleNo 해당 샘플 데이터의 번호
	 */
	public Sample getSample(int sampleNo) {
		return sampleMapper.selectSampleOne(sampleNo);
	}
	/**
	 * 선택된 샘플에 대한 정보를 데이터베이스에서 수정
	 *
	 * @param sample 입력된 샘플에 대한 정보
	 */
	public int modifySample(SampleRequest sampleRequest) {
		Sample sample = new Sample();
		sample.setSampleNo(sampleRequest.getSampleNo());
		sample.setSampleId(sampleRequest.getSampleId());
		sample.setSamplePw(sampleRequest.getSamplePw());
		int transaction1 = sampleMapper.updateSample(sample);
		MultipartFile[] multiPartFile = sampleRequest.getMultipartFile();
		SampleFile sampleFile = sampleFileMapper.selectSampleFileFromSampleNo(sampleRequest.getSampleNo());
		System.out.println(sampleFile + "<=======");
		String path = sampleFile.getSampleFilePath();
		String realname = sampleFile.getSampleFileRealName();
		String preExt = sampleFile.getSampleFileExt();
		File preFile = new File(path + "\\" + realname + "." + preExt);
		int transaction2 = 0;
		for(MultipartFile multipart : multiPartFile) {
			String originalFileName = multipart.getOriginalFilename();
			int index = originalFileName.indexOf(".");
			// 1. 이름
			String fileName = originalFileName.substring(0,index);
			sampleFile.setSampleFileName(fileName);
			// 2. 확장자
			String ext = originalFileName.substring(fileName.length()+1, originalFileName.length());
			sampleFile.setSampleFileExt(ext);
			// 3. 타입
			sampleFile.setSampleFileType(multipart.getContentType());
			// 4. 크기
			sampleFile.setSampleFileSize(multipart.getSize());
			transaction2 = sampleFileMapper.updateSampleFile(sampleFile);
			preFile.delete(); //기존에 있던 파일 삭제
			try {
				multipart.transferTo(new File(path + "\\" + realname + "." + ext));
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}

		return transaction1 + transaction2;
	}
	/**
	 * 업로드한 샘플파일에 대한 데이터 하나를 가져온다
	 *
	 * @param sampleFileNo 업로드된 샘플파일의 등록번호
	 * @return 해당 등록번호에 대한 샘플파일 데이터
	 */
	public SampleFile getSampleFileFromSampleNo(int sampleFileNo) {
		return sampleFileMapper.selectSampleFileFromFileNo(sampleFileNo);
	}
}
