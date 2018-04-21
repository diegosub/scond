package br.com.cdtec.dao.specifications;

import java.math.BigInteger;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.cdtec.entity.Segmento;

public class SegmentoSpecifications {
	
	private SegmentoSpecifications(){}

	@SuppressWarnings("serial")
	public static Specification<Segmento> dsSegmentoLike(final String dsSegmento){
		return new Specification<Segmento>() {
			public Predicate toPredicate(Root<Segmento> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				if(dsSegmento != null && !dsSegmento.trim().equals("")) {
					return cb.like(cb.lower(root.get("dsSegmento")), "%"+dsSegmento.toLowerCase()+"%");
				}
				
				return null;
			}
		};
	}
	
	@SuppressWarnings("serial")
	public static Specification<Segmento> dsSegmentoIgual(final String dsSegmento){
		return new Specification<Segmento>() {
			public Predicate toPredicate(Root<Segmento> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				if(dsSegmento != null && !dsSegmento.trim().equals("")) {
					return cb.equal(cb.lower(root.get("dsSegmento")), dsSegmento.toLowerCase());
				}
				
				return null;
			}
		};
	}
	
	@SuppressWarnings("serial")
	public static Specification<Segmento> idCategoriaIgual(final BigInteger idCategoria){
		return new Specification<Segmento>() {
			public Predicate toPredicate(Root<Segmento> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				if(idCategoria != null && idCategoria.longValue() > 0) {
					return cb.equal(root.get("idCategoria"), idCategoria);
				}
				
				return null;
			}
		};
	}
	
	@SuppressWarnings("serial")
	public static Specification<Segmento> fgAtivoIgual(final String fgAtivo){
		return new Specification<Segmento>() {
			public Predicate toPredicate(Root<Segmento> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				if(fgAtivo != null && !fgAtivo.trim().equals("") && !fgAtivo.trim().equals("T")) {
					return cb.like(root.get("fgAtivo"), fgAtivo);
				}
				
				return null;
			}
		};
	}
	
	@SuppressWarnings("serial")
	public static Specification<Segmento> innerCategoria() {
		return new Specification<Segmento>() {
			public Predicate toPredicate(Root<Segmento> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				if(query.getResultType() != Long.class){
					root.fetch("categoria", JoinType.LEFT);
				}else{
					root.join("categoria", JoinType.LEFT);
				}
				return null;
			}
		};
	}
}
