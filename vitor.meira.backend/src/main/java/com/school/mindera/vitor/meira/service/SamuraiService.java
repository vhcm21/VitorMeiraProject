package com.school.mindera.vitor.meira.service;

import com.school.mindera.vitor.meira.command.Paginated;
import com.school.mindera.vitor.meira.command.samurai.CreateOrUpdateSamuraiDetailsDto;
import com.school.mindera.vitor.meira.command.samurai.SamuraiDetailsDto;
import com.school.mindera.vitor.meira.exception.SamuraiAlreadyExistsException;
import com.school.mindera.vitor.meira.exception.SamuraiNotFoundException;

public interface SamuraiService {

    SamuraiDetailsDto createSamurai(CreateOrUpdateSamuraiDetailsDto createOrUpdateSamuraiDetailsDto) throws SamuraiAlreadyExistsException;

    SamuraiDetailsDto getSamuraiById(long samuraiId);

    Paginated<SamuraiDetailsDto> getSamuraiList(int page, int size);

    SamuraiDetailsDto updateSamurai(long samuraiId, CreateOrUpdateSamuraiDetailsDto createOrUpdateSamuraiDetailsDto)
            throws SamuraiNotFoundException;

    void deleteSamurai(long samuraiId);
}
