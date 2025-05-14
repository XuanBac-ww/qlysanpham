package com.dev.qlisanpham.repository;

import com.dev.qlisanpham.model.NhaCungCap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NhaCungCapRepository extends JpaRepository<NhaCungCap,Long> {
    NhaCungCap findByMaNCC(String maNCC);
}
