package br.com.cdtec.dao.specifications;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.cdtec.entity.Despesa;

public class DespesaSpecifications {
	
	private DespesaSpecifications(){}
	
	@SuppressWarnings("serial")
	public static Specification<Despesa> idIgual(final BigInteger idDespesa){
		return new Specification<Despesa>() {
			public Predicate toPredicate(Root<Despesa> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				if(idDespesa != null && idDespesa.intValue() > 0) {
					return cb.equal(root.get("idDespesa"), idDespesa);
				}
				
				return null;
			}
		};
	}
	
	@SuppressWarnings("serial")
	public static Specification<Despesa> betweenDatas(final Date dtInicio, final Date dtFim){
		return new Specification<Despesa>() {
			public Predicate toPredicate(Root<Despesa> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				//DATAINICIO DEVE SER OBRIGATORIA E DATAFIM = NOW SE ESTIVER NULA
				if(dtInicio != null && !dtInicio.toString().equals("")) {
					
					Date dtFimAtual;
					
					if(dtFim == null || dtFim.toString().trim().equals("")) {
						GregorianCalendar gc = new GregorianCalendar();
						gc.setTime(dtFim);
						gc.add(Calendar.DAY_OF_MONTH, 1);
						gc.set(Calendar.HOUR, 0);
						gc.set(Calendar.MINUTE, 0);
						gc.set(Calendar.SECOND, 0);
						gc.set(Calendar.MILLISECOND, 0);
						
						dtFimAtual = gc.getTime();
					} else  {
						dtFimAtual = dtFim;
					}
					
					return cb.between(root.get("dtCompra"), dtInicio, dtFimAtual);
							
				}
				
				return null;
			}
		};
	}
	
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
	
	@SuppressWarnings("serial")
	public static Specification<Despesa> leftJoinSegmento() {
		return new Specification<Despesa>() {
			public Predicate toPredicate(Root<Despesa> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				if(query.getResultType() != Long.class){
					root.fetch("segmento", JoinType.INNER);
				}else{
					root.join("segmento", JoinType.INNER);
				}
				return null;
			}
		};
	}
	
	@SuppressWarnings("serial")
	public static Specification<Despesa> leftJoinEstabelecimento() {
		return new Specification<Despesa>() {
			public Predicate toPredicate(Root<Despesa> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				if(query.getResultType() != Long.class){
					root.fetch("estabelecimento", JoinType.LEFT);
				}else{
					root.join("estabelecimento", JoinType.LEFT);
				}
				return null;
			}
		};
	}

}
