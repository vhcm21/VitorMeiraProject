package com.school.mindera.vitor.meira.converter;

import com.school.mindera.vitor.meira.command.clan.ClanDetailsDto;
import com.school.mindera.vitor.meira.command.clan.CreateOrUpdateClanDetailsDto;
import com.school.mindera.vitor.meira.persistence.entity.ClanEntity;

public class ClanConverter {

    public static ClanEntity fromCreateClanDtoToClanEntity(CreateOrUpdateClanDetailsDto createOrUpdateClanDetailsDto) {
        return ClanEntity.builder()
                .name(createOrUpdateClanDetailsDto.getName())
                .birthProvince(createOrUpdateClanDetailsDto.getBirthProvince())
                .build();
    }

    public static ClanDetailsDto fromClanEntityToClanDetailsDto(ClanEntity clanEntity) {
        return ClanDetailsDto.builder()
                .id(clanEntity.getId())
                .name(clanEntity.getName())
                .birthProvince(clanEntity.getBirthProvince())
                .build();
    }
}
