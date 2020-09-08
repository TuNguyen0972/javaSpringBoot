package com.trungtamjava.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.trungtamjava.model.CategoryDTO;
import com.trungtamjava.model.ProductDTO;
import com.trungtamjava.service.CategoryService;
import com.trungtamjava.service.ProductService;

@Controller
@RequestMapping(value = "/admin")
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	// @GetMapping(value = "/danh-sach-san-pham")
	// public String listProduct(HttpServletRequest request,
	// @RequestParam(value = "keyword", required = false) String keyword,
	// @RequestParam(value = "page", required = false) Integer page) {
	// page = page == null ? 1 : page;
	// keyword = keyword == null ? "" : keyword;
	//
	// System.out.println("page"+page);
	// System.out.println("keyword"+keyword);
	// // mac dinh 10 ban ghi 1 trang
	// List<ProductDTO> productList = productService.search(keyword, 0, page *
	// 10);
	//
	// request.setAttribute("products", productList);
	// request.setAttribute("page", page);
	// request.setAttribute("keyword", keyword);
	//
	// return "product/listProduct";
	// }

	@GetMapping(value = "/them-san-pham")
	public String addProduct(HttpServletRequest request) {
		request.setAttribute("products", new ProductDTO());
		List<CategoryDTO> listCategories = categoryService.getAllCategorys();
		request.setAttribute("categories", listCategories);

		return "product/addProduct";
	}

	@PostMapping(value = "/them-san-pham")
	public String addUser(HttpServletRequest request, @ModelAttribute("product") ProductDTO productDTO) {
		MultipartFile file = productDTO.getFile();
		// luu file xuong o cung
		try {
			File newfile = new File(
					"D:\\github\\class-spring08\\abc\\ProjectSpringBoot\\src\\main\\resources\\static\\img\\"
							+ productDTO.getFile().getOriginalFilename());
			FileOutputStream fileOutputStream;

			fileOutputStream = new FileOutputStream(newfile);
			fileOutputStream.write(productDTO.getFile().getBytes());
			fileOutputStream.close();
			newfile = new File("D:\\github\\class-spring08\\abc\\ProjectSpringBoot\\target\\classes\\static\\img\\"
					+ productDTO.getFile().getOriginalFilename());
			fileOutputStream = new FileOutputStream(newfile);
			fileOutputStream.write(productDTO.getFile().getBytes());
			fileOutputStream.close();

			// productDTO.setImage(file.getOriginalFilename());

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String image = productDTO.getFile().getOriginalFilename();
		productDTO.setImage(image);
		// luu vao csdl
		productService.addProduct(productDTO);

		return "redirect:/admin/danh-sach-san-pham";
	}

	@GetMapping(value = "/sua-san-pham")
	public String editProduct(HttpServletRequest request, @RequestParam(name = "productId") int productId,
			Model model) {

		// ProductDTO productDTO = productService.getProductDTOById(id);
		// model.addAttribute("product", productDTO);
		model.addAttribute("products", productService.getProductById(productId));
		List<CategoryDTO> listCategories = categoryService.getAllCategorys();
		request.setAttribute("categories", listCategories);
		return "product/editProduct";
	}

	@PostMapping(value = "/sua-san-pham")
	public String editCategory(HttpServletRequest request, @ModelAttribute("productDTO") ProductDTO productDTO) {
		MultipartFile file = productDTO.getFile();
		if (file.getOriginalFilename().equals("")) {
			productDTO.setImage(productService.getProductById(productDTO.getId()).getImage());
		} else {

			// luu file xuong o cung
			try {
				File newfile = new File(
						"D:\\github\\class-spring08\\abc\\ProjectSpringBoot\\target\\classes\\static\\img\\"
								+ productDTO.getFile().getOriginalFilename());
				FileOutputStream fileOutputStream;

				fileOutputStream = new FileOutputStream(newfile);
				fileOutputStream.write(productDTO.getFile().getBytes());
				fileOutputStream.close();
				newfile = new File("D:\\github\\class-spring08\\abc\\ProjectSpringBoot\\target\\classes\\static\\img\\"
						+ productDTO.getFile().getOriginalFilename());
				fileOutputStream = new FileOutputStream(newfile);
				fileOutputStream.write(productDTO.getFile().getBytes());
				fileOutputStream.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		String image = productDTO.getFile().getOriginalFilename();
		productDTO.setImage(image);

		productService.updateProduct(productDTO);

		return "redirect:/admin/danh-sach-san-pham";

	}

	@GetMapping(value = "/xoa-san-pham")
	public String deleteProduct(HttpServletRequest request, @RequestParam(name = "id") int userId) {
		productService.deleteProduct(userId);
		return "redirect:/danh-sach-san-pham";

	}

	@GetMapping(value = { "/tim-san-pham", "/danh-sach-san-pham" })
	public String search(HttpServletRequest request, @RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(value = "page", required = false) Integer page, 
			@RequestParam(name = "category", required = false) Integer categoryId) {
		final int PAGE_SIZE = 2;
		page = page == null ? 1 : page;
		keyword = keyword == null ? "" : keyword;
		
		categoryId = categoryId == null ? -1 : categoryId;
		int totalProduct = productService.countProductWhenSearch(keyword,categoryId);

		int pageCount = (totalProduct % PAGE_SIZE == 0) ? totalProduct / PAGE_SIZE : totalProduct / PAGE_SIZE + 1;

		List<ProductDTO> listProducts = productService.search(keyword,categoryId, (page - 1) * PAGE_SIZE, PAGE_SIZE);
		List<Integer> listCount = new ArrayList<Integer>();
		for (int i = 1; i <= pageCount; i++) {
			listCount.add(i);
		}
		
		List<CategoryDTO> listCategories = categoryService.getAllCategorys();
		
		request.setAttribute("listProducts", listProducts);
		request.setAttribute("category", listCategories);
		request.setAttribute("page", page);
		request.setAttribute("listCount", listCount);
		request.setAttribute("keyword", keyword);
		request.setAttribute("categoryId", categoryId);
		return "product/listProduct";

	}

}
