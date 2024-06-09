package apap.ti.silogistik2106652000.DTO;

import org.mapstruct.Mapper;

import apap.ti.silogistik2106652000.DTO.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106652000.DTO.response.ReadPermintaanPengirimanResponseDTO;
import apap.ti.silogistik2106652000.model.PermintaanPengiriman;

@Mapper(componentModel="spring")
public interface PermintaanPengirimanMapper {
    PermintaanPengiriman createPermintaanPengirimanRequestDTOToPermintaanPengiriman(CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO);

    ReadPermintaanPengirimanResponseDTO permintaanPengirimanToReadPermintaanPengirimanResponseDTO(PermintaanPengiriman permintaanPengiriman);
}
