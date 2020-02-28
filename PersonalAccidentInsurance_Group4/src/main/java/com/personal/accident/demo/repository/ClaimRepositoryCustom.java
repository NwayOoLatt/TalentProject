package com.personal.accident.demo.repository;

import java.util.List;
import com.personal.accident.demo.dto.PolicyHolder;


public interface ClaimRepositoryCustom {
 

	List<PolicyHolder> findPolicy(PolicyHolder p,int id);
}
