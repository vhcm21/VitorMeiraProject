package com.school.mindera.vitor.meira.converter;

import com.school.mindera.vitor.meira.command.birth.CreateOrUpdateProvinceDetailsDto;
import com.school.mindera.vitor.meira.command.birth.ProvinceDetailsDto;
import com.school.mindera.vitor.meira.persistence.entity.ProvinceEntity;

public class ProvinceConverter {

    public static ProvinceEntity fromCreateProvinceDtoToProvinceEntity(CreateOrUpdateProvinceDetailsDto createOrUpdateProvinceDetailsDto) {
        return ProvinceEntity.builder()
                .name(createOrUpdateProvinceDetailsDto.getName())
                .build();
    }

    public static ProvinceDetailsDto fromProvinceEntityToProvinceDetailsDto(ProvinceEntity clanEntity) {
        return ProvinceDetailsDto.builder()
                .id(clanEntity.getId())
                .name(clanEntity.getName())
                .build();
    }
}