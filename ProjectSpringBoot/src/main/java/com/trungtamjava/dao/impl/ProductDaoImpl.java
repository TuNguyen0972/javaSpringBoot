package com.trungtamjava.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.trungtamjava.dao.ProductDao;
import com.trungtamjava.entity.Product;
import com.trungtamjava.entity.User;

@Transactional
@Repository
public class ProductDaoImpl implements ProductDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void addProduct(Product product) {
		entityManager.persist(product);

	}

	@Override
	public void updateProduct(Product product) {
		entityManager.merge(product);

	}

	@Override
	public void deleteProduct(Product product) {
		entityManager.remove(product);

	}

	@Override
	public Product getProductById(int id) {

		return entityManager.find(Product.class, id);
	}

	@Override
	public List<Product> getAllProducts() {
		String jql = "SELECT p FROM Product p";

		return entityManager.createQuery(jql, Product.class).getResultList();

	}

	@Override
	public Product getProductByProductname(String productname) {

		String jql = "SELECT p FROM Product p WHERE p.name = :uname";
		return entityManager.createQuery(jql, Product.class).setParameter("uname", productname).getSingleResult();
	}

	@Override
	public List<Product> search(String keyword, int categoryId,  int start, int length) {
		String jql ="SELECT p from Product p where (name like :keyword or description like :keyword)";
		if(categoryId!=-1) {
			jql+=" and category.id = :categoryId";
		}
		Query query = entityManager.createQuery(jql,Product.class);
		query.setParameter("keyword","%"+ keyword+"%");
		if(categoryId!=-1) {
			query.setParameter("categoryId",categoryId);
		}
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.getResultList();
		
	}

	@Override
	public int countProductWhenSearch(String keyword, int categoryId ) {
		String jql ="SELECT p from Product p where (name like :keyword or description like :keyword)";
		if(categoryId!=-1) {
			jql+=" and category.id = :categoryId";
		}
		Query query = entityManager.createQuery(jql,Product.class);
		query.setParameter("keyword","%"+ keyword+"%");
		if(categoryId!=-1) {
			query.setParameter("categoryId",categoryId);
		}
		return query.getResultList().size();
	}

}
