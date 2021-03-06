package com.trungtamjava.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trungtamjava.dao.BillProductDao;
import com.trungtamjava.entity.Bill;
import com.trungtamjava.entity.BillProduct;
import com.trungtamjava.entity.Product;
import com.trungtamjava.model.BillDTO;
import com.trungtamjava.model.BillProductDTO;
import com.trungtamjava.model.ProductDTO;
import com.trungtamjava.service.BillProductService;

@Service
@Transactional
public class BillProductServiceImpl implements BillProductService{
 
	@Autowired
	BillProductDao billProductDao;

	@Override
	public void addBillProductDTO(BillProductDTO billProductDTO) {
		BillProduct billProduct = new BillProduct();

		Product product = new Product();
		product.setId(billProductDTO.getProduct().getId());
		billProduct.setProduct(product);
		billProduct.setQuantity(billProductDTO.getQuantity());
		billProduct.setUnitPrice(billProductDTO.getUnitPrice());

		Bill bill = new Bill();
		bill.setId(billProductDTO.getBill().getId());
		billProduct.setBill(bill);
		
//		System.out.println("billProduct: "+billProduct.getBill().getId()+" "+billProduct.getProduct().getId());
		billProductDao.addBillProduct(billProduct);
	}

	@Override
	public void deleteBillProductDTO(int id) {
		BillProduct billProduct = billProductDao.getBillProductById(id);
		if (billProduct != null) {
			billProductDao.deleteBillProduct(billProduct);
		}
		
	}

	@Override
	public void updateBillProductDTO(BillProductDTO billProductDTO) {
		
		BillProduct billProduct = billProductDao.getBillProductById(billProductDTO.getId());
		if (billProduct != null) {
			billProduct = new BillProduct();
			billProduct.setId(billProductDTO.getId());
			
			Product product = new Product();
			product.setId(billProductDTO.getProduct().getId());
			product.setName(billProductDTO.getProduct().getName());
			product.setPrice(billProductDTO.getProduct().getPrice());
			product.setSoLuong(billProductDTO.getProduct().getSoLuong());
			
			billProduct.setProduct(product);
			billProduct.setQuantity(billProductDTO.getQuantity());
			billProduct.setUnitPrice(billProductDTO.getUnitPrice());

			Bill bill = new Bill();
			bill.setId(billProductDTO.getBill().getId());
			billProduct.setBill(bill);

			billProductDao.updateBillProduct(billProduct);
		}
	}

	@Override
	public BillProductDTO getBillProductDTOById(int id) {
		
		BillProduct billProduct = billProductDao.getBillProductById(id);
		if (billProduct != null) {
			BillProductDTO billProductDTO = new BillProductDTO();
			billProductDTO.setId(billProduct.getId());
			ProductDTO productDTO = new ProductDTO();
			productDTO.setId(billProduct.getProduct().getId());
			productDTO.setName(billProduct.getProduct().getName());
			productDTO.setPrice(billProduct.getProduct().getPrice());
			productDTO.setSoLuong(billProduct.getProduct().getSoLuong());
			
			billProductDTO.setProduct(productDTO);
			billProductDTO.setQuantity(billProduct.getQuantity());
			billProductDTO.setUnitPrice(billProduct.getUnitPrice());

			BillDTO billDTO = new BillDTO();
			billDTO.setId(billProduct.getBill().getId());

			billProductDTO.setBill(billDTO);

			return billProductDTO;
		}
		return null;
	}

	@Override
	public List<BillProductDTO> search(String keyword, int start, int length) {
		List<BillProduct> billProducts = billProductDao.search(keyword, start, length);
		List<BillProductDTO> billProductDTOs = new ArrayList<BillProductDTO>();

		for (BillProduct billProduct : billProducts) {
			BillProductDTO billProductDTO = new BillProductDTO();
			billProductDTO.setId(billProduct.getId());
			ProductDTO productDTO = new ProductDTO();
			productDTO.setId(billProduct.getProduct().getId());
			productDTO.setName(billProduct.getProduct().getName());
			productDTO.setPrice(billProduct.getProduct().getPrice());
			productDTO.setSoLuong(billProduct.getProduct().getSoLuong());
			billProductDTO.setProduct(productDTO);
			billProductDTO.setQuantity(billProduct.getQuantity());
			billProductDTO.setUnitPrice(billProduct.getUnitPrice());

			BillDTO billDTO = new BillDTO();
			billDTO.setId(billProduct.getBill().getId());

			billProductDTO.setBill(billDTO);

			billProductDTOs.add(billProductDTO);
		}

		return billProductDTOs;
	}

	@Override
	public int countBillProductDTOWhenSearch(String keyword) {
		
		return countBillProductDTOWhenSearch(keyword);
	}

	@Override
	public List<BillProductDTO> searchByBillId(int billId, int start, int length) {
		
		List<BillProduct> billProducts = billProductDao.searchByBillId(billId, start, length);
		List<BillProductDTO> billProductDTOs = new ArrayList<BillProductDTO>();

		for (BillProduct billProduct : billProducts) {
			BillProductDTO billProductDTO = new BillProductDTO();
			billProductDTO.setId(billProduct.getId());
			ProductDTO productDTO = new ProductDTO();
			productDTO.setId(billProduct.getProduct().getId());
			productDTO.setName(billProduct.getProduct().getName());
			productDTO.setPrice(billProduct.getProduct().getPrice());
			productDTO.setSoLuong(billProduct.getProduct().getSoLuong());
			
			billProductDTO.setProduct(productDTO);
			billProductDTO.setQuantity(billProduct.getQuantity());
			billProductDTO.setUnitPrice(billProduct.getUnitPrice());

			BillDTO billDTO = new BillDTO();
			billDTO.setId(billProduct.getBill().getId());

			billProductDTO.setBill(billDTO);

			billProductDTOs.add(billProductDTO);
		}

		return billProductDTOs;
	}

	@Override
	public int countBillProductWhenSearchByBillId(int billId) {
		
		return billProductDao.countBillProductWhenSearchByBillId(billId);
	}
	
	
	
}
