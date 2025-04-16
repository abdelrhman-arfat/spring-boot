package com.first.first_project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.first.first_project.Entity.VoucherEntity;
import com.first.first_project.repo.VoucherRepository;

@Service
public class VoucherService {
  @Autowired
  private VoucherRepository voucherRepository;

  public List<VoucherEntity> getAllVouchers() {
    return voucherRepository.findAll();
  }

  public VoucherEntity getVoucherByCode(String code) {
    return voucherRepository.findByCode(code) ;
  }

  public VoucherEntity createVoucher(VoucherEntity voucher) {
    return voucherRepository.save(voucher);
  }
  public void deleteVoucher(Long id) {
    voucherRepository.deleteById(id);
  }
  public VoucherEntity updateVoucher(Long id, VoucherEntity voucher) {
    VoucherEntity oldVoucher = voucherRepository.findById(id).orElseThrow(() -> new RuntimeException("Voucher not found"));

    if(voucher.getCode() != null) {
      oldVoucher.setCode(voucher.getCode());
    }
    if(voucher.getDiscount() != null) {
      oldVoucher.setDiscount(voucher.getDiscount());
    }
    if(voucher.getExpire_date() != null) {
      oldVoucher.setExpire_date(voucher.getExpire_date());
    }
    return voucherRepository.save(oldVoucher);
  }
  
} 
