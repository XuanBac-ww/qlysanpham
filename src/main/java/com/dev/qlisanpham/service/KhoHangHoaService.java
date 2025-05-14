package com.dev.qlisanpham.service;

import com.dev.qlisanpham.model.HangHoa;
import com.dev.qlisanpham.model.Kho;
import com.dev.qlisanpham.model.KhoHangHoa;
import com.dev.qlisanpham.repository.KhoHangHoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KhoHangHoaService {

    @Autowired
    private KhoHangHoaRepository khoHangHoaRepository;

    public KhoHangHoa findByKhoAndHangHoa(Kho kho, HangHoa hangHoa) {
        return khoHangHoaRepository.findByKhoAndHangHoa(kho, hangHoa);
    }

    public void save(KhoHangHoa khoHangHoa) {
        khoHangHoaRepository.save(khoHangHoa);
    }

    public List<KhoHangHoa> findAll() {
        return khoHangHoaRepository.findAll();
    }
}
