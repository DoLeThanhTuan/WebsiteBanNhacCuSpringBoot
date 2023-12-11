package com.dolethanhtuan.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dolethanhtuan.dto.ProductDTO;
import com.dolethanhtuan.service.IProductService;

@RestController
public class ProductAPI {	
	@Autowired
	private IProductService proService;
	@GetMapping("/api/product")
	public ResponseEntity<?> getAll(){
		return ResponseEntity.ok(proService.getAll()) ;
	}
	
	@PostMapping("/api/product")
	public ResponseEntity<?> savePro(@RequestBody ProductDTO proD){
		return ResponseEntity.ok(proService.save(proD));
	}
	@PutMapping("api/product")
	public ResponseEntity<?> update(@RequestBody ProductDTO proD){
		return ResponseEntity.ok(proService.update(proD));
	}
	@DeleteMapping("/api/product")
	public ResponseEntity<?> delete(@RequestBody long[] ids){
		proService.deleteByIds(ids);
		return ResponseEntity.ok("Delete success");
	}
}
