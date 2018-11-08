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
	public List<Sample> getSelectSampleAll(HashMap<String, Integer> pageInfo, int currentPage, int rowPerPage) {
	int totalCount;
	int startPage;
	int endPage;
	int currentBlock;
	int lastBlock;
	int pagePerBlock = 10; //한 블록당 보여지는 페이지의 개수
	totalCount = sampleMapper.selectSampleCount();
	
	int totalPage = totalCount / rowPerPage; //총 페이지수
	if (totalCount % rowPerPage > 0) {
		totalPage++;
	}
	currentBlock = currentPage / pagePerBlock;
	if (currentPage % pagePerBlock > 0) {
		currentBlock++;
	} 
	lastBlock = totalCount / (pagePerBlock * rowPerPage);
	if (totalCount % (pagePerBlock * rowPerPage) > 0) {
		lastBlock++;
	}
	
	startPage = (currentBlock * pagePerBlock) - (pagePerBlock - 1);
	
	if(currentBlock == lastBlock) {
		endPage = totalPage;
	}
	else {
		endPage = startPage + (pagePerBlock-1);
	}
	pageInfo.put("currentPage", currentPage);
	pageInfo.put("currentBlock", currentBlock);
	pageInfo.put("lastBlock", lastBlock);
	pageInfo.put("pagePerBlock", pagePerBlock);
	pageInfo.put("startPage", startPage);
	pageInfo.put("endPage", endPage);
	return sampleMapper.selectSampleAll(currentPage, rowPerPage);
	}
	public int removeSample(int sampleNo) {
		return sampleMapper.deleteSample(sampleNo);
	}
	public int addSample(Sample sample) {
		return sampleMapper.insertSample(sample);
	}
	public Sample getSample(int sampleNo) {
		return sampleMapper.selectSampleOne(sampleNo);
	}
	public int modifySample(Sample sample) {
		return sampleMapper.updateSample(sample);
	}
}
