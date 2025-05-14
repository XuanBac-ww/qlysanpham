package com.dev.qlisanpham.controller;

import com.dev.qlisanpham.model.NhaCungCap;
import com.dev.qlisanpham.service.NhaCungCapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/nhacungcap")
public class NhaCungCapController {
    @Autowired
    private NhaCungCapService nhaCungCapService;

    // Hiển thị thông tin nhà cung cấp
    @GetMapping("/{maNCC}")
    public String xemThongTin(@PathVariable Long maNCC, Model model) {
        NhaCungCap ncc = nhaCungCapService.findByMaNCC(maNCC);
        if (ncc == null) {
            return "error/404"; // Trang báo lỗi nếu không tìm thấy
        }
        model.addAttribute("ncc", ncc);
        return "nhacungcap/chitiet"; // View hiển thị thông tin
    }

    // Hiển thị form cập nhật
    @GetMapping("/{maNCC}/edit")
    public String showEditForm(@PathVariable Long maNCC, Model model) {
        NhaCungCap ncc = nhaCungCapService.findByMaNCC(maNCC);
        if (ncc == null) {
            return "error/404";
        }
        model.addAttribute("ncc", ncc);
        return "nhacungcap/edit"; // View cập nhật
    }

    // Xử lý cập nhật thông tin
    @PostMapping("/{maNCC}/update")
    public String capNhatThongTin(@PathVariable Long maNCC, @ModelAttribute("ncc") NhaCungCap newInfo) {
        nhaCungCapService.updateNhaCungCap(maNCC, newInfo);
        return "redirect:/nhacungcap/" + maNCC; // Redirect sau khi cập nhật
    }
}
