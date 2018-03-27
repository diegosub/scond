package br.com.cdtec.security.jwt;

import java.io.Serializable;

public class JwtAuthenticationRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String login;	
	private String password;

	public JwtAuthenticationRequest() {
		super();
	}
	
	public JwtAuthenticationRequest(String login, String senha) {
		this.setLogin(login);
		this.setPassword(password);
	}
	
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
