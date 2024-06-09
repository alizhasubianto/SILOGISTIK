package apap.ti.silogistik2106652000;

import java.util.Locale;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.github.javafaker.Faker;

import apap.ti.silogistik2106652000.DTO.GudangMapper;
import apap.ti.silogistik2106652000.DTO.KaryawanMapper;
import apap.ti.silogistik2106652000.DTO.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106652000.DTO.request.CreateKaryawanRequestDTO;

import apap.ti.silogistik2106652000.service.GudangService;
import apap.ti.silogistik2106652000.service.KaryawanService;
import jakarta.transaction.Transactional;

@SpringBootApplication
public class Silogistik2106652000Application {

	public static void main(String[] args) {
		SpringApplication.run(Silogistik2106652000Application.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner run(GudangService gudangService, KaryawanService karyawanService, GudangMapper gudangMapper, KaryawanMapper karyawanMapper){
		return args -> {
			var faker = new Faker(new Locale("in-ID"));
			
			String[] namaGudang = {"Gudang Penyimpanan", "Gudang Logistik", "Gudang Campuran", "Gudang Kecil", "Gudang Cabang", "Gudang Pusat"};


		for(int i = 0; i < 6; i++){
			// Membuat fake data memanfaatkan Java Faker
			String randomNamaGudang = faker.options().option(namaGudang);
			CreateGudangRequestDTO gudangDTO = new CreateGudangRequestDTO();
			gudangDTO.setNama(randomNamaGudang);
			gudangDTO.setAlamatGudang(faker.address().fullAddress());

			var gudang = gudangMapper.createGudangRequestDTOToGudang(gudangDTO);
			gudangService.createGudang(gudang);
		}

		for(int j = 0; j < 6; j++){
			CreateKaryawanRequestDTO karyawanDTO = new CreateKaryawanRequestDTO();
			karyawanDTO.setNama(faker.name().fullName());
			karyawanDTO.setJenisKelamin(faker.random().nextInt(1, 2));
			karyawanDTO.setTanggalLahir(faker.date().birthday());
		
			var karyawan = karyawanMapper.createKaryawanRequestDTOToKaryawan(karyawanDTO);
			karyawanService.createKaryawan(karyawan);
		}
	};
	}
	

}
