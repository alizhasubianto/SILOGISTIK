package apap.ti.silogistik2106652000.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106652000.DTO.response.ReadPermintaanPengirimanResponseDTO;
import apap.ti.silogistik2106652000.model.Barang;

import apap.ti.silogistik2106652000.model.PermintaanPengiriman;
import apap.ti.silogistik2106652000.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106652000.repository.PermintaanPengirimanBarangDb;
import apap.ti.silogistik2106652000.repository.PermintaanPengirimanDb;

@Service
public class PermintaanPengirimanServiceImpl implements PermintaanPengirimanService{
    
    @Autowired
    PermintaanPengirimanDb permintaanPengirimanDb;

    @Autowired
    PermintaanPengirimanBarangService permintaanPengirimanBarangService;

    @Autowired
    PermintaanPengirimanBarangDb permintaanPengirimanBarangDb;

    @Override
    public Long countAllPermintaanPengiriman(){
        List<PermintaanPengiriman> listPermintaan = permintaanPengirimanDb.findByOrderByWaktuPermintaanAsc();
        List<PermintaanPengiriman> listPermintaanPengirimanToShow = new ArrayList<>();

        for (PermintaanPengiriman permintaanPengiriman : listPermintaan) {

            if (!permintaanPengiriman.getIsCancelled() == true) {
                listPermintaanPengirimanToShow.add(permintaanPengiriman);
            }
        }

        return (long) listPermintaanPengirimanToShow.size();
    }

    @Override
    public List<PermintaanPengiriman> getAllPermintaanPengiriman(){
        List<PermintaanPengiriman> listPermintaan = permintaanPengirimanDb.findByOrderByWaktuPermintaanAsc();
        List<PermintaanPengiriman> listPermintaanPengirimanToShow = new ArrayList<>();

        for (PermintaanPengiriman permintaanPengiriman : listPermintaan) {

            if (!permintaanPengiriman.getIsCancelled() == true) {
                listPermintaanPengirimanToShow.add(permintaanPengiriman);
            }
        }
        

        // Mengurutkan daftar berdasarkan waktu permintaan terbaru
        Collections.sort(listPermintaanPengirimanToShow, Comparator.comparing(PermintaanPengiriman::getWaktuPermintaan).reversed());

        return listPermintaanPengirimanToShow;
    }

    @Override
    public PermintaanPengiriman getPermintaanPengirimanById(long idPermintaanPengiriman){
        for (PermintaanPengiriman permintaanPengiriman: getAllPermintaanPengiriman()){
            if (permintaanPengiriman.getId().equals(idPermintaanPengiriman)){
                return permintaanPengiriman;
            }
        }
        return null;
    }

    @Override
    public void savePermintaanPengiriman(PermintaanPengiriman permintaanPengirimanDTO){
        PermintaanPengiriman permintaanPengiriman = getPermintaanPengirimanById(permintaanPengirimanDTO.getId());

        for (PermintaanPengirimanBarang permintaanPengirimanBarang : permintaanPengirimanDTO.getListPermintaanPengirimanBarang()){
            if (permintaanPengirimanBarang.getId() != null){
                PermintaanPengirimanBarang permintaanPengirimanExisting = permintaanPengirimanBarangService.getPermintaanPengirimanBarangById(permintaanPengirimanBarang.getId());
                permintaanPengirimanExisting.setKuantitasPesanan(permintaanPengirimanBarang.getKuantitasPesanan());
                permintaanPengirimanExisting.setPermintaanPengiriman(permintaanPengirimanDTO);
                permintaanPengirimanBarangDb.save(permintaanPengirimanExisting);
            } 
        }

    }

    @Override
    public void generateNomorPengiriman(PermintaanPengiriman permintaanPengiriman) {
        int totalKuantitas = 0;

        for (PermintaanPengirimanBarang barang : permintaanPengiriman.getListPermintaanPengirimanBarang()){
            totalKuantitas += barang.getKuantitasPesanan();
        }

        if (totalKuantitas > 99){
            totalKuantitas = totalKuantitas % 100;
        }

        String jenisLayanan = "";
        switch (permintaanPengiriman.getJenisLayanan()) {
            case 1:
                jenisLayanan = "SAM";
                break;
            case 2:
                jenisLayanan = "KIL";
                break;
            case 3:
                jenisLayanan = "REG";
                break;
            case 4:
                jenisLayanan = "HEM";
                break;
        }

        LocalDateTime waktuSaatIni = LocalDateTime.now();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm");
        String waktuFormatted = waktuSaatIni.format(formatter);
        LocalDateTime waktuDiformat = LocalDateTime.parse(waktuFormatted, formatter);

        permintaanPengiriman.setWaktuPermintaan(waktuDiformat);

        DateTimeFormatter formatterWaktuPermintaan = DateTimeFormatter.ofPattern("HH:mm:ss");

        String waktuPermintaanString = waktuSaatIni.format(formatterWaktuPermintaan);

        String nomorPengiriman = "REQ" + String.format("%02d", totalKuantitas) + jenisLayanan + waktuPermintaanString;
        permintaanPengiriman.setNomorPengiriman(nomorPengiriman);

        permintaanPengirimanDb.save(permintaanPengiriman);
    }

    @Override
    public Map<Barang, Integer> getDetailBarangPesanan(long id){

        var permintaanPengiriman = getPermintaanPengirimanById(id);
        List<PermintaanPengirimanBarang> listPermintaanPengirimanBarang = permintaanPengiriman.getListPermintaanPengirimanBarang();

        Map<Barang, Integer> detailBarangMap = new HashMap<>();

        for (PermintaanPengirimanBarang permintaanPengirimanBarang : listPermintaanPengirimanBarang){
            Barang barang = permintaanPengirimanBarang.getBarang();

            Integer kuantitas = permintaanPengirimanBarang.getKuantitasPesanan();

            detailBarangMap.put(barang, kuantitas);

            System.out.println(detailBarangMap);
        }

        return detailBarangMap;

    }

    @Override
    public String convertToDescription(ReadPermintaanPengirimanResponseDTO permintaanPengirimanDTO){
        switch (permintaanPengirimanDTO.getJenisLayanan()){
            case 1:
                return "Same Day";
            case 2:
                return "Kilat";
            case 3:
                return "Reguler";
            case 4:
                return "Hemat";
            default:
                return "Jenis layanan tidak valid";
        }
    }

    @Override
    public void cancelPermintaanPengiriman(PermintaanPengiriman permintaanPengiriman){

        if (permintaanPengiriman.getId() != null){
            permintaanPengiriman.setIsCancelled(true);
            permintaanPengirimanDb.save(permintaanPengiriman);
        }
    }

    @Override
    public List<PermintaanPengiriman> filterPermintaanPengiriman(LocalDateTime startDate, LocalDateTime endDate, String sku) {
        List<PermintaanPengiriman> allPermintaanPengiriman = permintaanPengirimanDb.findAll();

        return allPermintaanPengiriman.stream()
            .filter(permintaan -> !permintaan.getIsCancelled())
            .filter(permintaan -> isWithinDateRange(permintaan.getWaktuPermintaan(), startDate, endDate))
            .filter(permintaan -> containsBarang(permintaan, sku))
            .collect(Collectors.toList());
    }

    private boolean isWithinDateRange(LocalDateTime date, LocalDateTime startDate, LocalDateTime endDate) {
        LocalDate dateWithoutTime = date.toLocalDate();
        return (startDate == null || dateWithoutTime.isEqual(startDate.toLocalDate()) || dateWithoutTime.isAfter(startDate.toLocalDate())) &&
            (endDate == null || dateWithoutTime.isEqual(endDate.toLocalDate()) || dateWithoutTime.isBefore(endDate.toLocalDate()));
    }

    private boolean containsBarang(PermintaanPengiriman permintaan, String sku) {
        return sku == null || permintaan.getListPermintaanPengirimanBarang()
            .stream()
            .anyMatch(barang -> barang.getBarang().getSku().equals(sku));
    }
}