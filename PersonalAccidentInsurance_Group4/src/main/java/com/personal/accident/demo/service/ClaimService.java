package com.personal.accident.demo.service;

import java.util.List;

import com.personal.accident.demo.dto.PolicyHolder;
import com.personal.accident.demo.model.ClaimModel;
import com.personal.accident.demo.model.Proposal;

public interface ClaimService {

	List<Proposal> findPolicy(Proposal pmodel,int id);
	Boolean saveClaim(Proposal pmodel);
}
