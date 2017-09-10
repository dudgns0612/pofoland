/*
 * @(#) BoardFileController.java
 *
 * v1.0.0 / 2017. 7. 27. 
 *
 * Copyright ((c) 2017 by HST, Inc. All Rights Reserved.
 */
package com.hst.pofoland.biz.file.ctrl;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.inject.Inject;

import org.apache.commons.configuration.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hst.pofoland.biz.file.vo.FileVO;
import com.hst.pofoland.common.annotation.EchoOff;
import com.hst.pofoland.common.utils.FileUtils;
import com.hst.pofoland.common.utils.LoggerManager;
import com.hst.pofoland.common.view.ImageView;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * $com.hst.pofoland.biz.file.BoardFileController.java
 * 클래스 설명 : 파일처리 컨트롤러
 *
 * @author 이현규
 * @since 2017. 7. 27.
 * @version 1.0.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일          수정자         수정내용
 * -------------------------------------------------
 * 2017. 7. 27.   이현규  최초생성
 * </pre>
 */
@Controller
public class BoardFileController {

    @Inject
    private FileUtils fileUtil;
    
    @Inject
    private Configuration config;
    
    @RequestMapping(value="/file/tempImageUpload", method=RequestMethod.POST)
    @ResponseBody
    @EchoOff
    public String tempImageUpload(MultipartHttpServletRequest request) {
        Iterator<String> iter = request.getFileNames();
        
        MultipartFile mFile;
        
        FileVO fileVo = new FileVO();
        
        while(iter.hasNext()) {
            mFile = request.getFile(iter.next());
            
            // MultipartFile 파싱
            fileVo = fileUtil.parseMultipartFile(mFile, "tempImage");
            
            try {
                // 업로드
                mFile.transferTo(new File(fileVo.getBoardFilepath()));
            } catch (IllegalStateException e) {
                LoggerManager.info(getClass(), e.getMessage());
            } catch (IOException e) {
                LoggerManager.info(getClass(), e.getMessage());
            }
        }
        
        return config.getString("upload.dirNames.tempImage") + "/" + fileVo.getFilenameExcludeDirectory();
    }
    
    @RequestMapping(value = "/file/view/{directory}/{storedFileName:.+}", method = RequestMethod.GET)
    public ImageView imageView(@PathVariable("directory")String directory, @PathVariable("storedFileName")String storedFileName) {
        LoggerManager.info(getClass(), "{} / {}", directory, storedFileName);
        return new ImageView(directory, storedFileName);
    }
    
}

