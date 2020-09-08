package com.trungtamjava.service;

import java.util.List;

import com.trungtamjava.model.CategoryDTO;
import com.trungtamjava.model.UserDTO;

public interface CategoryService {

	public void addCategory(CategoryDTO categoryDTO);

	public void updateCategory(CategoryDTO categoryDTO);

	public void deleteCategory(int id);

	public CategoryDTO getCategoryById(int id);

	public List<CategoryDTO> getAllCategorys();
	
    List<CategoryDTO> search(String name, int start, int length);
	
	public int countCategoryWhenSearch(String name);
}
