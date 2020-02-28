package com.personal.accident.demo.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.accident.demo.dto.Beneficiary;
import com.personal.accident.demo.dto.PolicyHolder;
import com.personal.accident.demo.dto.Premium;
import com.personal.accident.demo.dto.User;
import com.personal.accident.demo.model.ClaimModel;
import com.personal.accident.demo.model.Proposal;
import com.personal.accident.demo.repository.BeneficiaryRepository;
import com.personal.accident.demo.repository.PremiumRepository;
import com.personal.accident.demo.repository.ProposalRepository;
import com.personal.accident.demo.repository.UserRepository;

@Service
@Transactional
public class ProposalServiceImpl implements ProposalService{

	@Autowired
		ProposalRepository holderInfoRepository;
	
	@Autowired
		UserRepository userrepository;
	
	@Autowired
		PremiumRepository premiumRepository;

	
	@Autowired
		BeneficiaryRepository benefitRepository;
	
	
	
	  
	
	@Override
	public Boolean saveProposal(Proposal p,int id) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		System.out.println("------User-------");
		User u = new User();
		u.setId(id);		

		System.out.println("------Premium-------");
		Premium premium = new Premium();

		
		Double amount = Double.valueOf(p.getTotalamount());
		premium.setLampSum(amount);
		premium.setTerm(p.getTerm());
		premium.setYear(p.getYear());
		premium.setPayamount(p.getPayamount());
		premium.setStatus("active");

		premiumRepository.save(premium);

		
		System.out.println("------Benefit-------");
		Beneficiary benefit = new Beneficiary();
		
		benefit.setAddress(p.getB_address());
		benefit.setEmail(p.getB_email());
		benefit.setName(p.getB_name());
		benefit.setNrc(p.getB_nrc());
		benefit.setPhone(p.getB_phone());
		benefit.setRelationship(p.getRelationship());
		benefit.setStatus("active");		
		benefitRepository.save(benefit);	
		
		System.out.println("------Policy-------");
		PolicyHolder holder = new PolicyHolder();

		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		
		holder.setP_no(p.getP_no());
		holder.setFirstName(p.getFirstName());
		holder.setLastName(p.getLastName());
		
		String date = dateFormat.format(p.getDob());
		holder.setDob(date);
		holder.setAge(p.getAge());
		holder.setNrc(p.getNrc());
		
		holder.setEmail(p.getEmail());
		holder.setPhone(p.getPhone());
		holder.setMartialstatus(p.getM_status());
		holder.setOccupation(p.getOccupation());
		
		String date1 = dateFormat.format(p.getStart_date());
		holder.setStart_date(date1);
		
		String date2 = dateFormat.format(p.getEnd_date());
		holder.setEnd_date(date2);
		holder.setHomeNo(p.getHomeNo());
		holder.setStreet(p.getStreet());
		holder.setState(p.getState());
		holder.setGender(p.getGender());
		
		holder.setReject("false");
		holder.setStatus_checking("pending");

		holder.setUser(u);
		holder.setPremium(premium);
		holder.setBeneficiary(benefit);

		holderInfoRepository.save(holder);

		
		
		return true;
		
	
	}

	@Override
	public Boolean updateProposal(Proposal p,int id) {
		// TODO Auto-generated method stub

		System.out.println("updateProposal");
		Optional<PolicyHolder> updateDB=this.holderInfoRepository.findById(p.getP_no());		
		
		if(updateDB.isPresent()) {
			
			User u = new User();
			u.setId(id);		

			System.out.println("------Premium-------"+p.getP_id());
			Optional<Premium> updateDB2=this.premiumRepository.findById(p.getP_id());
			Premium premium = updateDB2.get();
			
			Double amount = Double.valueOf(p.getTotalamount());
			premium.setLampSum(amount);
			premium.setTerm(p.getTerm());
			premium.setYear(p.getYear());
			premium.setPayamount(p.getPayamount());
			premium.setStatus("active");

			premiumRepository.save(premium);

			System.out.println("------Policy-------");
			
			PolicyHolder holder = updateDB.get();

			DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
			
			holder.setP_no(p.getP_no());
			holder.setFirstName(p.getFirstName());
			holder.setLastName(p.getLastName());
			
			String date = dateFormat.format(p.getDob());
			holder.setDob(date);
			holder.setAge(p.getAge());
			holder.setNrc(p.getNrc());
			
			holder.setEmail(p.getEmail());
			holder.setPhone(p.getPhone());
			holder.setMartialstatus(p.getM_status());
			holder.setOccupation(p.getOccupation());
			
			String date1 = dateFormat.format(p.getStart_date());
			holder.setStart_date(date1);
			
			String date2 = dateFormat.format(p.getEnd_date());
			holder.setEnd_date(date2);
			
			System.out.println("Before Approved");
			holder.setState(p.getState());
			holder.setHomeNo(p.getHomeNo());
			holder.setStreet(p.getStreet());
			holder.setGender(p.getGender());
			
			holder.setReject("false");
			
			holder.setStatus_checking("Approved");
			System.out.println("After Approved");
			holder.setUser(u);
			holder.setPremium(premium);

			holderInfoRepository.save(holder);

			System.out.println("------Benefit-------");
			Optional<Beneficiary> updateDB3=this.benefitRepository.findById(p.getB_id());
			Beneficiary benefit = updateDB3.get();
			
			benefit.setAddress(p.getB_address());
			benefit.setEmail(p.getB_email());
			benefit.setName(p.getB_name());
			benefit.setNrc(p.getB_nrc());
			benefit.setPhone(p.getB_phone());
			benefit.setRelationship(p.getRelationship());
			benefit.setStatus("active");
			benefit.setPolicyholder(holder);		
			benefitRepository.save(benefit);
					
		
	}
		return true;

	}

	@Override
	public Boolean deleteProposal(Proposal p) {
		// TODO Auto-generated method stub
		
Optional<PolicyHolder> updateDB=this.holderInfoRepository.findById(p.getP_no());		
		
		if(updateDB.isPresent()) {
			
//			User u = new User();
//			u.setId(1);
//			u.setName("User");
//			u.setEmail("u@gmail.com");
//			u.setPassword("11111");
//			u.setStatus("active");

			
			System.out.println("------Premium-------"+p.getP_id());
			Optional<Premium> updateDB2=this.premiumRepository.findById(p.getP_id());
			Premium premium = updateDB2.get();
			
			premium.setStatus("delete");

			premiumRepository.save(premium);
			
			System.out.println("------Policy-------");
			PolicyHolder holder = updateDB.get();

			
			holder.setReject("delete");
			holder.setStatus_checking("reject");
			holderInfoRepository.save(holder);

			System.out.println("------Benefit-------");
			Optional<Beneficiary> updateDB3=this.benefitRepository.findById(p.getB_id());
			Beneficiary benefit = updateDB3.get();
			
			
			benefit.setStatus("delete");
			benefitRepository.save(benefit);
					
	
	}
		return true;

	}

	@Override
	public List<Proposal> statusChecking(int id) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		
		Double pay=0.0;
		List<PolicyHolder> plist=holderInfoRepository.getProposal(id);
		List<Proposal> prolist = new ArrayList<Proposal>();
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		/*
		 * BeneficiaryModel bmodel=new BeneficiaryModel(); ContactModel contact;
		 */
		
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
					pp.setTotalamount(plist.get(j).getPremium().getLampSum());
					pp.setYear(plist.get(j).getPremium().getYear());
					pp.setTerm(plist.get(j).getPremium().getTerm());
					pp.setAmount(plist.get(j).getPremium().getPayamount());
					pp.setSdate(plist.get(j).getStart_date());
					pp.setEdate(plist.get(j).getEnd_date());
					pp.setOccupation(plist.get(j).getOccupation());
				/* pp.setDob(dateFormat.parse(plist.get(j).getDob())); */
					pp.setDateofbirth(plist.get(j).getDob());
					pp.setStreet(plist.get(j).getStreet());
					pp.setHomeNo(plist.get(j).getHomeNo());
					pp.setState(plist.get(j).getState());
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
					
				/**/
					
					List<ClaimModel> clist=new ArrayList<ClaimModel>();
					
					  for(int i=0;i<plist.get(j).getClaim().size();i++) {
						  
						  ClaimModel cmodel=new ClaimModel();
						  System.out.println("(((cliam))))"+plist.get(j).getClaim().get(i).getCategory().getType());
						  cmodel.setType(plist.get(j).getClaim().get(i).getCategory().getType());
						  cmodel.setToday(plist.get(j).getClaim().get(i).getDate());
						  cmodel.setAmount(plist.get(j).getClaim().get(i).getAmount());
						  cmodel.setReason(plist.get(j).getClaim().get(i).getReason());
						  cmodel.setPlace(plist.get(j).getClaim().get(i).getPlace());
						  System.out.println("Place"+cmodel.getPlace());
						  clist.add(cmodel);
					  }
					  
					 for(int p=0;p<plist.get(j).getPayment().size();p++) {
						 
						 
						 pay+=plist.get(j).getPayment().get(p).getAmount();
						 System.out.println("payamount----"+pay);
					 }
					 
					  pp.setPayment(String.valueOf(pay));
					  pp.setClaim(clist);
					  prolist.add(pp);
					
			}
		}

		return prolist;
	
	}

	@Override
	public String getProposalID() {
		
		return holderInfoRepository.getProposalID();
	}

}
