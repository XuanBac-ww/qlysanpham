package com.dev.qlisanpham.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class LoaiHangHoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maLoai;

    private String tenLoai;
    private String moTa;

    @OneToMany(mappedBy = "loaiHangHoa")
    private List<HangHoa> hangHoas;

    // Getters and setters
}