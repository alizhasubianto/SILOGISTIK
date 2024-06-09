package apap.ti.silogistik2106652000.DTO;

import java.util.stream.Collectors;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import java.util.List;
import apap.ti.silogistik2106652000.DTO.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106652000.DTO.request.UpdateGudangRequestDTO;
import apap.ti.silogistik2106652000.DTO.response.ReadGudangResponseDTO;
import apap.ti.silogistik2106652000.model.Gudang;
import apap.ti.silogistik2106652000.model.GudangBarang;

@Mapper(componentModel = "spring")
public interface GudangMapper {
    Gudang createGudangRequestDTOToGudang(CreateGudangRequestDTO createGudangRequestDTO);
    Gudang updateGudangRequestDTOToGudang(UpdateGudangRequestDTO updateGudangRequestDTO);

    UpdateGudangRequestDTO gudangToUpdateGudangRequestDTO(Gudang gudang);
    ReadGudangResponseDTO gudangToReadGudangResponseDTO(Gudang gudang);

}
