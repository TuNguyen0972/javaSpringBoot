package com.trungtamjava.controller;

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

import com.trungtamjava.model.CategoryDTO;
import com.trungtamjava.service.CategoryService;

@Controller
@RequestMapping(value="/admin")
public class CategoryController {

	@Autowired 
	CategoryService categoryService;
	

	@GetMapping(value = "/them-the-loai")
	public String addCategory(HttpServletRequest request) {
		request.setAttribute("category", new CategoryDTO());
		return "category/addCategory";
	}
	

	@PostMapping(value = "/them-the-loai")
	public String addCategory(HttpServletRequest request, @ModelAttribute("category") CategoryDTO categoryDTO) {
		categoryService.addCategory(categoryDTO);

		return "redirect:/admin/danh-sach-the-loai";
	}

	@GetMapping(value="/sua-the-loai")
	public String editCategory(HttpServletRequest request,@RequestParam(name = "categoryId" ) int categoryId, Model model){
		model.addAttribute("category", categoryService.getCategoryById(categoryId));
		return "category/editCategory";
	}
	
	@PostMapping(value = "/sua-the-loai")
	public String editCategory(HttpServletRequest request, @ModelAttribute("categoryDTO") CategoryDTO categoryDTO) {

		categoryService.updateCategory(categoryDTO);

		return "redirect:/admin/danh-sach-the-loai";

	}
	
	@GetMapping(value="/xoa-the-loai")
	public String deleteCategory(HttpServletRequest request, @RequestParam(name = "categoryId") int categoryId){
		
		 categoryService.deleteCategory(categoryId);
		
		 return "redirect:/admin/danh-sach-the-loai";
	}
	
	@GetMapping(value = {"/tim-the-loai","/danh-sach-the-loai"})
	public String search(HttpServletRequest request, @RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(value = "page", required = false) Integer page) {
		final int PAGE_SIZE = 2;
		page = page == null ? 1 : page;
		keyword = keyword == null ? "" : keyword;
		int totalUser = categoryService.countCategoryWhenSearch(keyword);
		
		int pageCount = (totalUser % PAGE_SIZE == 0) ? totalUser / PAGE_SIZE : totalUser / PAGE_SIZE + 1;
		
		List<CategoryDTO> listCategorys = categoryService.search(keyword, (page-1)*PAGE_SIZE,PAGE_SIZE);
		List<Integer> listCount = new ArrayList<Integer>();
		for(int i=1;i<=pageCount;i++) {
			listCount.add(i);
		}
//		System.out.println(listCount.size());
		request.setAttribute("categorys", listCategorys);
		request.setAttribute("page", page);
		request.setAttribute("keyword", keyword);
		request.setAttribute("listCount", listCount);
		return "category/listCategory";
	}
}
