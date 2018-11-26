package com.example.springmall;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	@Autowired
	private MemberMapper memberMapper;
	public Map<String,String> GetCountMember() {
		return memberMapper.selectMember();
	}

}
