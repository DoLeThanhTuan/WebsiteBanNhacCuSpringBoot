package com.dolethanhtuan.controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dolethanhtuan.dto.PageRS;
import com.dolethanhtuan.dto.ProductDTO;
import com.dolethanhtuan.service.ICategoryService;
import com.dolethanhtuan.service.IProductService;

@Controller
public class HomeController {
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private IProductService productService;
	@GetMapping(path = {"/","/home"})
	public String viewHome(@RequestParam(name="txtSearch",required = false) String txtSearch
			,@RequestParam(name = "categoryId" ,required = false) Integer cateId
			,@RequestParam(name = "index",required = false,defaultValue = "1") Integer index 
			,Model model) 
	{
		this.genericFunction(model,"home");
		PageRS pageRS = new PageRS();
		if(txtSearch != null && cateId != null) {
			pageRS = productService.getAllByCategory_idAndtxtSearch(index, cateId, txtSearch);
		}
		else if(txtSearch != null && cateId == null) {
			pageRS = productService.searchProductByKeywordAndIndex(txtSearch, index);
		}
		else if(cateId != null && txtSearch == null) {
			pageRS = productService.getAllByCategoryAndIndex(cateId, index);
			model.addAttribute("categoryId", cateId);
		}else {
			pageRS = productService.getListProductByIndex(PageRequest.of(index-1, 8));
		}
		if(pageRS == null)
			return "redirect:/home";
		List<ProductDTO> listProduct = pageRS.getListProDTO();
		model.addAttribute("listProduct",listProduct);
		model.addAttribute("pageRS",pageRS);
		return "home";
	}
	@GetMapping("/blog")
	public String viewBlog(Model model) {
		this.genericFunction(model,"blog");
		return "blog";
	}
	@GetMapping("/blog-details")
	public String viewBlogDetails(Model model) {
		this.genericFunction(model,"blog-details");
		return "blog-details";
	}
	@GetMapping("checkout")
	public String viewCheckout(Model model) {
		this.genericFunction(model,"checkout");
		return "checkout";
	}
	@GetMapping("contact")
	public String viewContact(Model model) {
		this.genericFunction(model,"contact");
		return "contact";
	}
	@GetMapping("main")
	public String viewMain() {
		return "main";
	}
	@GetMapping("shop-details")
	public String viewshopdetails(Model model,@RequestParam("id")int idProduct) {
		ProductDTO productD = productService.findOneById(idProduct);
		model.addAttribute("product",productD);
		List<ProductDTO> listProduct = productService.getAllRelative(4, productD.getCategory().getId());
		model.addAttribute("listProduct",listProduct);
		this.genericFunction(model,"shop-details");
		return "shop-details";
	}
	@GetMapping("shop-grid")
	public String viewShopGrid(Model model) {
		this.genericFunction(model,"shop-grid");
		return "shop-grid";
	}
	@GetMapping("shoping-cart")
	public String viewCart(Model model) {
		this.genericFunction(model,"shoping-cart");
		return "shoping-cart";
	}
	
	@GetMapping("/saveProduct")
	public String saveProduct(@RequestParam("name") String name,
							 	@RequestParam("photo") MultipartFile photo,
							 	@RequestParam("fileCV") MultipartFile filecv) {
		// check empty
		if(photo.isEmpty() || filecv.isEmpty())
			return "Lỗi";
		// lấy đường dẫn để lưu file
		Path path = Paths.get("files/");
		try {
			// Lưu photo vào thư mục files
			InputStream ipsPhoto = photo.getInputStream();
			Files.copy(ipsPhoto, path.resolve(photo.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);

			// Lưu filecv vào thư mục files
			InputStream ipsFileCV = photo.getInputStream();
			Files.copy(ipsFileCV, path.resolve(filecv.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}
	
	public void genericFunction(Model model,String title) {
		model.addAttribute("categories",categoryService.findAll());
		model.addAttribute("title",title );
	}
}
