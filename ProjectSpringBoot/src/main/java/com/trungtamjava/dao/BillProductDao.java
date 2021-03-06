package com.trungtamjava.dao;

import java.util.List;

import com.trungtamjava.entity.BillProduct;

public interface BillProductDao {

	public void addBillProduct(BillProduct billProduct);

	public void deleteBillProduct(BillProduct billProduct);

	public void updateBillProduct(BillProduct billProduct);

	public BillProduct getBillProductById(int id);

	public List<BillProduct> search(String keyword, int start, int length);
	
	public int countBillProductWhenSearch(String keyword);
	
	public List<BillProduct> searchByBillId(int billId, int start, int length);
	
	public int countBillProductWhenSearchByBillId(int billId);
}
