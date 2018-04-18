package br.com.cdtec.dao.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.cdtec.entity.Categoria;

public class CategoriaSpecifications {
	
	private CategoriaSpecifications(){}

	@SuppressWarnings("serial")
	public static Specification<Categoria> dsCategoriaLike(final String dsCategoria){
		return new Specification<Categoria>() {
			public Predicate toPredicate(Root<Categoria> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				if(dsCategoria != null && !dsCategoria.trim().equals("")) {
					return cb.like(cb.lower(root.get("dsCategoria")), "%"+dsCategoria.toLowerCase()+"%");
				}
				
				return null;
			}
		};
	}
	
	@SuppressWarnings("serial")
	public static Specification<Categoria> fgAtivoIgual(final String fgAtivo){
		return new Specification<Categoria>() {
			public Predicate toPredicate(Root<Categoria> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				if(fgAtivo != null && !fgAtivo.trim().equals("") && !fgAtivo.trim().equals("T")) {
					return cb.like(root.get("fgAtivo"), fgAtivo);
				}
				
				return null;
			}
		};
	}
}
