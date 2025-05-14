package com.dev.qlisanpham.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PhieuXuatHangHoa {
    @EmbeddedId
    private PhieuXuatHangHoaId id;

    @ManyToOne
    @MapsId("maPhieuXuat")
    @JoinColumn(name = "maPhieuXuat")
    private PhieuXuatHang phieuXuatHang;

    @ManyToOne
    @MapsId("maHang")
    @JoinColumn(name = "maHang")
    private HangHoa hangHoa;

    private int soLuong;
}
