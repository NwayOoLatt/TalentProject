package com.personal.accident.demo.service;


import com.personal.accident.demo.model.PaymentModel;
import com.personal.accident.demo.model.Proposal;


public interface PaymentService {

	Boolean savePayment(PaymentModel paymentmodel);
	Boolean completeProposal(PaymentModel p);
}
