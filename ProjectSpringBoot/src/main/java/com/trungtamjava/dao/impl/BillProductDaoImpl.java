package com.trungtamjava.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.trungtamjava.dao.BillProductDao;
import com.trungtamjava.entity.BillProduct;
@Transactional
@Repository

public class BillProductDaoImpl implements BillProductDao{
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void addBillProduct(BillProduct billProduct) {
		entityManager.persist(billProduct);
		
	}

	@Override
	public void deleteBillProduct(BillProduct billProduct) {
		entityManager.remove(billProduct);
		
	}

	@Override
	public void updateBillProduct(BillProduct billProduct) {
		entityManager.merge(billProduct);
		
	}

	@Override
	public BillProduct getBillProductById(int id) {
		return entityManager.find(BillProduct.class, id);
	}

	@Override
	public List<BillProduct> search(String keyword, int start, int length) {
		
		String jql ="SELECT bp from BillProduct bp";
		Query query = entityManager.createQuery(jql,BillProduct.class);
		query.setParameter("keyword","%"+ keyword+"%");
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.getResultList();
	}

	@Override
	public int countBillProductWhenSearch(String keyword) {
		
		String jql ="SELECT bp from BillProduct bp";
		Query query = entityManager.createQuery(jql,BillProduct.class);
		return query.getResultList().size();
	}

	@Override
	public List<BillProduct> searchByBillId(int billId, int start, int length) {
		String jql ="SELECT bp from BillProduct bp where bp.bill.id = :billId";
		Query query = entityManager.createQuery(jql,BillProduct.class);
		query.setParameter("billId", billId);
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.getResultList();
	}

	@Override
	public int countBillProductWhenSearchByBillId(int billId) {
		String jql ="SELECT bp from BillProduct bp where bp.bill.id = :billId";
		Query query = entityManager.createQuery(jql,BillProduct.class);
		query.setParameter("billId", billId);
		return query.getResultList().size();
	}

}
