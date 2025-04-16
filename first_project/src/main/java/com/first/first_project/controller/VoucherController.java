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

import com.first.first_project.Entity.VoucherEntity;
import com.first.first_project.service.VoucherService;

@RestController
@RequestMapping("/vouchers")
public class VoucherController {

  @Autowired
  private VoucherService voucherService;

  @GetMapping
  public List<VoucherEntity> getAllVouchers() {
    return voucherService.getAllVouchers();
  }
  @GetMapping("/{code}")
  public VoucherEntity getVoucherById(@PathVariable("code") String code) {
    return voucherService.getVoucherByCode(code);
  }
  @PostMapping
  public VoucherEntity createVoucher(@RequestBody VoucherEntity voucher) {
    return voucherService.createVoucher(voucher);
  }

  @DeleteMapping("/{id}")
  public void deleteVoucher(@PathVariable("id") Long id) {
    voucherService.deleteVoucher(id);
  }
  @PutMapping("/{id}")
  public VoucherEntity updateVoucher(@PathVariable("id") Long id, @RequestBody VoucherEntity voucher) {
    return voucherService.updateVoucher(id, voucher);
  }
}
