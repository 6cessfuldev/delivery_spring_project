package com.ezen.delivery.security.oauth2.userinfo;

import java.util.Map;

public class FacebookUserInfo implements OAuth2UserInfo {
	
	private Map<String, Object> attributes;

	public FacebookUserInfo(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	
	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	@Override
	public String getProviderId() {
		return attributes.get("id").toString();
	}

	@Override
	public String getProvider() {
		// TODO Auto-generated method stub
		return "facebook";
	}

	@Override
	public String getEmail() {
		return attributes.get("email").toString();
	}

	@Override
	public String getName() {
		return attributes.get("name").toString();
	}

}
