package com.hst.pofoland.common.utils;

public enum StatusUtils {
	SARAMIN_API_URL("http://api.saramin.co.kr/job-search");
	
	private String firstValue;
	
	StatusUtils(String firstValue) {
		this.firstValue = firstValue;
	}

	public String getFirstValue() {
		return firstValue;
	}

	public void setFirstValue(String FirstValue) {
		this.firstValue = FirstValue;
	}
	
	
}
