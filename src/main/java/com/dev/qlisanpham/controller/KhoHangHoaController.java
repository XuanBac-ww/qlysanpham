package com.dev.qlisanpham.controller;

import com.dev.qlisanpham.model.KhoHangHoa;
import com.dev.qlisanpham.model.KhoHangHoaId;
import com.dev.qlisanpham.repository.HangHoaRepository;
import com.dev.qlisanpham.repository.KhoHangHoaRepository;
import com.dev.qlisanpham.repository.KhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/khohanghoa")
public class KhoHangHoaController {
    @Autowired
    private KhoHangHoaRepository repo;
    @Autowired
    private KhoRepository khoRepo;
    @Autowired
    private HangHoaRepository hangRepo;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("list", repo.findAll());
        return "khohanghoa/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("khohanghoa", new KhoHangHoa());
        model.addAttribute("khos", khoRepo.findAll());
        model.addAttribute("hangs", hangRepo.findAll());
        return "khohanghoa/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute KhoHangHoa entity) {
        repo.save(entity);
        return "redirect:/khohanghoa";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam Long maKho, @RequestParam Long maHang, Model model) {
        KhoHangHoaId id = new KhoHangHoaId();
        id.setKho(maKho);
        id.setHangHoa(maHang);
        KhoHangHoa entity = repo.findById(id).orElse(null);
        model.addAttribute("khohanghoa", entity);
        model.addAttribute("khos", khoRepo.findAll());
        model.addAttribute("hangs", hangRepo.findAll());
        return "khohanghoa/form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long maKho, @RequestParam Long maHang) {
        KhoHangHoaId id = new KhoHangHoaId();
        id.setKho(maKho);
        id.setHangHoa(maHang);
        repo.deleteById(id);
        return "redirect:/khohanghoa";
    }
}
