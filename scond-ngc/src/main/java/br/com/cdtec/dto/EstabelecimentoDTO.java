package br.com.cdtec.dto;

import java.math.BigInteger;

public class EstabelecimentoDTO {

	private BigInteger idEstabelecimento;
	private String dsEstabelecimento;
	
	
	public BigInteger getIdEstabelecimento() {
		return idEstabelecimento;
	}
	public void setIdEstabelecimento(BigInteger idEstabelecimento) {
		this.idEstabelecimento = idEstabelecimento;
	}
	public String getDsEstabelecimento() {
		return dsEstabelecimento;
	}
	public void setDsEstabelecimento(String dsEstabelecimento) {
		this.dsEstabelecimento = dsEstabelecimento;
	}
	
}