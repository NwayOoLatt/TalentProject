package com.personal.accident.demo.service;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.accident.demo.dto.PolicyHolder;
import com.personal.accident.demo.dto.User;
import com.personal.accident.demo.model.ClaimModel;
import com.personal.accident.demo.model.Proposal;
import com.personal.accident.demo.repository.ProposalRepository;

@Service
@Transactional
public class PolicyServiceImpl implements PolicyService {

	@Autowired
	ProposalRepository holderInfoRepository;

	
	@Override
	public List<Proposal> proposalList(int id) {
		
		
		// TODO Auto-generated method stub
	
		Double pay=0.0;
		List<PolicyHolder> plist=holderInfoRepository.getProposalApproved(id);
		List<Proposal> prolist = new ArrayList<Proposal>();
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		DecimalFormat decimalFormat = new DecimalFormat("0.0");
		
		
		int c=0;
		
		if (!plist.isEmpty()) {

			for (int j=0;j<plist.size();j++) {

					c++;
					Proposal pp = new Proposal();
					pp.setCount(c);
					pp.setFirstName(plist.get(j).getFirstName());
					pp.setLastName(plist.get(j).getLastName());
					pp.setName(plist.get(j).getFirstName() + plist.get(j).getLastName());
					pp.setAge(plist.get(j).getAge());
					pp.setNrc(plist.get(j).getNrc());
					pp.setM_status(plist.get(j).getMartialstatus());
					pp.setP_no(plist.get(j).getP_no());
					pp.setPhone(plist.get(j).getPhone());
					pp.setEmail(plist.get(j).getEmail());
					pp.setStatus_checking(plist.get(j).getStatus_checking());
					pp.setTotalamount1(decimalFormat.format(plist.get(j).getPremium().getLampSum()));
					pp.setTotalamount(plist.get(j).getPremium().getLampSum());
					pp.setYear(plist.get(j).getPremium().getYear());
					pp.setTerm(plist.get(j).getPremium().getTerm());
					pp.setPayamount(plist.get(j).getPremium().getPayamount());
					pp.setSdate(plist.get(j).getStart_date());
					pp.setEdate(plist.get(j).getEnd_date());
					pp.setOccupation(plist.get(j).getOccupation());
					pp.setDateofbirth(plist.get(j).getDob());
					pp.setStreet(plist.get(j).getStreet());
					pp.setHomeNo(plist.get(j).getHomeNo());
					pp.setState(plist.get(j).getState());
					pp.setAddress(plist.get(j).getHomeNo()+" / "+plist.get(j).getStreet()+" / "+plist.get(j).getState());
				
					pp.setGender(plist.get(j).getGender());
					pp.setC_id(plist.get(j).getPremium().getId());
					pp.setP_id(plist.get(j).getPremium().getId());
					
					
					pp.setB_id(plist.get(j).getBeneficiary().getId());
					pp.setB_address(plist.get(j).getBeneficiary().getAddress());
					pp.setB_email(plist.get(j).getBeneficiary().getEmail());
					pp.setB_name(plist.get(j).getBeneficiary().getName());
					pp.setB_nrc(plist.get(j).getBeneficiary().getNrc());
					pp.setB_phone(plist.get(j).getBeneficiary().getPhone());
					pp.setRelationship(plist.get(j).getBeneficiary().getRelationship());
					
					
					List<ClaimModel> clist=new ArrayList<ClaimModel>();
					int cc=0;
					  for(int i=0;i<plist.get(j).getClaim().size();i++) {
						  cc++;
						  ClaimModel cmodel=new ClaimModel();
						  System.out.println("(((cliam))))"+plist.get(j).getClaim().get(i).getCategory().getType());
						  cmodel.setType(plist.get(j).getClaim().get(i).getCategory().getType());
						  cmodel.setToday(plist.get(j).getClaim().get(i).getDate());
						  cmodel.setAmount(plist.get(j).getClaim().get(i).getAmount());
						  cmodel.setReason(plist.get(j).getClaim().get(i).getReason());
						  cmodel.setPlace(plist.get(j).getClaim().get(i).getReason());
						  cmodel.setClaimamount(decimalFormat.format(plist.get(j).getClaim().get(i).getAmount()));
						  cmodel.setClaimCount(cc);
						  pp.setClaimCount(cc);
						  
						  clist.add(cmodel);
					  }
					  pay=0.0;
					  for(int p=0;p<plist.get(j).getPayment().size();p++) {
							 
						  	
						  	
						  	 pp.setAmount(plist.get(j).getPayment().get(p).getAmount());
							 pay+=plist.get(j).getPayment().get(p).getAmount();
							 System.out.println("payamount----"+pay);
						}
					  pp.setPayment(null);
					  pp.setPayment(decimalFormat.format(pay));
					  pp.setClaim(clist);
					  prolist.add(pp);
						
			}
		}

		return prolist;
	
	}

}
