package com.example.springmall;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
	public Map<String,String> selectMember();
}
