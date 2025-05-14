package com.dev.qlisanpham.service;

import com.dev.qlisanpham.model.LoaiHangHoa;
import com.dev.qlisanpham.repository.LoaiHangHoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoaiHangHoaService {

    @Autowired
    LoaiHangHoaRepository loaiHangHoaRepository;


    public List<LoaiHangHoa> findAll() {
        return loaiHangHoaRepository.findAll();
    }

    public void update(Long id, LoaiHangHoa newInfo) {
        Optional<LoaiHangHoa> optional = loaiHangHoaRepository.findById(id);
        if (optional.isPresent()) {
            LoaiHangHoa existing = optional.get();
            existing.setTenLoai(newInfo.getTenLoai());
            existing.setMoTa(newInfo.getMoTa());
            loaiHangHoaRepository.save(existing);
        }
    }
}
