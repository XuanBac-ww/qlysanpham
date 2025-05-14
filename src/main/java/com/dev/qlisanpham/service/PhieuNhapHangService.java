package com.dev.qlisanpham.service;

import com.dev.qlisanpham.model.HangHoa;
import com.dev.qlisanpham.model.PhieuNhapHang;
import com.dev.qlisanpham.model.PhieuNhapHangHoa;
import com.dev.qlisanpham.repository.HangHoaRepository;
import com.dev.qlisanpham.repository.PhieuNhapHangHoaRepository;
import com.dev.qlisanpham.repository.PhieuNhapHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PhieuNhapHangService {

    @Autowired
    private PhieuNhapHangRepository phieuNhapHangRepository;

    @Autowired
    private HangHoaRepository hangHoaRepository;

    @Autowired
    private PhieuNhapHangHoaRepository phieuNhapHangHoaRepository;

    /**
     * Thêm hàng hóa vào phiếu nhập
     */
    public void themHangHoa(Long maPhieuNhap, Long maHangHoa, int soLuong) {
        Optional<PhieuNhapHang> optionalPhieuNhap = phieuNhapHangRepository.findById(maPhieuNhap);
        Optional<HangHoa> optionalHangHoa = hangHoaRepository.findById(maHangHoa);

        if (optionalPhieuNhap.isPresent() && optionalHangHoa.isPresent()) {
            PhieuNhapHang phieuNhap = optionalPhieuNhap.get();
            HangHoa hangHoa = optionalHangHoa.get();

            // Tạo chi tiết phiếu nhập
            PhieuNhapHangHoa chiTiet = new PhieuNhapHangHoa();
            chiTiet.setPhieuNhap(phieuNhap);
            chiTiet.setHangHoa(hangHoa);
            chiTiet.setSoLuong(soLuong);
            chiTiet.setDonGia(hangHoa.getDonGia()); // Giả sử HangHoa có đơn giá

            // Thêm vào danh sách
            phieuNhap.getDanhSachHangHoa().add(chiTiet);

            // Cập nhật tổng tiền
            tinhTongTien(phieuNhap);

            // Lưu lại phiếu nhập (cascading sẽ lưu luôn danh sách hàng hóa)
            phieuNhapHangRepository.save(phieuNhap);
        }
    }

    /**
     * Xóa hàng hóa khỏi phiếu nhập
     */
    public void xoaHangHoa(Long maPhieuNhap, Long maHangHoa) {
        Optional<PhieuNhapHang> optionalPhieuNhap = phieuNhapHangRepository.findById(maPhieuNhap);

        if (optionalPhieuNhap.isPresent()) {
            PhieuNhapHang phieuNhap = optionalPhieuNhap.get();

            phieuNhap.getDanhSachHangHoa().removeIf(chiTiet ->
                    chiTiet.getHangHoa().getMaHang().equals(maHangHoa)
            );

            tinhTongTien(phieuNhap);
            phieuNhapHangRepository.save(phieuNhap);
        }
    }

    /**
     * Tính tổng tiền của phiếu nhập
     */
    public void tinhTongTien(PhieuNhapHang phieuNhap) {
        float tong = 0f;
        for (PhieuNhapHangHoa chiTiet : phieuNhap.getDanhSachHangHoa()) {
            tong += (float) (chiTiet.getDonGia() * chiTiet.getSoLuong());
        }
        phieuNhap.setTongTien(tong);
    }

    /**
     * Xem chi tiết phiếu nhập
     */
    public PhieuNhapHang xemChiTietPhieuNhap(Long maPhieuNhap) {
        return phieuNhapHangRepository.findById(maPhieuNhap).orElse(null);
    }


}
