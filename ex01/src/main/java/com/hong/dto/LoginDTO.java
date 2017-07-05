package com.hong.dto;

public class LoginDTO {

	private String userid;
	private String upw;
	private boolean useCookie;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUpw() {
		return upw;
	}
	public void setUpw(String upw) {
		this.upw = upw;
	}
	public boolean isUseCookie() {
		return useCookie;
	}
	public void setUseCookie(boolean useCookie) {
		this.useCookie = useCookie;
	}

	@Override
	public String toString() {
		return "LoginDTO [userid=" + userid + ", upw=" + upw + ", useCookie=" + useCookie + "]";
	}
}
