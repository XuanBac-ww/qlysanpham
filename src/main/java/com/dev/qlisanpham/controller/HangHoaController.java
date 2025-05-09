package com.dev.qlisanpham.controller;

import com.dev.qlisanpham.model.HangHoa;
import com.dev.qlisanpham.repository.HangHoaRepository;
import com.dev.qlisanpham.repository.LoaiHangHoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hanghoa")
public class HangHoaController {
    @Autowired
    private HangHoaRepository hangHoaRepo;
    @Autowired
    private LoaiHangHoaRepository loaiRepo;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("list", hangHoaRepo.findAll());
        return "hanghoa/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("hang", new HangHoa());
        model.addAttribute("loais", loaiRepo.findAll());
        return "hanghoa/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute HangHoa hangHoa) {
        hangHoaRepo.save(hangHoa);
        return "redirect:/hanghoa";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("hang", hangHoaRepo.findById(id).orElse(null));
        model.addAttribute("loais", loaiRepo.findAll());
        return "hanghoa/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        hangHoaRepo.deleteById(id);
        return "redirect:/hanghoa";
    }
}