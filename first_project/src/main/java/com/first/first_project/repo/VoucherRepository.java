package com.first.first_project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.first.first_project.Entity.VoucherEntity;

@Repository
public interface VoucherRepository  extends JpaRepository<VoucherEntity, Long>  {
  VoucherEntity findByCode(String code);
  
}
