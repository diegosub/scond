package br.com.cdtec.dao.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.cdtec.entity.Cartao;

public class CartaoSpecifications {
	
	private CartaoSpecifications(){}

	@SuppressWarnings("serial")
	public static Specification<Cartao> dsCartaoLike(final String dsCartao){
		return new Specification<Cartao>() {
			public Predicate toPredicate(Root<Cartao> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				if(dsCartao != null && !dsCartao.trim().equals("")) {
					return cb.like(cb.lower(root.get("dsCartao")), "%"+dsCartao.toLowerCase()+"%");
				}
				
				return null;
			}
		};
	}
	
	@SuppressWarnings("serial")
	public static Specification<Cartao> fgAtivoIgual(final String fgAtivo){
		return new Specification<Cartao>() {
			public Predicate toPredicate(Root<Cartao> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				if(fgAtivo != null && !fgAtivo.trim().equals("") && !fgAtivo.trim().equals("T")) {
					return cb.like(root.get("fgAtivo"), fgAtivo);
				}
				
				return null;
			}
		};
	}
}
