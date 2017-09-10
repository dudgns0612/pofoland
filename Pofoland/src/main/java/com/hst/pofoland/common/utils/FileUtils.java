/*
 * @(#) FileUtils.java
 *
 * v1.0.0 / 2017. 8. 13. 
 *
 * Copyright ((c) 2017 by HST, Inc. All Rights Reserved.
 */
package com.hst.pofoland.common.utils;

import java.io.File;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.multipart.MultipartFile;

import com.hst.pofoland.biz.file.vo.FileVO;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * $com.hst.pofoland.common.utils.FileUtils.java
 * 클래스 설명 : 파일처리 관련 유틸 클래스
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
public class FileUtils implements InitializingBean {
    
    private static final double MB = 1024 * 1024;
    //private static final double GB = 1024 * MB;
    
    // 시스템 파일관리 루트
    private String fileRoot;
    
    private Map<String, String> childDirectory;
    
    @Override
    public void afterPropertiesSet() throws Exception {
        String tempImage = childDirectory.get("tempImage");
        String atthFiles = childDirectory.get("atthFiles");
        LoggerManager.info(getClass(), "{}", fileRoot);
        LoggerManager.info(getClass(), "{}", tempImage);
        LoggerManager.info(getClass(), "{}", atthFiles);
        
        // 디렉토리 존재여부 확인 후 생성
        File f = new File(tempImage);
        if (!f.exists()) f.mkdirs();
        f = new File(atthFiles);
        if (!f.exists()) f.mkdirs();
    }
    
    
    /**
     * @return childDirectory 반환
     */
    public Map<String, String> getChildDirectory() {
        return childDirectory;
    }


    /**
     * @param childDirectory 설정할 childDirectory
     */
    public void setChildDirectory(Map<String, String> childDirectory) {
        this.childDirectory = childDirectory;
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
    
    public String getDirectory(String name) {
        if (name == null)
            return "";

        return childDirectory.get(name);
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
        fileVo.setBoardFilepath(childDirectory.get(useDirectoryName) + storedFileName);
        fileVo.setBoardFilesize(fileSize / MB);
        
        return fileVo;
    }
    
}
