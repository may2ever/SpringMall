package com.example.springmall.sample.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.springmall.sample.vo.Sample;

@Mapper
public interface SampleMapper {
	//1. select all
	List<Sample> selectSampleAll(@Param("currentPage")int currentPage, @Param("rowPerPage")int rowPerPage);
	 //select all count
	int selectSampleCount();
	//2. delete
	int deleteSample(int sampleNo);
	//3. insert
	int insertSample(Sample sample);
	//4. update
	int updateSample(Sample sample);
	//5, select one
	Sample selectSampleOne(int sampleNo);
	//6. select search
	List<Sample> selectSearchSample(@Param("currentPage")int currentPage, @Param("rowPerPage")int rowPerPage, @Param("searchQuery")String searchQuery, @Param("searchType")String searchType);
	//6. select search count
	int selectSearchCount(@Param("searchQuery")String searchQuery, @Param("searchType")String searchType);
	
	Sample selectSampleAndFile(int sampleNo);
}
