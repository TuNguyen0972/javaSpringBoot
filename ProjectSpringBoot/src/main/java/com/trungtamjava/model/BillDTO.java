package com.trungtamjava.model;


import java.util.Date;
import java.util.List;

public class BillDTO {

private int id;
	
	private Date buyDate;

	private float discountPercent;

	private float priceTotal;

	private UserDTO user;

	private List<BillProductDTO> billProducts;
	
	

	public BillDTO() {
		
		
	}
	
	

	public BillDTO(int id, Date buyDate, float discountPercent, float priceTotal, UserDTO user,
			List<BillProductDTO> billProducts) {
		super();
		this.id = id;
		this.buyDate = buyDate;
		this.discountPercent = discountPercent;
		this.priceTotal = priceTotal;
		this.user = user;
		this.billProducts = billProducts;
	}



	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the buyDate
	 */
	public Date getBuyDate() {
		return buyDate;
	}

	/**
	 * @param buyDate the buyDate to set
	 */
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	/**
	 * @return the discountPercent
	 */
	public float getDiscountPercent() {
		return discountPercent;
	}

	/**
	 * @param discountPercent the discountPercent to set
	 */
	public void setDiscountPercent(float discountPercent) {
		this.discountPercent = discountPercent;
	}

	/**
	 * @return the priceTotal
	 */
	public float getPriceTotal() {
		return priceTotal;
	}

	/**
	 * @param priceTotal the priceTotal to set
	 */
	public void setPriceTotal(float priceTotal) {
		this.priceTotal = priceTotal;
	}

	/**
	 * @return the user
	 */
	public UserDTO getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(UserDTO user) {
		this.user = user;
	}

	/**
	 * @return the billProducts
	 */
	public List<BillProductDTO> getBillProducts() {
		return billProducts;
	}

	/**
	 * @param billProducts the billProducts to set
	 */
	public void setBillProducts(List<BillProductDTO> billProducts) {
		this.billProducts = billProducts;
	}
	
	
}
