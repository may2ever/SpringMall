package com.example.springmall.sample.service;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.springmall.sample.mapper.SampleMapper;
import com.example.springmall.sample.vo.PageMarker;
import com.example.springmall.sample.vo.Sample;

@Service
@Transactional
public class SampleService {
	@Autowired 
	private SampleMapper sampleMapper;
	public List<Sample> getSelectSampleAll(HashMap<String, Integer> map, PageMarker pageMaker) {
		//페이징 관련 코드
		int totalSampleCount = sampleMapper.selectSampleCount();
		pageMaker.setTotalCount(totalSampleCount);
		pageMaker.executePaging();
		return sampleMapper.selectSampleAll(map);
	}
	public int removeSample(int sampleNo) {
		return sampleMapper.deleteSample(sampleNo);
		
	}
}
