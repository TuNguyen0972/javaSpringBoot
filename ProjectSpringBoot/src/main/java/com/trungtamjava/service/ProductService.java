package com.trungtamjava.service;

import java.util.List;

import com.trungtamjava.model.ProductDTO;
import com.trungtamjava.model.UserDTO;

public interface ProductService {

public void addProduct(ProductDTO productDTO);
	
	public void updateProduct(ProductDTO productDTO);
	
	public void deleteProduct(int id);
	
	public ProductDTO getProductById(int id);
	
	public List<ProductDTO> getAllProducts();
	
	List<ProductDTO> search(String keyword,int categoryId, int start, int length);
	
	public int countProductWhenSearch(String keyword,int categoryId);
	
}
