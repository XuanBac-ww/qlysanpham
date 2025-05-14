package com.dev.qlisanpham.service;

import com.dev.qlisanpham.model.HangHoa;
import com.dev.qlisanpham.model.KhachHang;
import com.dev.qlisanpham.model.PhieuXuatHang;
import com.dev.qlisanpham.repository.HangHoaRepository;
import com.dev.qlisanpham.repository.KhachHangRepository;
import com.dev.qlisanpham.repository.PhieuXuatHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhieuXuatHangService {
    @Autowired
    private PhieuXuatHangRepository phieuXuatHangRepository;

    @Autowired
    private HangHoaRepository hangHoaRepository;

    @Autowired
    private KhachHangRepository khachHangRepository;

    public List<PhieuXuatHang> getAllPhieuXuatHang() {
        return phieuXuatHangRepository.findAll();
    }

    public PhieuXuatHang getPhieuXuatHangById(Long maPhieuXuat) {
        return phieuXuatHangRepository.findById(maPhieuXuat)
                .orElseThrow(() -> new RuntimeException("Phiếu xuất hàng không tồn tại"));
    }

    public PhieuXuatHang savePhieuXuatHang(PhieuXuatHang phieuXuatHang) {
        phieuXuatHang.tinhTongTien();
        return phieuXuatHangRepository.save(phieuXuatHang);
    }

    public void deletePhieuXuatHang(Long maPhieuXuat) {
        phieuXuatHangRepository.deleteById(maPhieuXuat);
    }

    public PhieuXuatHang themHangHoaVaoPhieuXuat(Long maPhieuXuat, Long maHang, int soLuong) {
        PhieuXuatHang phieuXuat = getPhieuXuatHangById(maPhieuXuat);
        HangHoa hangHoa = hangHoaRepository.findById(maHang)
                .orElseThrow(() -> new RuntimeException("Hàng hóa không tồn tại"));

        phieuXuat.themHangHoa(hangHoa, soLuong);
        return savePhieuXuatHang(phieuXuat);
    }

    public PhieuXuatHang xoaHangHoaKhoiPhieuXuat(Long maPhieuXuat, Long maHang) {
        PhieuXuatHang phieuXuat = getPhieuXuatHangById(maPhieuXuat);
        phieuXuat.xoaHangHoa(maHang);
        return savePhieuXuatHang(phieuXuat);
    }

    public List<KhachHang> getAllKhachHang() {
        return khachHangRepository.findAll();
    }

    public List<HangHoa> getAllHangHoa() {
        return hangHoaRepository.findAll();
    }
}
