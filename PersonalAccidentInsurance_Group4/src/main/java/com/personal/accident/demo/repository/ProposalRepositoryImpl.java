package com.personal.accident.demo.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.personal.accident.demo.dto.PolicyHolder;

@Repository
public class ProposalRepositoryImpl implements ProposalCustom{

	@Autowired
	EntityManager em;
	
	@Override
	public List<PolicyHolder> getProposal(int id) {

		// TODO Auto-generated method stub
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<PolicyHolder> cq = cb.createQuery(PolicyHolder.class);
		System.out.println("---------Repository1-------------");
		Root<PolicyHolder> usrr = cq.from(PolicyHolder.class);
		Predicate p = cb.equal(usrr.get("user"), id);
		Predicate p2=cb.notEqual(usrr.get("status_checking"), "delete");
		
		cq.where(cb.and(p,p2)).distinct(true);
		
		TypedQuery<PolicyHolder> typedQuery = em.createQuery(cq);
		List<PolicyHolder> resultList = new ArrayList<PolicyHolder>();

		try {
			resultList = typedQuery.getResultList();
			System.out.println("*----Claim list---*" + resultList.get(0).getClaim()+"*---UserList----*"+resultList.get(0).getUser()+"*---Premium----*"+resultList.get(0).getPremium().getLampSum()+"******Payment****"+resultList.get(0).getPayment()+"****Beneficiary****"+resultList.get(0).getBeneficiary());
		} catch (NoResultException nre) {
			System.out.println("ERROR");		
		}
		return resultList;
	
	}

	@Override
	public List<PolicyHolder> getProposalApproved(int id) {
		// TODO Auto-generated method stub

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<PolicyHolder> cq = cb.createQuery(PolicyHolder.class);
		System.out.println("---------Repository1-------------");
		Root<PolicyHolder> usrr = cq.from(PolicyHolder.class);
		Predicate p = cb.equal(usrr.get("user"), id);
		Predicate p2 = cb.equal(usrr.get("status_checking"), "Complete");
		
		cq.where(cb.and(p,p2)).distinct(true);
		TypedQuery<PolicyHolder> typedQuery = em.createQuery(cq);
		List<PolicyHolder> resultList = new ArrayList<PolicyHolder>();

		try {
			resultList = typedQuery.getResultList();
			System.out.println("*----Claim list---*" + resultList.get(0).getClaim()+"*---UserList----*"+resultList.get(0).getUser()+"*---Premium----*"+resultList.get(0).getPremium().getLampSum());
		} catch (NoResultException nre) {
			System.out.println("ERROR");		
		}
		
		return resultList;
	
	}

	@Override
	public String getProposalID() {
		StringBuilder sb = new StringBuilder();
		
		String id = null;
		try {
			sb.append("SELECT MAX(p.p_no) FROM PolicyHolder p ");
			TypedQuery<String> q = em.createQuery(sb.toString(), String.class);
			id = q.getSingleResult();
			
		} catch (NoResultException nre) {
			
			System.out.println("---ERROR1----");
			
		} catch(Exception e) {
			
			System.out.println("----ERROR1----");
		}
		
		return id;
		
	}

}
