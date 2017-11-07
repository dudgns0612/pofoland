package com.hst.pofoland.biz.extJobs.vo;

import org.springframework.stereotype.Component;

import com.hst.pofoland.common.vo.BaseVO;


public class ExtJobsVO extends BaseVO {

	private String id;
	private String url;
	private String company; 				// 기업명	
	private String title;					// 게시글 제목
	private String location;				// 지역명
	private String jobType;					// 정규,계약직 구분
	private String experienceLevel; 		// 요구경력
	private String requiredEducadtionLevel;	// 최종학력
	private String keyword;					// 키워드
	private String salary;					// 연봉
	/*
	private String locMcd;		
	private String locBcd;
	private String jobTypeCd;
	private String jobCategory;
	private String eduLv;
	*/
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public String getExperienceLevel() {
		return experienceLevel;
	}
	public void setExperienceLevel(String experienceLevel) {
		this.experienceLevel = experienceLevel;
	}
	public String getRequiredEducadtionLevel() {
		return requiredEducadtionLevel;
	}
	public void setRequiredEducadtionLevel(String requiredEducadtionLevel) {
		this.requiredEducadtionLevel = requiredEducadtionLevel;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	
	
	
	
	
	
	
	
	

}
