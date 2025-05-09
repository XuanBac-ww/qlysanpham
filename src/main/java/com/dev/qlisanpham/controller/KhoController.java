package com.dev.qlisanpham.controller;

import com.dev.qlisanpham.model.Kho;
import com.dev.qlisanpham.repository.KhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/kho")
public class KhoController {
    @Autowired
    private KhoRepository repo;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("list", repo.findAll());
        return "kho/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("kho", new Kho());
        return "kho/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Kho kho) {
        repo.save(kho);
        return "redirect:/kho";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("kho", repo.findById(id).orElse(null));
        return "kho/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/kho";
    }
}