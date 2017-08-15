/*
 * @(#) FileUtils.java
 *
 * v1.0.0 / 2017. 8. 13. 
 *
 * Copyright ((c) 2017 by HST, Inc. All Rights Reserved.
 */
package com.hst.pofoland.common.utils;

import java.io.File;
import java.util.UUID;

import javax.inject.Inject;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.hst.pofoland.biz.file.vo.FileVO;

/**
 * 
 * 시스템명 : 
 * $com.hst.pofoland.common.utils.FileUtils.java
 * 클래스 설명 : 
 *
 * @author 이현규
 * @since 2017. 8. 13.
 * @version 1.0.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일          수정자         수정내용
 * -------------------------------------------------
 * 2017. 8. 13.   이현규  최초생성
 * </pre>
 */
@Component
public class FileUtils implements InitializingBean {
    
    @Inject
    private PropertyManager propertyManager;
    
    private static final double MB = 1024 * 1024;
    private static final double GB = 1024 * MB;
    
    // 시스템 파일관리 루트
    private String fileRoot;
    
    private String tempImageName;
    private String atthFilesName;
    
    @Override
    public void afterPropertiesSet() throws Exception {
        fileRoot = propertyManager.getProperty("file.root");
        tempImageName = propertyManager.getProperty("tempImage") + File.separator;
        atthFilesName = fileRoot + File.separator + propertyManager.getProperty("atthFiles") + File.separator;
        String tempImage = fileRoot + File.separator + propertyManager.getProperty("tempImage") + File.separator;
        String atthFiles = fileRoot + File.separator + propertyManager.getProperty("atthFiles") + File.separator;
        
        // 디렉토리 존재여부 확인 후 생성
        File f = new File(tempImage);
        if (!f.exists()) f.mkdirs();
        f = new File(atthFiles);
        if (!f.exists()) f.mkdirs();
    }
    
    
    /**
     * @return fileRoot 반환
     */
    public String getFileRoot() {
        return fileRoot;
    }


    /**
     * @param fileRoot 설정할 fileRoot
     */
    public void setFileRoot(String fileRoot) {
        this.fileRoot = fileRoot;
    }


    /**
     * @return tempImageName 반환
     */
    public String getTempImageName() {
        return tempImageName;
    }


    /**
     * @param tempImageName 설정할 tempImageName
     */
    public void setTempImageName(String tempImageName) {
        this.tempImageName = tempImageName;
    }


    /**
     * @return atthFilesName 반환
     */
    public String getAtthFilesName() {
        return atthFilesName;
    }


    /**
     * @param atthFilesName 설정할 atthFilesName
     */
    public void setAtthFilesName(String atthFilesName) {
        this.atthFilesName = atthFilesName;
    }


    /**
     * 업로드된 MultipartFile객체 FileVO로 변환
     * @param mFile 업로드된 파일
     * @return
     */
    public FileVO parseMultipartFile(MultipartFile mFile, String useDirectoryName) {
        FileVO fileVo = new FileVO();
        
        // 원본 파일명 추출
        String originalFileName = mFile.getOriginalFilename();
        String storedFileName = null;
        
        // 확장자 인덱스 구하기
        int extIdx = originalFileName.lastIndexOf(".");
        System.out.println(originalFileName);
        // 파일명 확장자 분리
        String fileName = originalFileName.substring(0, extIdx);
        String fileType = originalFileName.substring(extIdx + 1, originalFileName.length()); 
        
        // 저장 파일명 생성
        storedFileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + fileType;
        
        // 시퀀스말고 파일명으로 파일 참조 시 사용할 값
        fileVo.setFilenameExcludeDirectory(storedFileName);
        
        // 파일 크기 구하기
        double fileSize = mFile.getSize();
        
        // 파싱결과값 FileVO에 셋
        fileVo.setBoardFilename(fileName);
        fileVo.setBoardFiletype(fileType);
        fileVo.setBoardFilepath(fileRoot + File.separator + useDirectoryName + File.separator + storedFileName);
        fileVo.setBoardFilesize(fileSize / MB);
        
        return fileVo;
    }
    
}
