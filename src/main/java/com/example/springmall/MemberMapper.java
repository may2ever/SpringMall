package com.example.springmall;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
	public int selectCountMember();
}
