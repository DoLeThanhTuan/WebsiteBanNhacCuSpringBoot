package com.dolethanhtuan.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dolethanhtuan.dto.RoleDTO;
import com.dolethanhtuan.service.IRoleService;

@RestController
public class RoleAPI {
	@Autowired
	private IRoleService roleService;
	@GetMapping("/api/role")
	public ResponseEntity<?> getAllRole(){
		return ResponseEntity.ok(roleService.getAllRole());
	}
	@PostMapping("/api/role")
	public ResponseEntity<?> saveRole(@RequestBody RoleDTO roleD ){
		return ResponseEntity.ok(roleService.save(roleD));
	}
	@PutMapping("/api/role")
	public ResponseEntity<?> updateRole(@RequestBody RoleDTO roleD){
		return ResponseEntity.ok(roleService.update(roleD));
	}
	@DeleteMapping("/api/role/{roleCode}")
	public ResponseEntity<?> deleteRole(@PathVariable("roleCode") String roleCode){
		roleService.deleteByRoleCode(roleCode);
		return ResponseEntity.ok("success");
	}
}
