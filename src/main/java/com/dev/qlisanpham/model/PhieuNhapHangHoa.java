package com.dev.qlisanpham.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PhieuNhapHangHoa {

    @EmbeddedId
    private PhieuNhapHangHoaId id;

    @ManyToOne
    @MapsId("maPhieuNhap")
    @JoinColumn(name = "maPhieuNhap")
    private PhieuNhapHang phieuNhap;

    @ManyToOne
    @MapsId("maHang")
    @JoinColumn(name = "maHang")
    private HangHoa hangHoa;

    private Double DonGia;

    private Integer soLuong;
}
