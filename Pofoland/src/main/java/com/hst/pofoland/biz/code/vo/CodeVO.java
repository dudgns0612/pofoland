/*
 * @(#) CodeVO.java
 *
 * v1.0.0 / 2017. 10. 01. 
 *
 * Copyright ((c) 2017 by HST, Inc. All Rights Reserved.
 */
package com.hst.pofoland.biz.code.vo;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * $com.hst.pofoland.biz.code.service.CodeService.java
 * 클래스 설명 : 코드관리 VO 클래스
 *
 * @author 이현규
 * @since 2017. 10. 01.
 * @version 1.0.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일          수정자         수정내용
 * -------------------------------------------------
 * 2017. 10. 01.   이현규  최초생성
 * </pre>
 */
public class CodeVO {

    /** 코드 */
    private String code;
    
    /** 코드명 */
    private String codeName;
    
    /** 코드유형 */
    private String groupCode;
    
    /** 상위코드 */
    private String parentCode;

    /**
     * @return code 반환
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code 설정할 code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return codeName 반환
     */
    public String getCodeName() {
        return codeName;
    }

    /**
     * @param codeName 설정할 codeName
     */
    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    /**
     * @return groupCode 반환
     */
    public String getGroupCode() {
        return groupCode;
    }

    /**
     * @param groupCode 설정할 groupCode
     */
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    /**
     * @return parentCode 반환
     */
    public String getParentCode() {
        return parentCode;
    }

    /**
     * @param parentCode 설정할 parentCode
     */
    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
    
    public CodeVO group(String groupCode) {
        setGroupCode(groupCode);
        return this;
    }
    
    public CodeVO code(String code) {
        setCode(code);
        return this;
    }
    
    public static CodeVO newCode() {
        return new CodeVO();
    }
    
}
