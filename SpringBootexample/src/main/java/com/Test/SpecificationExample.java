package com.Test;


/*import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.sensei.app.domain.BBPSCtsReqEnqTxns;*/

/*import static com.sensei.app.domain.specifications.BbpsCtsReqEnqTxnsInfoSpecification.ordDateBetween;
import static com.sensei.app.domain.specifications.BbpsCtsReqEnqTxnsInfoSpecification.urnEquals;
import static org.springframework.data.jpa.domain.Specifications.where;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specifications;*/


public class SpecificationExample {
	//find service method for specification
	/*public List<BBPSCtsReqEnqTxns> find(LocalDate fromDate, LocalDate toDate, String URN) {
		
		Specifications<BBPSCtsReqEnqTxns> specs = where(null);
		if (!StringUtils.isEmpty(URN)) {
			specs = specs.and(urnEquals(URN));
		}
		if (fromDate != null && toDate != null) {
			specs = specs.and(ordDateBetween(fromDate, toDate));
		}
		List<BBPSCtsReqEnqTxns> ctsReqEnqTxnsList = bbpsCtsReqEnqTxnsRepository.findAll(specs);
		return ctsReqEnqTxnsList;
	}*/
		

}
/*
public class BbpsCtsReqEnqTxnsInfoSpecification {
	
	public static Specification<BBPSCtsReqEnqTxns> ordDateBetween(LocalDate fromDate,LocalDate toDate) {
		return new Specification<BBPSCtsReqEnqTxns>() {
			@Override
			public Predicate toPredicate(Root<BBPSCtsReqEnqTxns> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.between(root.<LocalDate> get("ordDt"), fromDate,toDate);//(root.<LocalDate> get("ordDt"), fromDate);
			}
		};
	}
	
	public static Specification<BBPSCtsReqEnqTxns> urnEquals(String URN) {
		return new Specification<BBPSCtsReqEnqTxns>() {
			@Override
			public Predicate toPredicate(Root<BBPSCtsReqEnqTxns> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.<LocalDate> get("urnNum"), URN);
			}
		};
	}

}*/
