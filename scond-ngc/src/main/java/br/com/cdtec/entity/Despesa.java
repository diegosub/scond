package br.com.cdtec.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Proxy;


@Entity
@Table(name="tb_despesa", schema="ngc")
@SequenceGenerator(name = "SQ_DESPESA", sequenceName = "SQ_DESPESA", allocationSize = 1)
@Proxy(lazy = true)
public class Despesa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_DESPESA")
	@Column(name="id_despesa")
	private BigInteger idDespesa;
	
	@Column(name="id_segmento")
	private BigInteger idSegmento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_segmento", insertable = false, updatable = false)
	private Segmento segmento;
	
	@Column(name="id_estabelecimento")
	private BigInteger idEstabelecimento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_estabelecimento", insertable = false, updatable = false)
	private Estabelecimento estabelecimento;
	
	@Column(name="id_cartao")
	private BigInteger idCartao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cartao", insertable = false, updatable = false)
	private Cartao cartao;
		
	@Column(name="id_usuario")
	private BigInteger idUsuario;
	
	@Column(name="vl_despesa")
	private Double vlDespesa;
	
	@Column(name="fg_ativo")
	private String fgAtivo;
	
	@Column(name="fg_cartao")
	private String fgCartao;
	
	@Column(name="ds_observacao")
	private String dsObservacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dt_cadastro")
	private Date dtCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dt_compra")
	private Date dtCompra;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dt_referencia")
	private Date dtReferencia;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dt_manutencao")
	private Date dtManutencao;
	
	@Column(name="vl_mes_cartao")
	private Integer vlMesCartao;
	
	@Transient
	private String strVlDespesa;

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

	public BigInteger getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(BigInteger idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Double getVlDespesa() {
		return vlDespesa;
	}

	public void setVlDespesa(Double vlDespesa) {
		this.vlDespesa = vlDespesa;
	}

	public String getFgAtivo() {
		return fgAtivo;
	}

	public void setFgAtivo(String fgAtivo) {
		this.fgAtivo = fgAtivo;
	}

	public Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
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

	public Date getDtManutencao() {
		return dtManutencao;
	}

	public void setDtManutencao(Date dtManutencao) {
		this.dtManutencao = dtManutencao;
	}

	public String getDsObservacao() {
		return dsObservacao;
	}

	public void setDsObservacao(String dsObservacao) {
		this.dsObservacao = dsObservacao;
	}

	public String getFgCartao() {
		return fgCartao;
	}

	public void setFgCartao(String fgCartao) {
		this.fgCartao = fgCartao;
	}

	public Integer getVlMesCartao() {
		return vlMesCartao;
	}

	public void setVlMesCartao(Integer vlMesCartao) {
		this.vlMesCartao = vlMesCartao;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

	public Segmento getSegmento() {
		return segmento;
	}

	public void setSegmento(Segmento segmento) {
		this.segmento = segmento;
	}

	public String getStrVlDespesa() {
		return strVlDespesa;
	}

	public void setStrVlDespesa(String strVlDespesa) {
		this.strVlDespesa = strVlDespesa;
	}
	
}