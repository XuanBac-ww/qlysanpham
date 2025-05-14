package com.dev.qlisanpham.controller;

import com.dev.qlisanpham.model.LoaiHangHoa;
import com.dev.qlisanpham.service.LoaiHangHoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/loaihanghoa")
public class LoaiHangHoaController {
    @Autowired
    private LoaiHangHoaService loaiHangHoaService;

    @GetMapping
    public String danhSachLoaiHang(Model model) {
        model.addAttribute("dsLoaiHang", loaiHangHoaService.findAll());
        return "loaihang/index";
    }
    @PostMapping("/cap-nhat/{id}")
    public String capNhatLoaiHang(@PathVariable Long id, @ModelAttribute LoaiHangHoa loaiHang) {
        loaiHangHoaService.update(id, loaiHang);
        return "redirect:/loai-hang";
    }
}