package com.dev.qlisanpham.controller;

import com.dev.qlisanpham.model.LoaiHangHoa;
import com.dev.qlisanpham.repository.LoaiHangHoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/loaihanghoa")
public class LoaiHangHoaController {
    @Autowired
    private LoaiHangHoaRepository repo;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("list", repo.findAll());
        return "loaihanghoa/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("loai", new LoaiHangHoa());
        return "loaihanghoa/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute LoaiHangHoa loai) {
        repo.save(loai);
        return "redirect:/loaihanghoa";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("loai", repo.findById(id).orElse(null));
        return "loaihanghoa/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/loaihanghoa";
    }
}