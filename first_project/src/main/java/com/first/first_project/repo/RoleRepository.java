package com.first.first_project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.first.first_project.Entity.RoleEntity;

@Repository
public interface RoleRepository  extends JpaRepository<RoleEntity, Long > {
  RoleEntity findByName(String name);
}

