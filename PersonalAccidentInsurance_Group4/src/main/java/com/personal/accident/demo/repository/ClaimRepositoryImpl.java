package com.personal.accident.demo.repository;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.personal.accident.demo.dto.ClaimForm;
import com.personal.accident.demo.dto.PolicyHolder;
import com.personal.accident.demo.model.Proposal;

@Repository
public class ClaimRepositoryImpl implements ClaimRepositoryCustom{

	@Autowired
	EntityManager em;
	
	

	@Override
	public List<PolicyHolder> findPolicy(PolicyHolder p,int id) {
		
		// TODO Auto-generated method stub
		
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<PolicyHolder> cq=cb.createQuery(PolicyHolder.class);
		System.out.println("----------1----------");
		
		Root<PolicyHolder> policy=cq.from(PolicyHolder.class);
				
		System.out.println("----------2----------");
		
		Predicate predicate=cb.equal(policy.get("p_no"), p.getP_no());
		Predicate predicate2 = cb.equal(policy.get("user"), id);
		/*
		 * Predicate predicate3 = cb.equal(policy.get("status_checking"), "Complete");
		 */
		
		cq.where(cb.and(predicate,predicate2)).distinct(true);
		
		TypedQuery<PolicyHolder> query = em.createQuery(cq); 
		List<PolicyHolder> list=query.getResultList();
		System.out.println(list);
		for(PolicyHolder pholder:list) {
			
			System.out.println("-----Policy---"+pholder.getP_no()+"\t"+pholder.getFirstName()+"\t"+pholder.getState()+"\t"+pholder.getClaim());
		}
		return list;
	}

}
