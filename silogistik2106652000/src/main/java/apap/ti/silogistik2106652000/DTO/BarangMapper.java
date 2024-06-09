package apap.ti.silogistik2106652000.DTO;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import apap.ti.silogistik2106652000.DTO.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106652000.DTO.request.UpdateBarangRequestDTO;
import apap.ti.silogistik2106652000.DTO.response.ReadBarangResponseDTO;
import apap.ti.silogistik2106652000.model.Barang;

@Mapper(componentModel="spring")
public interface BarangMapper {
    Barang createBarangRequestDTOToBarang(CreateBarangRequestDTO createBarangRequestDTO);
    Barang updateBarangRequestDTOToBarang(UpdateBarangRequestDTO updateBarangRequestDTO);

    ReadBarangResponseDTO barangToReadBarangResponseDTO(Barang barang);
    UpdateBarangRequestDTO barangToUpdateBarangResponseDTO(Barang barang);
    
}
