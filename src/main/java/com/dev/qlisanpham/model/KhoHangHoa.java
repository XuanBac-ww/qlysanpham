package com.dev.qlisanpham.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@IdClass(KhoHangHoaId.class)
@Getter
@Setter
public class KhoHangHoa {
    @Id
    @ManyToOne
    @JoinColumn(name = "maKho")
    private Kho kho;

    @Id
    @ManyToOne
    @JoinColumn(name = "maHang")
    private HangHoa hangHoa;

    private Integer soLuong;


    // Getters and setters
}
