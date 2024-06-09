package apap.ti.silogistik2106652000.DTO;

import org.mapstruct.Mapper;

import apap.ti.silogistik2106652000.DTO.request.CreateKaryawanRequestDTO;
import apap.ti.silogistik2106652000.model.Karyawan;

@Mapper(componentModel = "spring")
public interface KaryawanMapper {
    Karyawan createKaryawanRequestDTOToKaryawan(CreateKaryawanRequestDTO createKaryawanRequestDTO);
}
