package com.hst.pofoland.common.auth.security;

import java.util.ArrayList;
import java.util.List;

public class SecurityAuthorityManager {
	
	private List<SecurityRole> authList = new ArrayList<SecurityRole>();

	public void setAuthorityList(String... authority) {
		for (String auth : authority) {
			authList.add(new SecurityRole(auth));
		}
	}
	
	public List<SecurityRole> getAuthorityList() {
		return authList;
	}
}
