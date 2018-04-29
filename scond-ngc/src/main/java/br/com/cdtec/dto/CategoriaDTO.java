package br.com.cdtec.dto;

import java.math.BigInteger;

public class CategoriaDTO extends GenericDTO {

	private BigInteger idCategoria;	
	private String dsCategoria;
	public BigInteger getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(BigInteger idCategoria) {
		this.idCategoria = idCategoria;
	}
	public String getDsCategoria() {
		return dsCategoria;
	}
	public void setDsCategoria(String dsCategoria) {
		this.dsCategoria = dsCategoria;
	}
}