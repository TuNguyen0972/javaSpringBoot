package com.trungtamjava.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.trungtamjava.model.UserDTO;
import com.trungtamjava.service.UserService;
@Controller
@RequestMapping(value="/admin")
public class UserController {

	@Autowired
	private UserService userService;
	
//	@GetMapping(value = "/danh-sach-nguoi-dung")
//	public String listUser(HttpServletRequest request,
//				@RequestParam(value = "keyword", required = false) String keyword,
//				@RequestParam(value = "page", required = false) Integer page) {
//			page = page == null ? 1 : page;
//			keyword = keyword == null ? "" : keyword;
//			
////			System.out.println("page"+page);
////			System.out.println("keyword"+keyword);
//			// mac dinh 10 ban ghi 1 trang
//			List<UserDTO> userList = userService.search(keyword, 0, page * 10);
//
//			request.setAttribute("users", userList);
//			request.setAttribute("page", page);
//			request.setAttribute("keyword", keyword);
//		
//		return "user/listUser";
//	}
	

	@GetMapping(value = "/them-nguoi-dung")
	public String addUser(HttpServletRequest request) {
		request.setAttribute("user", new UserDTO());
		return "user/addUser";
	}

	@PostMapping(value = "/them-nguoi-dung")
	public String addUser(HttpServletRequest request, @ModelAttribute("user") UserDTO userDTO) {
		userDTO.setEnable((byte) 1);
		userService.addUser(userDTO);

		return "redirect:/admin/danh-sach-nguoi-dung";
	}
	
	@GetMapping(value = "/sua-nguoi-dung")
	public String editUser(HttpServletRequest request, @RequestParam(name = "userId" ) int userId, Model model) {
//		System.out.println();
		//request.setAttribute("user", userService.getUserById(userId));
		model.addAttribute("user", userService.getUserById(userId));
		return "user/editUser";
	}

	@PostMapping(value = "/sua-nguoi-dung")
	public String editUser(HttpServletRequest request, @ModelAttribute("userDTO") UserDTO userDTO) {

		userService.updateUser(userDTO);

		return "redirect:/admin/danh-sach-nguoi-dung";

	}
	
	@GetMapping(value = "/xoa-nguoi-dung")
	public String deleteUser(HttpServletRequest request, @RequestParam(name = "id") int userId) {
		userService.deleteUser(userId);
		return "redirect:/admin/danh-sach-nguoi-dung";

	}
	
	@GetMapping(value = {"/tim-nguoi-dung","/danh-sach-nguoi-dung"})
	public String search(HttpServletRequest request, @RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(value = "page", required = false) Integer page) {
		final int PAGE_SIZE = 2;
		page = page == null ? 1 : page;
		keyword = keyword == null ? "" : keyword;
		int totalUser = userService.countUserWhenSearch(keyword);
		
		int pageCount = (totalUser % PAGE_SIZE == 0) ? totalUser / PAGE_SIZE : totalUser / PAGE_SIZE + 1;
		
		List<UserDTO> listUsers = userService.search(keyword, (page-1)*PAGE_SIZE,PAGE_SIZE);
		List<Integer> listCount = new ArrayList<Integer>();
		for(int i=1;i<=pageCount;i++) {
			listCount.add(i);
		}
		System.out.println(listCount.size());
		request.setAttribute("users", listUsers);
		request.setAttribute("page", page);
		request.setAttribute("keyword", keyword);
		request.setAttribute("listCount", listCount);
		return "user/listUser";
	}
}
