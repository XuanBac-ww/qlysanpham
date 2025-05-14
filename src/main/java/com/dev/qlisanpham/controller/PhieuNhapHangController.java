package com.dev.qlisanpham.controller;

import com.dev.qlisanpham.model.PhieuNhapHang;
import com.dev.qlisanpham.service.PhieuNhapHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/phieunhaphang")
public class PhieuNhapHangController {
    @Autowired
    private PhieuNhapHangService phieuNhapHangService;

    // Xem chi tiết phiếu nhập
    @GetMapping("/{maPhieuNhap}")
    public String xemChiTiet(@PathVariable Long maPhieuNhap, Model model) {
        PhieuNhapHang phieuNhap = phieuNhapHangService.xemChiTietPhieuNhap(maPhieuNhap);
        model.addAttribute("phieuNhap", phieuNhap);
        return "phieuNhap/chitiet"; // Trả về view: src/main/resources/templates/phieuNhap/chitiet.html
    }

    // Thêm hàng hóa vào phiếu nhập (gọi từ form POST)
    @PostMapping("/{maPhieuNhap}/them-hang")
    public String themHangHoa(
            @PathVariable Long maPhieuNhap,
            @RequestParam Long maHangHoa,
            @RequestParam int soLuong
    ) {
        phieuNhapHangService.themHangHoa(maPhieuNhap, maHangHoa, soLuong);
        return "redirect:/phieu-nhap/" + maPhieuNhap;
    }

    // Xóa hàng hóa khỏi phiếu nhập
    @PostMapping("/{maPhieuNhap}/xoa-hang")
    public String xoaHangHoa(
            @PathVariable Long maPhieuNhap,
            @RequestParam Long maHangHoa
    ) {
        phieuNhapHangService.xoaHangHoa(maPhieuNhap, maHangHoa);
        return "redirect:/phieu-nhap/" + maPhieuNhap;
    }

    @PostMapping("/{maPhieuNhap}/tinh-tong-tien")
    public String capNhatTongTien(@PathVariable Long maPhieuNhap) {
        PhieuNhapHang phieuNhap = phieuNhapHangService.xemChiTietPhieuNhap(maPhieuNhap);
        if (phieuNhap != null) {
            phieuNhapHangService.tinhTongTien(phieuNhap);
        }
        return "redirect:/phieu-nhap/" + maPhieuNhap;
    }
}
