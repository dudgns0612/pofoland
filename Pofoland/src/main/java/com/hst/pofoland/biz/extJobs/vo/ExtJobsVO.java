package com.hst.pofoland.biz.extJobs.vo;

import org.springframework.stereotype.Component;

import com.hst.pofoland.common.vo.BaseVO;


public class ExtJobsVO extends BaseVO {

	private String id;
	private String url;
	private String company; 				// 기업명	
	private String name;					// 기업명 url
	private String title;					// 게시글 제목
	private String location;				// 지역명
	private String jobtype;					// 정규,계약직 구분
	private String experiencelevel; 		// 요구경력
	private String requirededucationlevel;	// 최종학력
	private String keyword;					// 키워드
	private String salary;					// 연봉
	/*
	 *  <experience-level code="0" min="0" max="0">경력무관</experience-level>
        <required-education-level code="0">학력무관</required-education-level>
	private String locMcd;		
	private String locBcd;
	private String jobTypeCd;
	private String jobCategory;
	private String eduLv;
	*/
	
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	public String getJobtype() {
		return jobtype;
	}
	public void setJobtype(String jobtype) {
		this.jobtype = jobtype;
	}
	public String getExperiencelevel() {
		return experiencelevel;
	}
	public void setExperiencelevel(String experiencelevel) {
		this.experiencelevel = experiencelevel;
	}
	public String getRequirededucationlevel() {
		return requirededucationlevel;
	}
	public void setRequirededucationlevel(String requirededucationlevel) {
		this.requirededucationlevel = requirededucationlevel;
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
