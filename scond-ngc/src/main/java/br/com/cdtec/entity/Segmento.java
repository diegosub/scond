package br.com.cdtec.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Proxy;


@Entity
@Table(name="tb_segmento", schema="ngc")
@SequenceGenerator(name = "SQ_SEGMENTO", sequenceName = "SQ_SEGMENTO", allocationSize = 1)
@Proxy(lazy = true)
public class Segmento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_SEGMENTO")
	@Column(name="id_segmento")
	private BigInteger idSegmento;
	
	@Column(name="ds_segmento")
	private String dsSegmento;
	
	@Column(name="id_categoria")
	private BigInteger idCategoria;
	
	@Column(name="id_usuario")
	private BigInteger idUsuario;
	
	@Column(name="fg_ativo")
	private String fgAtivo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dt_cadastro")
	private Date dtCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dt_manutencao")
	private Date dtManutencao;

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

	public BigInteger getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(BigInteger idUsuario) {
		this.idUsuario = idUsuario;
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

	public Date getDtManutencao() {
		return dtManutencao;
	}

	public void setDtManutencao(Date dtManutencao) {
		this.dtManutencao = dtManutencao;
	}
}