package com.personal.accident.demo.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.accident.demo.dto.Category;
import com.personal.accident.demo.dto.ClaimForm;
import com.personal.accident.demo.dto.PolicyHolder;
import com.personal.accident.demo.model.BeneficiaryModel;
import com.personal.accident.demo.model.ClaimModel;
import com.personal.accident.demo.model.Proposal;
import com.personal.accident.demo.repository.ClaimRepository;
import com.personal.accident.demo.repository.ClaimRepositoryImpl;

@Service
@Transactional
public class ClaimServiceImpl implements ClaimService {

	@Autowired
	ClaimRepositoryImpl claimrepository;

	@Autowired
	ClaimRepository crepository;

	@Override
	public List<Proposal> findPolicy(Proposal pmodel,int id) {
		// TODO Auto-generated method stub
		PolicyHolder p = new PolicyHolder();
		p.setP_no(pmodel.getP_no());
		
		List<PolicyHolder> plist = claimrepository.findPolicy(p,id);
		
		System.out.println("---pno---" + pmodel.getP_no());
		List<Proposal> prolist = new ArrayList<Proposal>();
		BeneficiaryModel bmodel=new BeneficiaryModel();
		ClaimModel cmodel=new ClaimModel();

		if (!plist.isEmpty()) {

			for (int j=0;j<plist.size();j++) {

				
					Proposal pp = new Proposal();
					pp.setName(plist.get(j).getFirstName() + plist.get(j).getLastName());
					pp.setP_no(plist.get(j).getP_no());
					pp.setPhone(plist.get(j).getPhone());
					pp.setEmail(plist.get(j).getEmail());
					pp.setStatus_checking(plist.get(j).getStatus_checking());
					pp.setTotalamount(plist.get(j).getPremium().getLampSum());
					pp.setSdate(plist.get(j).getStart_date());
					pp.setEdate(plist.get(j).getEnd_date());
				/* pp.setClaim(plist.get(j).getClaim()); */
					
					List<ClaimModel> claim=new ArrayList<ClaimModel>();
					
					for(int i=0;i<plist.get(j).getClaim().size();i++) {
						
						cmodel=new ClaimModel();
						cmodel.setId(plist.get(j).getClaim().get(i).getId());
						cmodel.setAmount(plist.get(j).getClaim().get(i).getAmount());
						cmodel.setPlace(plist.get(j).getClaim().get(i).getPlace());
						cmodel.setReason(plist.get(j).getClaim().get(i).getReason());
						cmodel.setToday(plist.get(j).getClaim().get(i).getDate());
						cmodel.setType(plist.get(j).getClaim().get(i).getCategory().getType());
						
						claim.add(cmodel);
					}
					pp.setClaim(claim);
					
					pp.setB_id(plist.get(j).getBeneficiary().getId());
					pp.setB_address(plist.get(j).getBeneficiary().getAddress());
					pp.setB_email(plist.get(j).getBeneficiary().getEmail());
					pp.setB_name(plist.get(j).getBeneficiary().getName());
					pp.setB_nrc(plist.get(j).getBeneficiary().getNrc());
					pp.setB_phone(plist.get(j).getBeneficiary().getPhone());
					pp.setRelationship(plist.get(j).getBeneficiary().getRelationship());
					
					
					prolist.add(pp);
					
					System.out.println("premium" + plist.get(j).getPremium().getLampSum());

				

			}
		}

		return prolist;
	}

	/*
	 * @Override public List<Proposal> findClaim(Proposal pmodel) {
	 *  // TODO Auto-generated method stub
	 * ClaimForm claim = new ClaimForm(); List<ClaimForm> clist =
	 * claimrepository.findClaim(pmodel); System.out.println("---find claim---" +
	 * clist); return null; }
	 */
	
	
	@Override
	public Boolean saveClaim(Proposal pmodel) {

		// TODO Auto-generated method stub

		PolicyHolder pholder = new PolicyHolder();
		pholder.setP_no(pmodel.getP_no());
		
		Category cat=new Category();
		cat.setId(pmodel.getC_id());
		
		System.out.println("service pno= " + pholder.getP_no());
		Date date = null;
		
		System.out.println("-----1-----");
		ClaimForm claim = new ClaimForm();
		claim.setDate(pmodel.getToday());
		claim.setPlace(pmodel.getPlace());
		claim.setReason(pmodel.getReason());
		claim.setAmount(pmodel.getAmount());
		claim.setReason(pmodel.getReason());
		claim.setPolicyholder(pholder);
		claim.setCategory(cat);
		
		claim.setStatus("true");
		System.out.println("Claim to save");
		crepository.save(claim);

		return true;
	}

}
