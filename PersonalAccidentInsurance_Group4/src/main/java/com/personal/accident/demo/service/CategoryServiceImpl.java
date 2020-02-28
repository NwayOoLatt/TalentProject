package com.personal.accident.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.accident.demo.dto.Category;
import com.personal.accident.demo.model.CategoryModel;
import com.personal.accident.demo.repository.CategoryRepository;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoryRepository categoryRepository;
		
	@Override
	public List<CategoryModel> allCategory() {
		// TODO Auto-generated method stub
		
		List<Category> catList=categoryRepository.findAll();
		List<CategoryModel> catModelList=new ArrayList<CategoryModel>();
		
		for(int i=0;i<catList.size();i++) {
		
			CategoryModel catModel=new CategoryModel();
			catModel.setId(catList.get(i).getId());
			catModel.setType(catList.get(i).getType());
			catModel.setPercentage(catList.get(i).getPercentage());
			catModelList.add(catModel);
		}
		
		return catModelList;
	}

}
