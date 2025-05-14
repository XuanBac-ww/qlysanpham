package com.dev.qlisanpham.service;

import com.dev.qlisanpham.model.HangHoa;
import com.dev.qlisanpham.repository.HangHoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HangHoaService {

    @Autowired
    private HangHoaRepository hangHoaRepository;

    public HangHoa findById(Long id) {
        return hangHoaRepository.findById(id).orElse(null);
    }

    // Lưu hàng hóa
    public void save(HangHoa hangHoa) {
        hangHoaRepository.save(hangHoa);
    }

    // Lấy tất cả hàng hóa
    public List<HangHoa> findAll() {
        return hangHoaRepository.findAll();
    }
}
