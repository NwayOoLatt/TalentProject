package com.personal.accident.demo.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.accident.demo.dto.Payment;
import com.personal.accident.demo.dto.PolicyHolder;
import com.personal.accident.demo.model.PaymentModel;
import com.personal.accident.demo.model.Proposal;
import com.personal.accident.demo.repository.PaymentRepository;
import com.personal.accident.demo.repository.ProposalRepository;
import com.personal.accident.demo.repository.UserRepository;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService{

	@Autowired
	PaymentRepository paymentrepository;
	
	@Autowired
	ProposalRepository holderInfoRepository;
	
	@Override
	public Boolean savePayment(PaymentModel paymentmodel) {
		
		// TODO Auto-generated method stub
		
		PolicyHolder holder = new PolicyHolder();
		Payment payment=new Payment();
		holder.setP_no(paymentmodel.getP_no());
		payment.setId(paymentmodel.getId());
		payment.setAmount(paymentmodel.getAmount());
		payment.setPaytype(paymentmodel.getPaytype());
		payment.setStatus("active"); 
		
		payment.setPolicyholder(holder);
		System.out.println("pnooooo"+payment.getPolicyholder()); 
		paymentrepository.save(payment);	
		return true;
	}
	@Override
	public Boolean completeProposal(PaymentModel p) {
		// TODO Auto-generated method stub
		Optional<PolicyHolder> updateDB=this.holderInfoRepository.findById(p.getP_no());		
		
		if(updateDB.isPresent()) {
			
			PolicyHolder holder = updateDB.get();

			holder.setStatus_checking("Complete");
			holderInfoRepository.save(holder);
		}
		
		return true;
	}
	
}
