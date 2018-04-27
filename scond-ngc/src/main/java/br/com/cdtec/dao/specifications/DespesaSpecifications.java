package br.com.cdtec.dao.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.cdtec.entity.Despesa;

public class DespesaSpecifications {
	
	private DespesaSpecifications(){}
	
	@SuppressWarnings("serial")
	public static Specification<Despesa> fgAtivoIgual(final String fgAtivo){
		return new Specification<Despesa>() {
			public Predicate toPredicate(Root<Despesa> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				if(fgAtivo != null && !fgAtivo.trim().equals("") && !fgAtivo.trim().equals("T")) {
					return cb.like(root.get("fgAtivo"), fgAtivo);
				}
				
				return null;
			}
		};
	}
}
