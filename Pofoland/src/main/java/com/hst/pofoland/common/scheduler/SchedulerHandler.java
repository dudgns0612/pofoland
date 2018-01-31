package com.hst.pofoland.common.scheduler;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import com.hst.pofoland.biz.user.dao.UserDAO;
import com.hst.pofoland.common.utils.FileUtils;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * $com.hst.pofoland.common.scheduler.SchedulerHandler.java
 * 클래스 설명 : 특정 시간마다 실행해야하는 로직 수행
 *
 * @author 김영훈
 * @since 2017. 9. 18
 * @version 1.0.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자			수정내용
 * -------------------------------------------------
 * 2017. 9. 18.		김영훈			최초생성
 * </pre>
 */

public class SchedulerHandler {
	
	@Inject
	FileUtils fileUtils;
	
	@Inject
	UserDAO userDAO;
	
	public void deleteNoneProfile() {
		
		File userDir = new File(fileUtils.getDirectory("userProfile"));
		File[] profileStoreList = userDir.listFiles();
		
		List<String> profileDBList = userDAO.selectAllUserProfile();
		
		for (int i=0 ; i < profileStoreList.length ; i++) {
			File file = profileStoreList[i];
			String profileName = file.getName();
			
			boolean check = false;
			if (!("default_profile").contains(profileName)) {
				for (int j=0 ; j < profileDBList.size() ; j++) {
					if (profileName.equals(profileDBList.get(j))) {
						check = true;
						break;
					}
				}
			}
			if (!check) {
				file.delete();
			}
		}
	}
}
