package com.personal.accident.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.accident.demo.dto.Category;


public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
