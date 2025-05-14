package com.dev.qlisanpham.model;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class PhieuXuatHangHoaId implements Serializable {
    private Long maPhieuXuat;
    private Long maHang;
}
