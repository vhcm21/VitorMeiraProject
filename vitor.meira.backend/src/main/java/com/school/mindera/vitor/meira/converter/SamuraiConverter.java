package com.school.mindera.vitor.meira.converter;

import com.school.mindera.vitor.meira.command.samurai.CreateOrUpdateSamuraiDetailsDto;
import com.school.mindera.vitor.meira.command.samurai.SamuraiDetailsDto;
import com.school.mindera.vitor.meira.persistence.entity.SamuraiEntity;

public class SamuraiConverter {

    public static SamuraiEntity fromCreateSamuraiDtoToSamuraiEntity(CreateOrUpdateSamuraiDetailsDto createOrUpdateSamuraiDetailsDto) {
        return SamuraiEntity.builder()
                .name(createOrUpdateSamuraiDetailsDto.getName())
                .birthYear(createOrUpdateSamuraiDetailsDto.getBirthYear())
                .deathYear(createOrUpdateSamuraiDetailsDto.getDeathYear())
                .gender(createOrUpdateSamuraiDetailsDto.getGender())
                .build();
    }

    public static SamuraiDetailsDto fromSamuraiEntityToSamuraiDetailsDto(SamuraiEntity samuraiEntity) {
        return SamuraiDetailsDto.builder()
                .id(samuraiEntity.getId())
                .name(samuraiEntity.getName())
                .birthYear(samuraiEntity.getBirthYear())
                .deathYear(samuraiEntity.getDeathYear())
                .gender(samuraiEntity.getGender())
                .build();
    }
}
