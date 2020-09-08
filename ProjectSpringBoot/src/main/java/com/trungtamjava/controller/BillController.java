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

import com.trungtamjava.model.BillDTO;
import com.trungtamjava.model.BillProductDTO;
import com.trungtamjava.service.BillProductService;
import com.trungtamjava.service.BillService;

@Controller
@RequestMapping(value = "/admin")
public class BillController {

	@Autowired
	BillService billService;

	@Autowired
	BillProductService billProductService;

	

	@GetMapping(value = "xoa-bill")
	public String deleteBill(@RequestParam(name = "billId", required = false) Integer billId) {

		if (billId != null) {
			billService.deleteBillDTO(billId);
		}

		return "redirect:/admin/danh-sach-bill";
	}

	@GetMapping(value = { "/tim-bill", "/danh-sach-bill" })
	public String search(HttpServletRequest request, @RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(name = "page", required = false) Integer page) {

		final int PAGE_SIZE = 2;
		page = page == null ? 1 : page;

		keyword = keyword == null ? "" : keyword;
		int totalBill = billService.countBillDTOWhenSearch(keyword);

		int pageCount = (totalBill % PAGE_SIZE == 0) ? totalBill / PAGE_SIZE : totalBill / PAGE_SIZE + 1;

		List<BillDTO> listBillDTOs = billService.search(keyword, (page - 1) * PAGE_SIZE, PAGE_SIZE);
		List<Integer> listCount = new ArrayList<Integer>();
		for (int i = 1; i <= pageCount; i++) {
			listCount.add(i);
		}

		request.setAttribute("bills", listBillDTOs);
		request.setAttribute("page", page);
		request.setAttribute("listCount", listCount);
		request.setAttribute("keyword", keyword);

		return "bill/listBill";
	}

	int billId;

	@GetMapping(value = "/chi-tiet-bill")
	public String detailBill(HttpServletRequest request, @RequestParam(name = "billId") int billId,
			@RequestParam(name = "page", required = false) Integer page) {

		this.billId = billId;
		final int PAGE_SIZE = 2;
		page = page == null ? 1 : page;

		int totalBillProduct = billProductService.countBillProductWhenSearchByBillId(billId);
		if (totalBillProduct == 0) {
			billService.deleteBillDTO(billId);
			return "redirect:/admin/danh-sach-bill";
		}

		int pageCount = (totalBillProduct % PAGE_SIZE == 0) ? totalBillProduct / PAGE_SIZE
				: totalBillProduct / PAGE_SIZE + 1;

		List<BillProductDTO> billProductDTOs = billProductService.searchByBillId(billId, (page - 1) * PAGE_SIZE,
				PAGE_SIZE);
		List<Integer> listCount = new ArrayList<Integer>();
		for (int i = 1; i <= pageCount; i++) {
			listCount.add(i);
		}

		request.setAttribute("bills", billProductDTOs);
		request.setAttribute("page", page);
		request.setAttribute("listCount", listCount);
		request.setAttribute("billId", billId);

		return "admin/bill/billProduct";
	}

	@GetMapping(value = "bill/delete-bill-product")
	public String DeleteBillProduct(@RequestParam(name = "billProductId", required = false) Integer billProductId) {

		if (billProductId != null) {
			billProductService.deleteBillProductDTO(billProductId);

		}
		return "redirect:/admin/chi-tiet-bill?billId=" + this.billId;
	}
}
