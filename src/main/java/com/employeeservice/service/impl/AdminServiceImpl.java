package com.employeeservice.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employeeservice.dto.request.AdminRequestDTO;
import com.employeeservice.dto.response.AdminResponseDTO;
import com.employeeservice.entities.Admin;
import com.employeeservice.exceptions.NoContentFoundException;
import com.employeeservice.repository.AdminRepository;
import com.employeeservice.service.AdminService;
import com.employeeservice.utility.MapperUtility;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	public AdminServiceImpl(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}

	@Override
	public List<AdminResponseDTO> getAllAdminList() throws NoContentFoundException {
		List<AdminResponseDTO> admindtoList =new ArrayList<>();
		AdminResponseDTO adminDto=null;
		List<Admin> adminList = adminRepository.findAll();	
		for(Admin admin :adminList ) {
			 adminDto=new AdminResponseDTO();
			 adminDto.setId(admin.getId());
			 adminDto.setFirstName(admin.getFirstName());
			 adminDto.setLastName(admin.getLastName());
			 adminDto.setUserName(admin.getUserName());
			 admindtoList.add(adminDto);
		}
	//	List<AdminResponseDTO> admindto  = MapperUtility.map(admin, List.class);		
		return admindtoList;
	}
	@Override
	public AdminResponseDTO getAdminById(Long adminId) {
		Admin admin = adminRepository.findById(adminId).get();
		AdminResponseDTO admindto  = MapperUtility.map(admin, AdminResponseDTO.class);		
		return admindto;
	}
	@Override
	public AdminResponseDTO createAdmin(AdminRequestDTO requestDTO) {
		Admin admin = new Admin();
		admin = MapperUtility.map(requestDTO, Admin.class);
		admin.setRoles(Arrays.asList("ROLE_USER"));
		admin= adminRepository.save(admin);
		AdminResponseDTO admindto  = MapperUtility.map(admin, AdminResponseDTO.class);	
		return admindto;
	}

	@Override
	public AdminResponseDTO updateAdmin(Long adminId,AdminRequestDTO requestDTO) {
		Admin admin = adminRepository.findById(adminId).get();
		
		admin.setEmail(requestDTO.getEmail());
		admin.setLastName(requestDTO.getLastName());
		admin.setFirstName(requestDTO.getFirstName());
		Admin updatedAdmin = adminRepository.save(admin);
		AdminResponseDTO admindto  = MapperUtility.map(admin, AdminResponseDTO.class);	
		return admindto;
	}
	@Override
	 public Map<String, Boolean> deleteAdmin(Long adminId){
		Admin admin = adminRepository.findById(adminId).get();				
		adminRepository.delete(admin);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	 }

	@Override
	public AdminResponseDTO fetchAdminByUsername(String username) {
		AdminResponseDTO admindto = new AdminResponseDTO();
		Admin admin = adminRepository.findByUserName(username);
		admindto.setEmail(admin.getEmail());
		admindto.setPassword(admin.getPassword());
		admindto.setId(admin.getId());
		return admindto;
	}

	

}
