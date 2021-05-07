package com.school.mindera.vitor.meira.controller;

import com.school.mindera.vitor.meira.command.birth.CreateOrUpdateProvinceDetailsDto;
import com.school.mindera.vitor.meira.command.birth.ProvinceDetailsDto;
import com.school.mindera.vitor.meira.error.ErrorMessages;
import com.school.mindera.vitor.meira.exception.VitorMeiraApiException;
import com.school.mindera.vitor.meira.service.ProvinceServiceImp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/provinces")
public class ProvinceController {
    private static final Logger LOGGER = LogManager.getLogger(ProvinceController.class);

    private final ProvinceServiceImp provinceService;

    public ProvinceController(ProvinceServiceImp provinceService) {
        this.provinceService = provinceService;
    }

    @PostMapping
    public ResponseEntity<ProvinceDetailsDto> createProvince(@Valid @RequestBody CreateOrUpdateProvinceDetailsDto createOrUpdateProvinceDetailsDto) {
        LOGGER.info("Request to create new province - {}", createOrUpdateProvinceDetailsDto);

        ProvinceDetailsDto provinceDetailsDto;
        try {
            provinceDetailsDto = provinceService.createProvince(createOrUpdateProvinceDetailsDto);

        } catch (VitorMeiraApiException e) {
            throw e;

        } catch (Exception e) {
            LOGGER.error("Failed to created province - {}", createOrUpdateProvinceDetailsDto, e);
            throw new VitorMeiraApiException(ErrorMessages.OPERATION_FAILED, e);
        }

        LOGGER.info("Province created successfully. Retrieving created province with id {}", provinceDetailsDto.getId());

        return new ResponseEntity<>(provinceDetailsDto, HttpStatus.CREATED);
    }
}
