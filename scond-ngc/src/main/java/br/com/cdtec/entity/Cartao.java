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
@Table(name="tb_cartao", schema="ngc")
@SequenceGenerator(name = "SQ_CARTAO", sequenceName = "SQ_CARTAO", allocationSize = 1)
@Proxy(lazy = true)
public class Cartao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CARTAO")
	@Column(name="id_cartao")
	private BigInteger idCartao;
	
	@Column(name="ds_cartao")
	private String dsCartao;
	
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