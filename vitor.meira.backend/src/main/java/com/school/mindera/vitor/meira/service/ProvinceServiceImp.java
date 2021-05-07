package com.school.mindera.vitor.meira.service;

import com.school.mindera.vitor.meira.command.birth.CreateOrUpdateProvinceDetailsDto;
import com.school.mindera.vitor.meira.command.birth.ProvinceDetailsDto;
import com.school.mindera.vitor.meira.controller.ProvinceController;
import com.school.mindera.vitor.meira.converter.ProvinceConverter;
import com.school.mindera.vitor.meira.error.ErrorMessages;
import com.school.mindera.vitor.meira.exception.ProvinceAlreadyExistsException;
import com.school.mindera.vitor.meira.persistence.entity.ProvinceEntity;
import com.school.mindera.vitor.meira.persistence.repository.ProvinceRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class ProvinceServiceImp implements ProvinceService {
    private static final Logger LOGGER = LogManager.getLogger(ProvinceController.class);
    private final ProvinceRepository provinceRepository;

    public ProvinceServiceImp(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    @Override
    public ProvinceDetailsDto createProvince(CreateOrUpdateProvinceDetailsDto createOrUpdateProvinceDetailsDto) throws ProvinceAlreadyExistsException {
        LOGGER.debug("Creating province {} ", createOrUpdateProvinceDetailsDto);

        ProvinceEntity provinceEntity = ProvinceConverter.fromCreateProvinceDtoToProvinceEntity(createOrUpdateProvinceDetailsDto);

        LOGGER.info("Persisting province into database");
        try {
            provinceRepository.save(provinceEntity);
        } catch (DataIntegrityViolationException sqlException) {
            LOGGER.error(ErrorMessages.PROVINCE_ALREADY_EXISTS, sqlException);
            throw new ProvinceAlreadyExistsException(ErrorMessages.PROVINCE_ALREADY_EXISTS);
        }

        LOGGER.debug("Retrieving created province");

        return ProvinceConverter.fromProvinceEntityToProvinceDetailsDto(provinceEntity);
    }
}
