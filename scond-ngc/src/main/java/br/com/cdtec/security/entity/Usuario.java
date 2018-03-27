package br.com.cdtec.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="tb_usuario", schema="ngc")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "sq_usuario", sequenceName = "sq_usuario", allocationSize = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_usuario")	
	@Column(name="id_usuario")
	private BigInteger idUsuario;
	
	@Column(name="ds_nome")
	private String dsNome;

	@Column(name="ds_login")
	private String dsLogin;
	
	@Column(name="ds_email")
	private String dsEmail;
	
	@Column(name="ds_password")
	private String dsPassword;
	
	@Column(name="id_perfil")
	private BigInteger idPerfil;
	
	
	public BigInteger getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(BigInteger idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getDsNome() {
		return dsNome;
	}

	public void setDsNome(String dsNome) {
		this.dsNome = dsNome;
	}

	public String getDsEmail() {
		return dsEmail;
	}

	public void setDsEmail(String dsEmail) {
		this.dsEmail = dsEmail;
	}

	public String getDsPassword() {
		return dsPassword;
	}

	public void setDsPassword(String dsPassword) {
		this.dsPassword = dsPassword;
	}

	public BigInteger getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(BigInteger idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getDsLogin() {
		return dsLogin;
	}

	public void setDsLogin(String dsLogin) {
		this.dsLogin = dsLogin;
	}
}