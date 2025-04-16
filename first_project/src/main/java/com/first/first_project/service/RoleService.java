package com.first.first_project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.first.first_project.Entity.RoleEntity;
import com.first.first_project.repo.RoleRepository;

@Service
public class RoleService {
  @Autowired
  private RoleRepository roleRepository;

  public List<RoleEntity> getAllRoles() {
    return roleRepository.findAll();
  }
  public RoleEntity getRoleById(Long id) {
    return roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
  }
  public RoleEntity createRole(RoleEntity role) {
    return roleRepository.save(role);
  }
  public RoleEntity updateRole(Long id, RoleEntity role) {
    RoleEntity existingRole = getRoleById(id);
    if(!existingRole.getName().equals(role.getName())) {
      existingRole.setName(role.getName());
    }
    return roleRepository.save(existingRole);
  }
  public void deleteRole(Long id) {
    RoleEntity existingRole = getRoleById(id);
    roleRepository.delete(existingRole);
  }
  public RoleEntity getRoleByName(String name) {
    return roleRepository.findByName(name);
  }

}
