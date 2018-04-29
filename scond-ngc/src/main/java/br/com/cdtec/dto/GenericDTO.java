package br.com.cdtec.dto;

import java.math.BigInteger;

public class GenericDTO {
	
	private String fgAtivo;
	private String dtCadastro;
	private String dtManutencao;
	private BigInteger idUsuario;
	
	public GenericDTO() {}
	
	public String getFgAtivo() {
		return fgAtivo;
	}
	public void setFgAtivo(String fgAtivo) {
		this.fgAtivo = fgAtivo;
	}
	public String getDtCadastro() {
		return dtCadastro;
	}
	public void setDtCadastro(String dtCadastro) {
		this.dtCadastro = dtCadastro;
	}
	public String getDtManutencao() {
		return dtManutencao;
	}
	public void setDtManutencao(String dtManutencao) {
		this.dtManutencao = dtManutencao;
	}
	public BigInteger getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(BigInteger idUsuario) {
		this.idUsuario = idUsuario;
	}
	
}
