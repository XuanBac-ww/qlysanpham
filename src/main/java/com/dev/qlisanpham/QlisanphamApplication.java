package com.dev.qlisanpham;

import com.dev.qlisanpham.model.HangHoa;
import com.dev.qlisanpham.model.Kho;
import com.dev.qlisanpham.model.KhoHangHoa;
import com.dev.qlisanpham.model.LoaiHangHoa;
import com.dev.qlisanpham.repository.HangHoaRepository;
import com.dev.qlisanpham.repository.KhoHangHoaRepository;
import com.dev.qlisanpham.repository.KhoRepository;
import com.dev.qlisanpham.repository.LoaiHangHoaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class QlisanphamApplication {

	public static void main(String[] args) {
		SpringApplication.run(QlisanphamApplication.class, args);
	}
	@Bean
	CommandLineRunner initData(
			LoaiHangHoaRepository loaiRepo,
			HangHoaRepository hangRepo,
			KhoRepository khoRepo,
			KhoHangHoaRepository khoHangRepo
	) {
		return args -> {
			// Dummy LoaiHangHoa
			List<LoaiHangHoa> loais = new ArrayList<>();
			for (int i = 1; i <= 5; i++) {
				LoaiHangHoa loai = new LoaiHangHoa();
				loai.setTenLoai("Loại " + i);
				loai.setMoTa("Mô tả loại " + i);
				loais.add(loaiRepo.save(loai));
			}

			// Dummy Kho
			List<Kho> khos = new ArrayList<>();
			for (int i = 1; i <= 3; i++) {
				Kho kho = new Kho();
				kho.setTenKho("Kho " + i);
				kho.setDiaChi("Địa chỉ kho " + i);
				khos.add(khoRepo.save(kho));
			}

			// Dummy HangHoa
			List<HangHoa> hangs = new ArrayList<>();
			for (int i = 1; i <= 10; i++) {
				HangHoa hang = new HangHoa();
				hang.setTenHang("Hàng hóa " + i);
				hang.setSoLuong(50 + i);
				hang.setDonGia(1000.0 + i * 10);
				hang.setLoaiHangHoa(loais.get(i % loais.size())); // phân loại ngẫu nhiên
				hangs.add(hangRepo.save(hang));
			}

			// Dummy KhoHangHoa
			for (int i = 0; i < 10; i++) {
				KhoHangHoa khoHangHoa = new KhoHangHoa();
				khoHangHoa.setKho(khos.get(i % khos.size()));
				khoHangHoa.setHangHoa(hangs.get(i));
				khoHangHoa.setSoLuong(10 + i);
				khoHangRepo.save(khoHangHoa);
			}

			// Dummy NhaCungCap

			// Dummy PhieuNhapHang


			// Dummy PhieuNhapHangHoa



			System.out.println(">>> Dummy data inserted successfully.");
		};
	}
}
