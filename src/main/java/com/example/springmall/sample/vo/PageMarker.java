package com.example.springmall.sample.vo;

public class PageMarker {
	private int totalCount; // 전체 게시물 개수
	private int pageNum; // 현재 페이지번호
	private int contentNum = 10; // 한 페이지에 보여줄 행
	private int startPage = 1; // 현재 페이지 블록의 시작페이지
	private int endPage = 10; // 현재 페이지 블록의 마지막페이지
	private boolean prevPage = false; // 이전페이지 화살표
	private boolean nextPage; // 다음페이지 화살표
	private int currentBlock; // 현재 페이지 블록
	private int lastBlock; // 마지막 페이지 블록
	private int pagePerBlock;// 블록당 보여질 페이지의 개수
	public PageMarker(int pageNum,int contentNum, int pagePerBlock) {
		this.pageNum = pageNum;
		this.contentNum = contentNum;
		this.pagePerBlock = pagePerBlock;
	}
	public int getPagePerBlock() {
		return pagePerBlock;
	}
	public void executePaging() {
		setCurrentBlock(pageNum);
		setLastBlock(totalCount);
		prevAndNextPage(pageNum);
		setStartPage(currentBlock);
		setEndPage(lastBlock, currentBlock);	
	}
	// 화살표 컨트롤하는 메서드 ex) 보이게 안보이게
	public void prevAndNextPage(int pageNum) {
		if (pageNum > 0 && pageNum < pagePerBlock + 1) {
			setPrevPage(false);
			setNextPage(true);
		} else if (getLastBlock() == getCurrentBlock()) {
			setPrevPage(true);
			setNextPage(false);
		} else {
			setPrevPage(true);
			setNextPage(true);
		}
	}
	// 전체 페이지를 구하는 메서드 전체게시글의 갯수 / 한페이지에 보여줄행
	// 전체게시글의 갯수 나누기 한페이지에 보여줄행이 0보다 크다면 +1 해줌으로써 전체페이지를 구할수있다.
	public int calcTotalPage(int totalCount, int contentNum) {
		int totalPage = totalCount / contentNum;
		if (totalCount % contentNum > 0) {
			totalPage++;
		}
		return totalPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getContentNum() {
		return contentNum;
	}
	public void setContentNum(int contentNum) {
		this.contentNum = contentNum;
	}
	public int getStartPage() {
		return startPage;
	}
	// 시작페이지를 구하는메서드 ex) '1'2345678910
	public void setStartPage(int currentBlock) {
		startPage = (currentBlock * pagePerBlock) - (pagePerBlock-1);
	}
	public int getEndPage() {
		return endPage;
	}
	// 마지막 페이지 구하는 메서드 ex) 123456789'10'
	public void setEndPage(int getLastBlock, int getCurrentBlock) {
		if (getLastBlock == getCurrentBlock) {
			endPage = calcTotalPage(totalCount, contentNum);
		} else {
			endPage = getStartPage() + (pagePerBlock-1);
		}
	}
	public boolean isPrevPage() {
		return prevPage;
	}
	public void setPrevPage(boolean prevPage) {
		this.prevPage = prevPage;
	}
	public boolean isNextPage() {
		return nextPage;
	}
	public void setNextPage(boolean nextPage) {
		this.nextPage = nextPage;
	}
	public int getCurrentBlock() {
		return currentBlock;
	}
	// 현재 페이지 블록을 구하는 메서드
	public void setCurrentBlock(int pageNum) {
		currentBlock = pageNum / pagePerBlock;
		if (pageNum % pagePerBlock > 0) {
			currentBlock++;
		}
	}
	public int getLastBlock() {
		return lastBlock;
	}
	public void setLastBlock(int totalCount) {
		lastBlock = totalCount / (pagePerBlock * contentNum);
		if (totalCount % (pagePerBlock * contentNum) > 0) {
			lastBlock++;
		}
	}
}
