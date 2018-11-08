package com.example.springmall.sample.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.springmall.sample.vo.Sample;

@Mapper
public interface SampleMapper {
	//1. select all
	List<Sample> selectSampleAll(int currentPage, int rowPerPage);
	int selectSampleCount();
	//2. delete
	int deleteSample(int sampleNo);
	//3. insert
	int insertSample(Sample sample);
	//4. update
	int updateSample(Sample sample);
	//5, select one
	Sample selectSampleOne(int sampleNo);
}
