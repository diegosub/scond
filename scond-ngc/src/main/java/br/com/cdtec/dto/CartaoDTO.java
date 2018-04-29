package br.com.cdtec.dto;

import java.math.BigInteger;

public class CartaoDTO extends GenericDTO{

	private BigInteger idCartao;	
	private String dsCartao;
	
	
	public BigInteger getIdCartao() {
		return idCartao;
	}
	public void setIdCartao(BigInteger idCartao) {
		this.idCartao = idCartao;
	}
	public String getDsCartao() {
		return dsCartao;
	}
	public void setDsCartao(String dsCartao) {
		this.dsCartao = dsCartao;
	}
}