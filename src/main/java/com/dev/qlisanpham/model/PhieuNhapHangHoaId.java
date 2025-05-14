package com.dev.qlisanpham.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class PhieuNhapHangHoaId implements Serializable {
    private Long maPhieuNhap;
    private Long maHang;
}
