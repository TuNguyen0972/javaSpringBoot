package com.trungtamjava.dao;

import java.util.List;

import com.trungtamjava.entity.Category;
import com.trungtamjava.entity.User;

public interface CategoryDao {

	public void addCategory(Category category);

	public void updateCategory(Category category); // update theo categoryEntity

	public void deleteCategory(Category category);

	public Category getCategoryById(int id); // tim theo categoryEntity

	public List<Category> getAllCategorys();

	public Category getCategoryByCategoryname(String categoryname);
	
    List<Category> search(String name, int start, int length);
	
	public int countCategoryWhenSearch(String name);
}
