package br.com.cdtec.security.jwt;

import java.io.Serializable;

public class JwtAuthenticationRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String dsLogin;	
	private String dsPassword;

	public JwtAuthenticationRequest() {
		super();
	}
	
	public JwtAuthenticationRequest(String dsLogin, String dsPassword) {
		this.setDsLogin(dsLogin);
		this.setDsPassword(dsPassword);
	}

	public String getDsLogin() {
		return dsLogin;
	}

	public void setDsLogin(String dsLogin) {
		this.dsLogin = dsLogin;
	}

	public String getDsPassword() {
		return dsPassword;
	}

	public void setDsPassword(String dsPassword) {
		this.dsPassword = dsPassword;
	}
}
