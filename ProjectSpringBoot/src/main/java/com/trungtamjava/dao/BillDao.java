package com.trungtamjava.dao;

import java.util.List;

import com.trungtamjava.entity.Bill;

public interface BillDao {

	public void addBill(Bill bill);

	public void deleteBill(Bill bill);

	public void updateBill(Bill bill);

	public Bill getBillById(int id);

	public List<Bill> search(String keyword, int start, int length);
	
	public int countBillWhenSearch(String keyword);
	
	public List<Bill> searchByUsername(String username, int start, int length);
	
	public int countBillWhenSearchByUsername(String username);
}
