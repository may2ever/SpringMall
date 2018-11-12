package com.example.springmall.sample.vo;

public class Sample {
	private int sampleNo;
	private String sampleId;
	private String samplePw;
	private SampleFile sampleFile;
	public SampleFile getSampleFile() {
		return sampleFile;
	}

	public void setSampleFile(SampleFile sampleFile) {
		this.sampleFile = sampleFile;
	}

	public Sample() {
	}
	
	public Sample(int sampleNo, String sampleId, String samplePw) {
		this.sampleNo = sampleNo;
		this.sampleId = sampleId;
		this.samplePw = samplePw;
	}
	
	public int getSampleNo() {
		return sampleNo;
	}
	public void setSampleNo(int sampleNo) {
		this.sampleNo = sampleNo;
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
	@Override
	public String toString() {
		return "Sample [sampleNo=" + sampleNo + ", sampleId=" + sampleId + ", samplePw=" + samplePw + "]";
	}
}
