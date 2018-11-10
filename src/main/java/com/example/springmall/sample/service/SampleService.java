package com.example.springmall.sample.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springmall.sample.mapper.SampleMapper;
import com.example.springmall.sample.vo.Sample;

@Service
public class SampleService {
	@Autowired
	private SampleMapper sampleMapper;
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
	totalCount = sampleMapper.selectSampleCount();
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
		return sampleMapper.deleteSample(sampleNo);
	}
	/**
	 * 샘플에 대한 정보를 데이터베이스에 등록
	 *
	 * @param sample 입력된 샘플에 대한 정보
	 */
	public int addSample(Sample sample) {
		return sampleMapper.insertSample(sample);
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
	public int modifySample(Sample sample) {
		return sampleMapper.updateSample(sample);
	}
}
