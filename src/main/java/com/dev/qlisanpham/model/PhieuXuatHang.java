package com.dev.qlisanpham.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class PhieuXuatHang {
    @Id
    private Long maPhieuXuat;
    private LocalDate ngayXuat;

    @ManyToOne
    @JoinColumn(name = "maKhachHang")
    private KhachHang khachHang;

    @OneToMany(mappedBy = "phieuXuatHang", cascade = CascadeType.ALL)
    private List<PhieuXuatHangHoa> danhSachHangHoa = new ArrayList<>();

    private double tongTien;

    // Constructors, getters, setters

    public void themHangHoa(HangHoa hangHoa, int soLuong) {
        PhieuXuatHangHoa phieuXuatHangHoa = new PhieuXuatHangHoa();
        phieuXuatHangHoa.setPhieuXuatHang(this);
        phieuXuatHangHoa.setHangHoa(hangHoa);
        phieuXuatHangHoa.setSoLuong(soLuong);
        this.danhSachHangHoa.add(phieuXuatHangHoa);
    }

    public void xoaHangHoa(Long maHang) {
        danhSachHangHoa.removeIf(item -> item.getHangHoa().getMaHang().equals(maHang));
    }

    public double tinhTongTien() {
        this.tongTien = danhSachHangHoa.stream()
                .mapToDouble(item -> item.getHangHoa().getDonGia() * item.getSoLuong())
                .sum();
        return this.tongTien;
    }
}
