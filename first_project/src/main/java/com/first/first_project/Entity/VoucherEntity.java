package com.first.first_project.Entity;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vouchers")
public class VoucherEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String code;

  @Column(precision = 8, scale = 3, nullable = false)
  private BigDecimal discount;
  @Column(name = "expire_date")
  private String expire_date;

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCode() {
    return this.code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public BigDecimal getDiscount() {
    return this.discount;
  }

  public void setDiscount(BigDecimal discount) {
    this.discount = discount;
  }

  public String getExpire_date() {
    return this.expire_date;
  }

  public void setExpire_date(String expire_date) {
    this.expire_date = expire_date;
  }


  
}
