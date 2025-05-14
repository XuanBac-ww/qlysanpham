package com.dev.qlisanpham.controller;

import com.dev.qlisanpham.model.PhieuXuatHang;
import com.dev.qlisanpham.service.PhieuXuatHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/phieunhaphang")
public class PhieuXuatHangController {
    @Autowired
    private PhieuXuatHangService phieuXuatHangService;

    @GetMapping
    public String listPhieuXuatHang(Model model) {
        model.addAttribute("phieuXuatHangList", phieuXuatHangService.getAllPhieuXuatHang());
        return "phieu-xuat-hang/list";
    }

    @GetMapping("/them")
    public String showAddForm(Model model) {
        model.addAttribute("phieuXuatHang", new PhieuXuatHang());
        model.addAttribute("khachHangList", phieuXuatHangService.getAllKhachHang());
        return "phieu-xuat-hang/add";
    }

    @PostMapping("/them")
    public String addPhieuXuatHang(@ModelAttribute PhieuXuatHang phieuXuatHang) {
        phieuXuatHang.setNgayXuat(LocalDate.now());
        phieuXuatHangService.savePhieuXuatHang(phieuXuatHang);
        return "redirect:/phieu-xuat-hang";
    }

    @GetMapping("/{maPhieuXuat}")
    public String viewDetail(@PathVariable Long maPhieuXuat, Model model) {
        PhieuXuatHang phieuXuat = phieuXuatHangService.getPhieuXuatHangById(maPhieuXuat);
        model.addAttribute("phieuXuatHang", phieuXuat);
        return "phieu-xuat-hang/detail";
    }

    @GetMapping("/{maPhieuXuat}/them-hang")
    public String showAddHangHoaForm(@PathVariable String maPhieuXuat, Model model) {
        model.addAttribute("maPhieuXuat", maPhieuXuat);
        model.addAttribute("hangHoaList", phieuXuatHangService.getAllHangHoa());
        return "phieu-xuat-hang/add-hang";
    }

    @PostMapping("/{maPhieuXuat}/them-hang")
    public String addHangHoaToPhieuXuat(
            @PathVariable Long maPhieuXuat,
            @RequestParam Long maHang,
            @RequestParam int soLuong) {
        phieuXuatHangService.themHangHoaVaoPhieuXuat(maPhieuXuat, maHang, soLuong);
        return "redirect:/phieu-xuat-hang/" + maPhieuXuat;
    }

    @GetMapping("/{maPhieuXuat}/xoa-hang/{maHang}")
    public String removeHangHoaFromPhieuXuat(
            @PathVariable Long maPhieuXuat,
            @PathVariable Long maHang) {
        phieuXuatHangService.xoaHangHoaKhoiPhieuXuat(maPhieuXuat, maHang);
        return "redirect:/phieu-xuat-hang/" + maPhieuXuat;
    }

    @GetMapping("/{maPhieuXuat}/xoa")
    public String deletePhieuXuatHang(@PathVariable Long maPhieuXuat) {
        phieuXuatHangService.deletePhieuXuatHang(maPhieuXuat);
        return "redirect:/phieu-xuat-hang";
    }
}
