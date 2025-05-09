package com.dev.qlisanpham.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Kho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maKho;

    private String tenKho;
    private String diaChi;

    @OneToMany(mappedBy = "kho")
    private List<KhoHangHoa> khoHangHoas;

    // Getters and setters
}