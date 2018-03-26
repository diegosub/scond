package br.com.cdtec.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="tb_categoria", schema="ngc")
public class Categoria implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "sq_categoria", sequenceName = "sq_categoria", allocationSize = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_categoria")	
	@Column(name="id_categoria")
	private BigInteger idCategoria;
	
	@Column(name="ds_categoria")
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