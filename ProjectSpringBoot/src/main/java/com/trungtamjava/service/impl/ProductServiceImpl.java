package com.trungtamjava.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trungtamjava.dao.ProductDao;
import com.trungtamjava.entity.Category;
import com.trungtamjava.entity.Product;
import com.trungtamjava.entity.User;
import com.trungtamjava.model.CategoryDTO;
import com.trungtamjava.model.ProductDTO;
import com.trungtamjava.model.UserDTO;
import com.trungtamjava.service.ProductService;
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;
	
	@Override
	public void addProduct(ProductDTO productDTO) {
		Product p = new Product();
		p.setName(productDTO.getName());
		p.setPrice(productDTO.getPrice());
		p.setDescription(productDTO.getDescription());
		p.setImage(productDTO.getImage());
		p.setSoLuong(productDTO.getSoLuong());
		Category c = new Category();
		c.setId(productDTO.getCategory().getId());
		c.setName(productDTO.getCategory().getName());
      	p.setCategory(c);
		productDao.addProduct(p);
	}

	@Override
	public void updateProduct(ProductDTO productDTO) {
		Product p = productDao.getProductById(productDTO.getId());
		if(p != null){
			p.setName(productDTO.getName());
			p.setPrice(productDTO.getPrice());
			p.setDescription(productDTO.getDescription());
			p.setImage(productDTO.getImage());
			p.setSoLuong(productDTO.getSoLuong());
			Category c = new Category();
			c.setId(productDTO.getCategory().getId());
			c.setName(productDTO.getCategory().getName());
			p.setCategory(c);
			
			productDao.updateProduct(p);
		}
		
	}

	@Override
	public void deleteProduct(int id) {
		Product p = productDao.getProductById(id);
		if(p != null){
			productDao.deleteProduct(p);
		}
		
	}

	@Override
	public ProductDTO getProductById(int id) {
		Product p = productDao.getProductById(id);
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(p.getId());
		productDTO.setName(p.getName());
		productDTO.setPrice(p.getPrice());
		productDTO.setImage(p.getImage());
		productDTO.setDescription(p.getDescription());
		productDTO.setSoLuong(p.getSoLuong());
		CategoryDTO dto = new CategoryDTO();
		dto.setId(p.getCategory().getId());
		dto.setName(p.getCategory().getName());
		productDTO.setCategory(dto);
		
		return productDTO;
	}

	@Override
	public List<ProductDTO> getAllProducts() {
		List<Product> listProduct = productDao.getAllProducts();
		List<ProductDTO> productDTO = new ArrayList<ProductDTO>();
		for (Product product : listProduct) {
			ProductDTO productDTO1 = new ProductDTO();
			productDTO1.setId(product.getId());
			productDTO1.setName(product.getName());
			productDTO1.setPrice(product.getPrice());
			productDTO1.setDescription(product.getDescription());
			productDTO1.setSoLuong(product.getSoLuong());
			CategoryDTO categoryDTO = new CategoryDTO();
			categoryDTO.setId(product.getCategory().getId());
			categoryDTO.setName(product.getCategory().getName());
			productDTO1.setCategory(categoryDTO);
			productDTO.add(productDTO1);
		}
		return productDTO;
	}
	@Override
	public List<ProductDTO> search(String keyword,int categoryId, int start, int length) {
		List<Product> products = productDao.search(keyword,categoryId, start, length);
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		for (Product product : products) {
			productDTOs.add(convert(product));
		}
		return productDTOs;
		
	}

	private ProductDTO convert(Product product) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setPrice(product.getPrice());
		productDTO.setDescription(product.getDescription());
		productDTO.setSoLuong(product.getSoLuong());
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(product.getCategory().getId());
		categoryDTO.setName(product.getCategory().getName());
		productDTO.setCategory(categoryDTO);
		productDTO.setCategory(categoryDTO);
		
		productDTO.setImage(product.getImage());
		
		return productDTO;
	     
	}

	@Override
	public int countProductWhenSearch(String keyword,int categoryId) {
		
		return productDao.countProductWhenSearch(keyword,categoryId);
	}

}

