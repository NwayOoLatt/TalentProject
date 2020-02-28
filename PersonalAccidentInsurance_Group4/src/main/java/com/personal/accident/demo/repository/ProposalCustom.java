package com.personal.accident.demo.repository;

import java.util.List;
import com.personal.accident.demo.dto.PolicyHolder;


public interface ProposalCustom {

	List<PolicyHolder> getProposal(int id);
	List<PolicyHolder> getProposalApproved(int id);
	String getProposalID();
	
}
