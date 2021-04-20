package com.school.mindera.vitor.meira.service;

import com.school.mindera.vitor.meira.command.Paginated;
import com.school.mindera.vitor.meira.command.samurai.CreateOrUpdateSamuraiDetailsDto;
import com.school.mindera.vitor.meira.command.samurai.SamuraiDetailsDto;
import com.school.mindera.vitor.meira.controller.SamuraiController;
import com.school.mindera.vitor.meira.converter.SamuraiConverter;
import com.school.mindera.vitor.meira.error.ErrorMessages;
import com.school.mindera.vitor.meira.exception.DatabaseCommunicationException;
import com.school.mindera.vitor.meira.exception.SamuraiNotFoundException;
import com.school.mindera.vitor.meira.persistence.entity.SamuraiEntity;
import com.school.mindera.vitor.meira.persistence.repository.SamuraiRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.school.mindera.vitor.meira.exception.SamuraiAlreadyExistsException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SamuraiServiceImp implements SamuraiService {
    private static final Logger LOGGER = LogManager.getLogger(SamuraiController.class);

    private final SamuraiRepository samuraiRepository;

    public SamuraiServiceImp(SamuraiRepository samuraiRepository) {
        this.samuraiRepository = samuraiRepository;
    }

    @Override
    public SamuraiDetailsDto createSamurai(CreateOrUpdateSamuraiDetailsDto createOrUpdateSamuraiDetailsDto) throws SamuraiAlreadyExistsException {
        LOGGER.debug("Creating samurai {} ", createOrUpdateSamuraiDetailsDto);

        SamuraiEntity samuraiEntity = SamuraiConverter.fromCreateSamuraiDtoToSamuraiEntity(createOrUpdateSamuraiDetailsDto);


        LOGGER.info("Persisting samurai into database");
        try {
            samuraiRepository.save(samuraiEntity);
        } catch (DataIntegrityViolationException sqlException) {
            LOGGER.error(ErrorMessages.SAMURAI_ALREADY_EXISTS, sqlException);
            throw new SamuraiAlreadyExistsException(ErrorMessages.SAMURAI_ALREADY_EXISTS);
        }

        LOGGER.debug("Retrieving created samurai");

        return SamuraiConverter.fromSamuraiEntityToSamuraiDetailsDto(samuraiEntity);
    }

    @Override
    public SamuraiDetailsDto getSamuraiById(long samuraiId) {
        LOGGER.debug("Getting samurai with id {} from database", samuraiId);
        SamuraiEntity samuraiEntity = getSamuraiEntityById(samuraiId);

        return SamuraiConverter.fromSamuraiEntityToSamuraiDetailsDto(samuraiEntity);
    }

    protected SamuraiEntity getSamuraiEntityById(long samuraiId) {
        return samuraiRepository.findById(samuraiId)
                .orElseThrow(() -> {
                    LOGGER.error("The samurai with id {} does not exist in database", samuraiId);
                    return new SamuraiNotFoundException(ErrorMessages.SAMURAI_NOT_FOUND);
                });
    }

    @Override
    public Paginated<SamuraiDetailsDto> getSamuraiList(int page, int size) {

        LOGGER.debug("Getting all samurai from database");
        Page<SamuraiEntity> samuraiList = null;
        try {
            samuraiList = samuraiRepository.findAll(PageRequest.of(page, size, Sort.by("name")));
        } catch (Exception e) {
            LOGGER.error("Failed while getting all samurai from database", e);
            throw new DatabaseCommunicationException(ErrorMessages.DATABASE_COMMUNICATION_ERROR, e);
        }

        LOGGER.debug("Converting list of samurai to SamuraiDetailsDto");
        List<SamuraiDetailsDto> samuraiListResponse = new ArrayList<>();
        for (SamuraiEntity samurai : samuraiList.getContent()) {
            samuraiListResponse.add(SamuraiConverter.fromSamuraiEntityToSamuraiDetailsDto(samurai));
        }

        // Build custom paginated object
        Paginated<SamuraiDetailsDto> results = new Paginated<>(
                samuraiListResponse,
                page,
                samuraiListResponse.size(),
                samuraiList.getTotalPages(),
                samuraiList.getTotalElements());

        return results;
    }

    @Override
    public SamuraiDetailsDto updateSamurai(long samuraiId, CreateOrUpdateSamuraiDetailsDto createOrUpdateSamuraiDetailsDto)
            throws SamuraiNotFoundException {

        LOGGER.debug("Verifying if samurai with id {} exists in database", samuraiId);
        SamuraiEntity samuraiEntity = getSamuraiEntityById(samuraiId);

        samuraiEntity.setName(createOrUpdateSamuraiDetailsDto.getName());
        samuraiEntity.setBirthYear(createOrUpdateSamuraiDetailsDto.getBirthYear());
        samuraiEntity.setDeathYear(createOrUpdateSamuraiDetailsDto.getDeathYear());
        samuraiEntity.setGender(createOrUpdateSamuraiDetailsDto.getGender());

        LOGGER.debug("Updating samurai with id {} in database with new data", samuraiId);
        try {
            samuraiRepository.save(samuraiEntity);
        } catch (Exception e) {
            LOGGER.error("Failed while updating samurai with id {} in database with new data - {}", samuraiId, samuraiEntity, e);
            throw new DatabaseCommunicationException(ErrorMessages.DATABASE_COMMUNICATION_ERROR, e);
        }

        return SamuraiConverter.fromSamuraiEntityToSamuraiDetailsDto(samuraiEntity);
    }

    @Override
    public void deleteSamurai(long samuraiId) {

        LOGGER.debug("Verifying if samurai with id {} exists in database", samuraiId);
        SamuraiEntity user = getSamuraiEntityById(samuraiId);

        LOGGER.debug("Removing samurai with id {} from database", samuraiId);
        try {
            samuraiRepository.delete(user);
        } catch (Exception e) {
            LOGGER.error("Failed while deleting samurai with id {} from database", samuraiId, e);
            throw new DatabaseCommunicationException(ErrorMessages.DATABASE_COMMUNICATION_ERROR, e);
        }
    }
}
