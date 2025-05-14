package com.dev.qlisanpham.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class NhaCungCap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maNCC;

    private String tenNCC;
    private String diaChi;
    private String soDienThoai;
    private String email;

    @OneToMany(mappedBy = "nhaCungCap", cascade = CascadeType.ALL)
    private List<PhieuNhapHang> danhSachPhieuNhap = new ArrayList<>();

}
