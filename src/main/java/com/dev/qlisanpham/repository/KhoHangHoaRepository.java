package com.dev.qlisanpham.repository;

import com.dev.qlisanpham.model.HangHoa;
import com.dev.qlisanpham.model.Kho;
import com.dev.qlisanpham.model.KhoHangHoa;
import com.dev.qlisanpham.model.KhoHangHoaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KhoHangHoaRepository extends JpaRepository<KhoHangHoa, KhoHangHoaId> {
    KhoHangHoa findByKhoAndHangHoa(Kho kho, HangHoa hangHoa);
}

