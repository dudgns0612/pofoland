/*
 * @(#) ImageView.java
 *
 * v1.0.0 / 2017. 8. 13. 
 *
 * Copyright ((c) 2017 by HST, Inc. All Rights Reserved.
 */
package com.hst.pofoland.common.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.servlet.view.AbstractView;

import com.hst.pofoland.common.utils.FileUtils;
import com.hst.pofoland.common.utils.LoggerManager;
import com.hst.pofoland.common.utils.PropertyManager;

/**
 * 
 * 시스템명 : 
 * $com.hst.pofoland.common.view.ImageView.java
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
public class ImageView extends AbstractView {
    
    @Inject
    private FileUtils fileUtil;
    
    private String directory;
    private String storedFileName;
    
    public ImageView() {
        this("", "");
    }
    
    public ImageView(String directory, String storedFileName) {
        this.directory = directory;
        this.storedFileName = storedFileName;
        
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    
    
    @Override
    protected void renderMergedOutputModel(Map<String, Object> modelMap, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            IOUtils.copy(new FileInputStream(getFullpath(directory, storedFileName)), response.getOutputStream());
        } catch(IOException e) {
            LoggerManager.error(getClass(), e.getMessage());
        }
    }
    
    private String getFullpath(String directory, String filename) {
        return fileUtil.getFileRoot() + File.separator + directory + File.separator + storedFileName;
    }
    
}
