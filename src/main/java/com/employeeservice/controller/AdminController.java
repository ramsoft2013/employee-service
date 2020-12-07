package com.employeeservice.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeeservice.dto.request.AdminRequestDTO;
import com.employeeservice.dto.response.AdminResponseDTO;
import com.employeeservice.service.AdminService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class AdminController {
	
	private final AdminService adminService;
	
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}
	
	@GetMapping("/admin")
	public List<AdminResponseDTO> getAllEmployees() {
		return adminService.getAllAdminList();
	}

	
	@PostMapping("/create")
	public ResponseEntity<?> createAdmin(@RequestBody AdminRequestDTO requestDTO) {
		adminService.createAdmin(requestDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("/admin/{id}")
	public ResponseEntity<?> getAdminById(@PathVariable(value = "id") Long adminId) {
		AdminResponseDTO admin = adminService.getAdminById(adminId);
		return ResponseEntity.ok().body(admin);
	}
	
	@PutMapping("/admin/{id}")
	public ResponseEntity<?> updateADmine(@PathVariable(value = "id") Long adminId,
			@RequestBody AdminRequestDTO adminDetails) {
		AdminResponseDTO admin = adminService.updateAdmin(adminId,adminDetails);
		return ResponseEntity.ok(admin);
	}
	
	@DeleteMapping("/admin/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long adminId){
		return adminService.deleteAdmin(adminId);
		
	}
	
	@GetMapping("/getusers/{username}")
	public  ResponseEntity<?> fetchAdminByUsername(@PathVariable("username") String username) {
		return ok(adminService.fetchAdminByUsername(username));
	}	
	
	@GetMapping("/sayHello/{name}")
	@HystrixCommand(fallbackMethod = "fallBackHello", commandProperties = {
			@HystrixProperty(
					name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"
			)
	})
	public String sayHello(@PathVariable("name") String name) {
		if (name.equalsIgnoreCase("hello"))
			throw new RuntimeException();
		return "success METHOD";
		
	}
	
	public String fallBackHello(String name) {
		return "fallback hello";
	}
	
	
	
	
	
}
