package com.school.mindera.vitor.meira.service;

import com.school.mindera.vitor.meira.command.clan.ClanDetailsDto;
import com.school.mindera.vitor.meira.command.clan.CreateOrUpdateClanDetailsDto;
import com.school.mindera.vitor.meira.controller.ClanController;
import com.school.mindera.vitor.meira.converter.ClanConverter;
import com.school.mindera.vitor.meira.error.ErrorMessages;
import com.school.mindera.vitor.meira.exception.ClanAlreadyExistsException;
import com.school.mindera.vitor.meira.persistence.entity.ClanEntity;
import com.school.mindera.vitor.meira.persistence.repository.ClanRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class ClanServiceImp implements ClanService {
    private static final Logger LOGGER = LogManager.getLogger(ClanController.class);
    private final ClanRepository clanRepository;

    public ClanServiceImp(ClanRepository clanRepository) {
        this.clanRepository = clanRepository;
    }

    @Override
    public ClanDetailsDto createClan(CreateOrUpdateClanDetailsDto createOrUpdateClanDetailsDto) throws ClanAlreadyExistsException {
        LOGGER.debug("Creating clan {} ", createOrUpdateClanDetailsDto);

        ClanEntity clanEntity = ClanConverter.fromCreateClanDtoToClanEntity(createOrUpdateClanDetailsDto);

        LOGGER.info("Persisting clan into database");
        try {
            clanRepository.save(clanEntity);
        } catch (DataIntegrityViolationException sqlException) {
            LOGGER.error(ErrorMessages.CLAN_ALREADY_EXISTS, sqlException);
            throw new ClanAlreadyExistsException(ErrorMessages.CLAN_ALREADY_EXISTS);
        }

        LOGGER.debug("Retrieving created clan");

        return ClanConverter.fromClanEntityToClanDetailsDto(clanEntity);
    }
}
