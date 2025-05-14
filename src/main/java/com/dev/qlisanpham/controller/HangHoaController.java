package com.dev.qlisanpham.controller;

import com.dev.qlisanpham.model.HangHoa;
import com.dev.qlisanpham.model.Kho;
import com.dev.qlisanpham.model.KhoHangHoa;
import com.dev.qlisanpham.repository.HangHoaRepository;
import com.dev.qlisanpham.repository.LoaiHangHoaRepository;
import com.dev.qlisanpham.service.HangHoaService;
import com.dev.qlisanpham.service.KhoHangHoaService;
import com.dev.qlisanpham.service.KhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/hanghoa")
public class HangHoaController {
    @Autowired
    private HangHoaRepository hangHoaRepo;
    @Autowired
    private LoaiHangHoaRepository loaiRepo;

    @Autowired
    private HangHoaService hangHoaService;

    @Autowired
    private KhoService khoService;

    @Autowired
    private KhoHangHoaService khoHangHoaService;

    // Nhập hàng hóa vào kho
    @PostMapping("/nhapHang")
    public String nhapHang(@RequestParam("maKho") Long maKho,
                           @RequestParam("maHang") Long maHang,
                           @RequestParam("soLuong") Integer soLuong, Model model) {
        Kho kho = khoService.findById(maKho);
        HangHoa hangHoa = hangHoaService.findById(maHang);

        if (kho != null && hangHoa != null) {
            KhoHangHoa khoHangHoa = khoHangHoaService.findByKhoAndHangHoa(kho, hangHoa);
            if (khoHangHoa == null) {
                khoHangHoa = new KhoHangHoa();
                khoHangHoa.setKho(kho);
                khoHangHoa.setHangHoa(hangHoa);
                khoHangHoa.setSoLuong(soLuong);
                khoHangHoaService.save(khoHangHoa);
            } else {
                khoHangHoa.setSoLuong(khoHangHoa.getSoLuong() + soLuong);
                khoHangHoaService.save(khoHangHoa);
            }
            model.addAttribute("message", "Nhập hàng hóa thành công!");
        } else {
            model.addAttribute("message", "Không tìm thấy kho hoặc hàng hóa!");
        }

        return "redirect:/hanghoa/xemDanhSachHangHoa";
    }

    // Xuất hàng hóa từ kho
    @PostMapping("/xuatHang")
    public String xuatHang(@RequestParam("maKho") Long maKho,
                           @RequestParam("maHang") Long maHang,
                           @RequestParam("soLuong") Integer soLuong, Model model) {
        Kho kho = khoService.findById(maKho);
        HangHoa hangHoa = hangHoaService.findById(maHang);

        if (kho != null && hangHoa != null) {
            KhoHangHoa khoHangHoa = khoHangHoaService.findByKhoAndHangHoa(kho, hangHoa);
            if (khoHangHoa != null && khoHangHoa.getSoLuong() >= soLuong) {
                khoHangHoa.setSoLuong(khoHangHoa.getSoLuong() - soLuong);
                khoHangHoaService.save(khoHangHoa);
                model.addAttribute("message", "Xuất hàng hóa thành công!");
            } else {
                model.addAttribute("message", "Số lượng hàng hóa không đủ để xuất!");
            }
        } else {
            model.addAttribute("message", "Không tìm thấy kho hoặc hàng hóa!");
        }

        return "redirect:/hanghoa/xemDanhSachHangHoa";
    }

    // Kiểm tra tồn kho
    @GetMapping("/kiemTraTonKho")
    public String kiemTraTonKho(@RequestParam("maKho") Long maKho, @RequestParam("maHang") Long maHang, Model model) {
        Kho kho = khoService.findById(maKho);
        HangHoa hangHoa = hangHoaService.findById(maHang);

        if (kho != null && hangHoa != null) {
            KhoHangHoa khoHangHoa = khoHangHoaService.findByKhoAndHangHoa(kho, hangHoa);
            if (khoHangHoa != null) {
                model.addAttribute("message", "Tồn kho: " + khoHangHoa.getSoLuong() + " sản phẩm.");
            } else {
                model.addAttribute("message", "Hàng hóa không có trong kho.");
            }
        } else {
            model.addAttribute("message", "Không tìm thấy kho hoặc hàng hóa!");
        }

        return "kiemTraTonKho";
    }

    // Xem danh sách hàng hóa trong kho
    @GetMapping("/xemDanhSachHangHoa")
    public String xemDanhSachHangHoa(Model model) {
        List<HangHoa> hangHoas = hangHoaService.findAll();
        model.addAttribute("hangHoas", hangHoas);
        return "xemDanhSachHangHoa";
    }
}