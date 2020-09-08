package com.trungtamjava.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.trungtamjava.dao.CategoryDao;
import com.trungtamjava.entity.Category;
import com.trungtamjava.entity.User;

@Transactional
@Repository
public class CategoryDaoImpl implements CategoryDao{
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public void addCategory(Category category) {
		entityManager.persist(category);
		
		
	}

	@Override
	public void updateCategory(Category category) {
		entityManager.merge(category);
		
	}

	@Override
	public void deleteCategory(Category category) {
		entityManager.remove(category);
		
	}

	@Override
	public Category getCategoryById(int id) {
		
		return entityManager.find(Category.class, id);
	}

	@Override
	public List<Category> getAllCategorys() {
		String jql = "SELECT c FROM Category c";
		return entityManager.createQuery(jql, Category.class).getResultList();
	}

	@Override
	public Category getCategoryByCategoryname(String categoryname) {
	     String jql = "SELECT c FROM Category c WHERE c.name = :uname";
		return entityManager.createQuery(jql, Category.class).setParameter("uname", categoryname).getSingleResult();
	}

	@Override
	public List<Category> search(String name, int start, int length) {
		String jql = "SELECT c from Category c where name like :name";
		Query query = entityManager.createQuery(jql, Category.class);
		query.setParameter("name", "%" + name + "%");
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.getResultList();
		
	}

	@Override
	public int countCategoryWhenSearch(String name) {
		String jql ="SELECT c from Category c where name like :name";
		Query query = entityManager.createQuery(jql,Category.class);
		query.setParameter("name","%"+ name +  "%");
		return (int) query.getResultList().size();
	}

	
}
