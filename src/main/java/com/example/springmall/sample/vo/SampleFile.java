package com.example.springmall.sample.vo;

public class SampleFile {
	//auto
	private int sampleFileNo;
	//insert sample
	private int sampleNo;
	//multipartFile
	private String sampleFilePath;
	private String sampleFileName;
	private String sampleFileExt;
	private String sampleFileType;
	private long sampleFileSize;
	private String sampleFileDate;
	public int getSampleFileNo() {
		return sampleFileNo;
	}
	public void setSampleFileNo(int sampleFileNo) {
		this.sampleFileNo = sampleFileNo;
	}
	public int getSampleNo() {
		return sampleNo;
	}
	public void setSampleNo(int sampleNo) {
		this.sampleNo = sampleNo;
	}
	public String getSampleFilePath() {
		return sampleFilePath;
	}
	public void setSampleFilePath(String sampleFilePath) {
		this.sampleFilePath = sampleFilePath;
	}
	public String getSampleFileName() {
		return sampleFileName;
	}
	public void setSampleFileName(String sampleFileName) {
		this.sampleFileName = sampleFileName;
	}
	public String getSampleFileExt() {
		return sampleFileExt;
	}
	public void setSampleFileExt(String sampleFileExt) {
		this.sampleFileExt = sampleFileExt;
	}
	public String getSampleFileType() {
		return sampleFileType;
	}
	public void setSampleFileType(String sampleFileType) {
		this.sampleFileType = sampleFileType;
	}
	public long getSampleFileSize() {
		return sampleFileSize;
	}
	public void setSampleFileSize(long sampleFileSize) {
		this.sampleFileSize = sampleFileSize;
	}
	public String getSampleFileDate() {
		return sampleFileDate;
	}
	public void setSampleFileDate(String sampleFileDate) {
		this.sampleFileDate = sampleFileDate;
	}
	@Override
	public String toString() {
		return "SampleFile [sampleFileNo=" + sampleFileNo + ", sampleNo=" + sampleNo + ", sampleFilePath="
				+ sampleFilePath + ", sampleFileName=" + sampleFileName + ", sampleFileExt=" + sampleFileExt
				+ ", sampleFileType=" + sampleFileType + ", sampleFileSize=" + sampleFileSize + ", sampleFileDate="
				+ sampleFileDate + "]";
	}
	
}
