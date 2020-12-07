package com.employeeservice.service;

import java.util.List;
import java.util.Map;

import com.employeeservice.dto.request.AdminRequestDTO;
import com.employeeservice.dto.response.AdminResponseDTO;


public interface AdminService {

	List<AdminResponseDTO> getAllAdminList();
	
	AdminResponseDTO getAdminById(Long adminId);
	
	AdminResponseDTO createAdmin(AdminRequestDTO requestDTO);

	AdminResponseDTO updateAdmin(Long adminId,AdminRequestDTO requestDTO);
    
    AdminResponseDTO fetchAdminByUsername(String username);
    
    Map<String, Boolean> deleteAdmin(Long adminId);

}
