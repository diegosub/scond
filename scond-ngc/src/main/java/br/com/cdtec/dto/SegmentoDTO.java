package br.com.cdtec.dto;

import java.math.BigInteger;

import br.com.cdtec.entity.Categoria;


public class SegmentoDTO {
	
	private BigInteger idSegmento;	
	private String dsSegmento;	
	private BigInteger idCategoria;	
	private Categoria categoria;
	

	public BigInteger getIdSegmento() {
		return idSegmento;
	}

	public void setIdSegmento(BigInteger idSegmento) {
		this.idSegmento = idSegmento;
	}

	public String getDsSegmento() {
		return dsSegmento;
	}

	public void setDsSegmento(String dsSegmento) {
		this.dsSegmento = dsSegmento;
	}

	public BigInteger getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(BigInteger idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}