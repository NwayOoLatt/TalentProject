package com.personal.accident.demo.service;

import java.util.List;

import com.personal.accident.demo.model.Proposal;

public interface ProposalService {
	
	Boolean saveProposal(Proposal p,int id);
	Boolean updateProposal(Proposal p,int id);
	Boolean deleteProposal(Proposal p);
	
	List<Proposal> statusChecking(int id);
	String getProposalID();
	
}
