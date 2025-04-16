package com.first.first_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.first.first_project.Entity.RoleEntity;
import com.first.first_project.service.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {

  @Autowired
  private RoleService roleService;

  @GetMapping
  public List<RoleEntity> getAllRoles() {
    return roleService.getAllRoles();
  }

  @GetMapping("/name/{name}")
  public RoleEntity getRoleByName(@PathVariable("name") String name) {
    return roleService.getRoleByName(name);
  }

  @GetMapping("/{id}")
  public RoleEntity getRoleById(@PathVariable("id") Long id) {
    return roleService.getRoleById(id);
  }

  @PostMapping
  public RoleEntity createRole(@RequestBody RoleEntity role) {
    return roleService.createRole(role);
  }
  @PutMapping("/{id}")
  public RoleEntity updateRole(@PathVariable("id") Long id, @RequestBody RoleEntity role) {
    return roleService.updateRole(id, role);
  }
  @DeleteMapping("/{id}")
  public void deleteRole(@PathVariable("id") Long id) {
    roleService.deleteRole(id);
  }

}
