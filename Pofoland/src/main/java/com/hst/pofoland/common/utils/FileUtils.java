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
    
    // 에디터용 템프 이미지 업로드 디렉토리
    private String tempImage;
    
    // 게시판, 포트폴리오용 파일 업로드 디렉토리
    private String atthFiles;
    
    @Override
    public void afterPropertiesSet() throws Exception {
        fileRoot = propertyManager.getProperty("file.root");
        tempImage = fileRoot + File.separator + propertyManager.getProperty("tempImage") + File.separator;
        atthFiles = fileRoot + File.separator + propertyManager.getProperty("atthFiles") + File.separator;
        
        // 디렉토리 존재여부 확인 후 생성
        File f = new File(tempImage);
        if (!f.exists()) f.mkdirs();
        f = new File(atthFiles);
        if (!f.exists()) f.mkdirs();
    }
    
    
    /**
     * 업로드된 MultipartFile객체 FileVO로 변환
     * @param mFile 업로드된 파일
     * @return
     */
    public FileVO parseMultipartFile(MultipartFile mFile) {
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
        
        // 파일 크기 구하기
        double fileSize = mFile.getSize();
        
        // 파싱결과값 FileVO에 셋
        fileVo.setBoardFilename(fileName);
        fileVo.setBoardFiletype(fileType);
        fileVo.setBoardFilepath(tempImage + storedFileName);
        fileVo.setBoardFilesize(fileSize / MB);
        
        return fileVo;
    }
    
}
