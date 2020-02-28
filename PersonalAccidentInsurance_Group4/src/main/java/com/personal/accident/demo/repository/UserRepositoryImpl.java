package com.personal.accident.demo.repository;

import java.util.ArrayList;
import javax.persistence.NoResultException;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.personal.accident.demo.dto.User;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

	
	@Autowired
	EntityManager em;
	
	@Override
	public List<User> findUser(User user) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		System.out.println("---------UserRepository1-------------" + user.getName());
		Root<User> usrr = cq.from(User.class);
        Predicate p=cb.equal(usrr.get("name"), user.getName());
		cq.where(p).distinct(true);
		TypedQuery<User> typedQuery = em.createQuery(cq);
		List<User> resultList = new ArrayList<User>();

		try {
			resultList = typedQuery.getResultList();
			System.out.println("----Repository2---" + resultList);
		} catch (NoResultException nre) {
			System.out.println("ERROR");
			return null;
		}

		return resultList;
	}

	@Override
	public List<User> findMail(User user) {
		
		// TODO Auto-generated method stub
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		System.out.println("---------UserRepository1-------------" + user.getEmail());
		Root<User> usrr = cq.from(User.class);
        Predicate p=cb.equal(usrr.get("email"), user.getEmail());
		cq.where(p).distinct(true);
		
		TypedQuery<User> typedQuery = em.createQuery(cq);
		List<User> resultList = new ArrayList<User>();

		try {
			resultList = typedQuery.getResultList();
			System.out.println("----Repository2---" + resultList);
		} catch (NoResultException nre) {
			System.out.println("ERROR");
			return null;
		}

		
		return resultList;
	}
}
