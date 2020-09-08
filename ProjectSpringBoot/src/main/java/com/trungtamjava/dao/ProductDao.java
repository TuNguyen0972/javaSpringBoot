package com.trungtamjava.dao;

import java.util.List;

import com.trungtamjava.entity.Product;
import com.trungtamjava.entity.User;

public interface ProductDao {
	
	public void addProduct(Product product);

	public void updateProduct(Product product); // update theo productEntity

	public void deleteProduct(Product product);

	public Product getProductById(int id); // tim theo productEntity

	public List<Product> getAllProducts();

	public Product getProductByProductname(String productname);
	
	List<Product> search(String name,int categoryId, int start, int length);
	
	public int countProductWhenSearch(String keyword, int categoryId );
}
