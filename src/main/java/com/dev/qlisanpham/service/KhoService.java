package com.dev.qlisanpham.service;

import com.dev.qlisanpham.model.Kho;
import com.dev.qlisanpham.repository.KhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KhoService {

    @Autowired
    private KhoRepository khoRepository;

    public Kho findById(Long id) {
        return khoRepository.findById(id).orElse(null);
    }
}
