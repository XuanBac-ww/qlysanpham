package com.dev.qlisanpham.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class HangHoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maHang;

    private String tenHang;
    private Integer soLuong;
    private Double donGia;

    @ManyToOne
    @JoinColumn(name = "maLoai")
    private LoaiHangHoa loaiHangHoa;

    @OneToMany(mappedBy = "hangHoa")
    private List<KhoHangHoa> khoHangHoas;

    // Getters and setters
}