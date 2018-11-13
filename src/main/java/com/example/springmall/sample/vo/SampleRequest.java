package com.example.springmall.sample.vo;

import org.springframework.web.multipart.MultipartFile;

public class SampleRequest {
	private int SampleNo;
	private String sampleId;
	private String samplePw;
	private MultipartFile[] multipartFile;
	public int getSampleNo() {
		return SampleNo;
	}
	public void setSampleNo(int sampleNo) {
		SampleNo = sampleNo;
	}
	public String getSampleId() {
		return sampleId;
	}
	public void setSampleId(String sampleId) {
		this.sampleId = sampleId;
	}
	public String getSamplePw() {
		return samplePw;
	}
	public void setSamplePw(String samplePw) {
		this.samplePw = samplePw;
	}
	public MultipartFile[] getMultipartFile() {
		return multipartFile;
	}
	public void setMultipartFile(MultipartFile[] multipartFile) {
		this.multipartFile = multipartFile;
	}
	
}
