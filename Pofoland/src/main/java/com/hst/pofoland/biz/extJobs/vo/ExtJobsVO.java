package com.hst.pofoland.biz.extJobs.vo;

import com.hst.pofoland.common.vo.BaseVO;

public class ExtJobsVO extends BaseVO {

	private String companyNm; 	// 기업명	
	private String locCd;		// 지역명
	private String locMcd;		//
	private String locBcd;
	private String jobTypeCd;
	private String jobCategory;
	private String experienceLevel;
	private String eduLv;
	private String salaryCd;
	private String keyword;
	private String url;
	
	public ExtJobsVO() {}
	
	public String getCompanyNm() {
		return companyNm;
	}
	public void setCompanyNm(String companyNm) {
		this.companyNm = companyNm;
	}
	public String getLocCd() {
		return locCd;
	}
	public void setLocCd(String locCd) {
		this.locCd = locCd;
	}
	public String getLocMcd() {
		return locMcd;
	}
	public void setLocMcd(String locMcd) {
		this.locMcd = locMcd;
	}
	public String getLocBcd() {
		return locBcd;
	}
	public void setLocBcd(String locBcd) {
		this.locBcd = locBcd;
	}
	public String getJobTypeCd() {
		return jobTypeCd;
	}
	public void setJobTypeCd(String jobTypeCd) {
		this.jobTypeCd = jobTypeCd;
	}
	public String getJobCategory() {
		return jobCategory;
	}
	public void setJobCategory(String jobCategory) {
		this.jobCategory = jobCategory;
	}
	public String getExperienceLevel() {
		return experienceLevel;
	}
	public void setExperienceLevel(String experienceLevel) {
		this.experienceLevel = experienceLevel;
	}
	public String getEduLv() {
		return eduLv;
	}
	public void setEduLv(String eduLv) {
		this.eduLv = eduLv;
	}
	public String getSalaryCd() {
		return salaryCd;
	}
	public void setSalaryCd(String salaryCd) {
		this.salaryCd = salaryCd;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	

}
