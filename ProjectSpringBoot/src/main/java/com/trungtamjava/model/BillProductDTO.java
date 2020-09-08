package com.trungtamjava.model;

public class BillProductDTO {

	private int id;

	private ProductDTO product;

	private int quantity;

	private float unitPrice;

	private BillDTO bill;
	
	

	public BillProductDTO() {
		
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
	 * @return the product
	 */
	public ProductDTO getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the unitPrice
	 */
	public float getUnitPrice() {
		return unitPrice;
	}

	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	/**
	 * @return the bill
	 */
	public BillDTO getBill() {
		return bill;
	}

	/**
	 * @param bill the bill to set
	 */
	public void setBill(BillDTO bill) {
		this.bill = bill;
	}
	
	
}
