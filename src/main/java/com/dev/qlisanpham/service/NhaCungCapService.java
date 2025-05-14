package com.dev.qlisanpham.service;


import com.dev.qlisanpham.model.NhaCungCap;
import com.dev.qlisanpham.repository.NhaCungCapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NhaCungCapService {
    @Autowired
    private NhaCungCapRepository nhaCungCapRepository;

    public NhaCungCap findByMaNCC(Long maNCC) {
        Optional<NhaCungCap> optional = nhaCungCapRepository.findById(maNCC);
        return optional.orElse(null);
    }

    public void updateNhaCungCap(Long maNCC, NhaCungCap newInfo) {
        Optional<NhaCungCap> optional = nhaCungCapRepository.findById(maNCC);
        if (optional.isPresent()) {
            NhaCungCap existing = optional.get();
            existing.setTenNCC(newInfo.getTenNCC());
            existing.setDiaChi(newInfo.getDiaChi());
            existing.setSoDienThoai(newInfo.getSoDienThoai());
            existing.setEmail(newInfo.getEmail());

            nhaCungCapRepository.save(existing);
        }
    }
}
