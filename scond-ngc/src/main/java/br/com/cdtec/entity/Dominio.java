package br.com.cdtec.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="tb_dominio", schema="ngc")
public class Dominio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id	
	@Column(name="id_dominio")
	private BigInteger idDominio;
	
	@Column(name="ds_dominio")
	private String dsDominio;

	@Column(name="vl_dominio")
	private String vlDominio;
	
	@Column(name="ds_campo")
	private String dsCampo;

	public BigInteger getIdDominio() {
		return idDominio;
	}

	public void setIdDominio(BigInteger idDominio) {
		this.idDominio = idDominio;
	}

	public String getDsDominio() {
		return dsDominio;
	}

	public void setDsDominio(String dsDominio) {
		this.dsDominio = dsDominio;
	}

	public String getVlDominio() {
		return vlDominio;
	}

	public void setVlDominio(String vlDominio) {
		this.vlDominio = vlDominio;
	}

	public String getDsCampo() {
		return dsCampo;
	}

	public void setDsCampo(String dsCampo) {
		this.dsCampo = dsCampo;
	}
}