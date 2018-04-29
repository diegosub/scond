package br.com.cdtec.dto;

import java.math.BigInteger;
import java.util.Date;

import br.com.cdtec.entity.Estabelecimento;
import br.com.cdtec.entity.Segmento;

public class DespesaDTO extends GenericDTO{
	
	private BigInteger idDespesa;
	private BigInteger idSegmento;
	private BigInteger idEstabelecimento;
	private BigInteger idCartao;
	private Double vlDespesa;
	private String fgCartao;
	private String dsObservacao;
	private Date dtCompra;
	private Date dtReferencia;
	private Integer vlMesCartao;
	
	private Date dtCompraInicio;
	private Date dtCompraFim;
	
	private Segmento segmento;
	private Estabelecimento estabelecimento;
	
	
	public BigInteger getIdDespesa() {
		return idDespesa;
	}
	public void setIdDespesa(BigInteger idDespesa) {
		this.idDespesa = idDespesa;
	}
	public BigInteger getIdSegmento() {
		return idSegmento;
	}
	public void setIdSegmento(BigInteger idSegmento) {
		this.idSegmento = idSegmento;
	}
	public BigInteger getIdEstabelecimento() {
		return idEstabelecimento;
	}
	public void setIdEstabelecimento(BigInteger idEstabelecimento) {
		this.idEstabelecimento = idEstabelecimento;
	}
	public BigInteger getIdCartao() {
		return idCartao;
	}
	public void setIdCartao(BigInteger idCartao) {
		this.idCartao = idCartao;
	}
	public Double getVlDespesa() {
		return vlDespesa;
	}
	public void setVlDespesa(Double vlDespesa) {
		this.vlDespesa = vlDespesa;
	}
	public String getFgCartao() {
		return fgCartao;
	}
	public void setFgCartao(String fgCartao) {
		this.fgCartao = fgCartao;
	}
	public String getDsObservacao() {
		return dsObservacao;
	}
	public void setDsObservacao(String dsObservacao) {
		this.dsObservacao = dsObservacao;
	}
	public Date getDtCompra() {
		return dtCompra;
	}
	public void setDtCompra(Date dtCompra) {
		this.dtCompra = dtCompra;
	}
	public Date getDtReferencia() {
		return dtReferencia;
	}
	public void setDtReferencia(Date dtReferencia) {
		this.dtReferencia = dtReferencia;
	}
	public Integer getVlMesCartao() {
		return vlMesCartao;
	}
	public void setVlMesCartao(Integer vlMesCartao) {
		this.vlMesCartao = vlMesCartao;
	}
	public Date getDtCompraInicio() {
		return dtCompraInicio;
	}
	public void setDtCompraInicio(Date dtCompraInicio) {
		this.dtCompraInicio = dtCompraInicio;
	}
	public Date getDtCompraFim() {
		return dtCompraFim;
	}
	public void setDtCompraFim(Date dtCompraFim) {
		this.dtCompraFim = dtCompraFim;
	}
	public Segmento getSegmento() {
		return segmento;
	}
	public void setSegmento(Segmento segmento) {
		this.segmento = segmento;
	}
	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}
	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}
	
}
