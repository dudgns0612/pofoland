/*
 * @(#) CodeServiceImpl.java
 *
 * v1.0.0 / 2017. 10. 5.
 *
 * Copyright ((c) 2017 by HST, Inc. All Rights Reserved.
 */
package com.hst.pofoland.biz.code.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.hst.pofoland.biz.code.dao.CodeDAO;
import com.hst.pofoland.biz.code.service.CodeService;
import com.hst.pofoland.biz.code.vo.CodeVO;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * com.hst.pofoland.biz.code.service.impl.CodeServiceImpl.java
 * 클래스 설명 : 코드관리 서비스 구현체 
 *
 * @author 이현규
 * @since 2017. 10. 5.
 * @version 1.0.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일          수정자         수정내용
 * -------------------------------------------------
 * 2017. 10. 5.   이현규          최초생성
 * </pre>
 */
@Service
public class CodeServiceImpl implements CodeService {

    @Inject
    private CodeDAO codeDao;
    
    @Override
    public List<CodeVO> getCodeList(CodeVO code) {
        return codeDao.selectCodeList(code);
    }

    @Override
    public List<CodeVO> getCodeList(String groupCode) {
        return this.getCodeList(CodeVO.newCode().group(groupCode));
    }

    @Override
    public CodeVO getCode(String groupCode, String code) {
        return codeDao.selectCode(CodeVO.newCode().group(groupCode).code(code));
    }

}
