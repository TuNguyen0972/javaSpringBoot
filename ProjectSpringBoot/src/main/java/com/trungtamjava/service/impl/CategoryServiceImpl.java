package com.trungtamjava.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trungtamjava.dao.CategoryDao;
import com.trungtamjava.entity.Category;
import com.trungtamjava.entity.User;
import com.trungtamjava.model.CategoryDTO;
import com.trungtamjava.model.UserDTO;
import com.trungtamjava.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDao categoryDao;
	
	@Override
	public void addCategory(CategoryDTO categoryDTO) {
		
		Category c = new Category();
		c.setName(categoryDTO.getName());
		categoryDao.addCategory(c);
	}

	@Override
	public void updateCategory(CategoryDTO categoryDTO) {
		Category c = categoryDao.getCategoryById(categoryDTO.getId());
		if(c != null){
			c.setName(categoryDTO.getName());
			categoryDao.updateCategory(c);
			
		}
		
	}

	@Override
	public void deleteCategory(int id) {
		Category c = categoryDao.getCategoryById(id);
	       if(c != null){
	    	   categoryDao.deleteCategory(c);
	       }
		
	}

	@Override
	public CategoryDTO getCategoryById(int id) {
		Category c = categoryDao.getCategoryById(id);
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(c.getId());
		categoryDTO.setName(c.getName());
		
		return categoryDTO;
	}

	@Override
	public List<CategoryDTO> getAllCategorys() {
		List<Category> categorys = categoryDao.getAllCategorys();
		List<CategoryDTO> categoryDTOs = new ArrayList<CategoryDTO>();
		for(Category category : categorys){
			CategoryDTO categoryDTO = new CategoryDTO();
			categoryDTO.setId(category.getId());
			categoryDTO.setName(category.getName());
			categoryDTOs.add(categoryDTO);
		}
		return categoryDTOs;
	}

	@Override
	public List<CategoryDTO> search(String name, int start, int length) {
		List<Category> categorys = categoryDao.search(name, start, length);
		List<CategoryDTO> categoryDTOs = new ArrayList<CategoryDTO>();
		for (Category category : categorys) {
			categoryDTOs.add(convert(category));
		}
		return categoryDTOs;
		
	}

	private CategoryDTO convert(Category category) {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(category.getId());
		categoryDTO.setName(category.getName());
	    
	     
		return categoryDTO;
	     
	}

	@Override
	public int countCategoryWhenSearch(String name) {
		return categoryDao.countCategoryWhenSearch(name);
	}

}
