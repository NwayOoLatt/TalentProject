package com.personal.accident.demo.service;

import java.util.List;

import com.personal.accident.demo.dto.User;
import com.personal.accident.demo.model.Proposal;

public interface PolicyService {

	List<Proposal> proposalList(int id);
}
