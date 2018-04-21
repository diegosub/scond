package br.com.cdtec.dao.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.cdtec.entity.Estabelecimento;

public class EstabelecimentoSpecifications {
	
	private EstabelecimentoSpecifications(){}

	@SuppressWarnings("serial")
	public static Specification<Estabelecimento> dsEstabelecimentoLike(final String dsEstabelecimento){
		return new Specification<Estabelecimento>() {
			public Predicate toPredicate(Root<Estabelecimento> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				if(dsEstabelecimento != null && !dsEstabelecimento.trim().equals("")) {
					return cb.like(cb.lower(root.get("dsEstabelecimento")), "%"+dsEstabelecimento.toLowerCase()+"%");
				}
				
				return null;
			}
		};
	}
	
	@SuppressWarnings("serial")
	public static Specification<Estabelecimento> fgAtivoIgual(final String fgAtivo){
		return new Specification<Estabelecimento>() {
			public Predicate toPredicate(Root<Estabelecimento> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				if(fgAtivo != null && !fgAtivo.trim().equals("") && !fgAtivo.trim().equals("T")) {
					return cb.like(root.get("fgAtivo"), fgAtivo);
				}
				
				return null;
			}
		};
	}
}
