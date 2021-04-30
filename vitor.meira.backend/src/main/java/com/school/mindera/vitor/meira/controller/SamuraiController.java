package com.school.mindera.vitor.meira.controller;

import com.school.mindera.vitor.meira.command.Paginated;
import com.school.mindera.vitor.meira.command.samurai.CreateOrUpdateSamuraiDetailsDto;
import com.school.mindera.vitor.meira.command.samurai.SamuraiDetailsDto;
import com.school.mindera.vitor.meira.error.ErrorMessages;
import com.school.mindera.vitor.meira.exception.VitorMeiraApiException;
import com.school.mindera.vitor.meira.service.SamuraiServiceImp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/samurais")
public class SamuraiController {
    private static final Logger LOGGER = LogManager.getLogger(SamuraiController.class);

    private final SamuraiServiceImp samuraiService;

    public SamuraiController(SamuraiServiceImp samuraiService) {
        this.samuraiService = samuraiService;
    }

    @PostMapping
    public ResponseEntity<SamuraiDetailsDto> createUser(@Valid @RequestBody CreateOrUpdateSamuraiDetailsDto createOrUpdateSamuraiDetailsDto) {
        LOGGER.info("Request to create new samurai - {}", createOrUpdateSamuraiDetailsDto);

        SamuraiDetailsDto samuraiDetailsDto;
        try {
            samuraiDetailsDto = samuraiService.createSamurai(createOrUpdateSamuraiDetailsDto);

        } catch (VitorMeiraApiException e) {
            throw e;

        } catch (Exception e) {
            LOGGER.error("Failed to created samurai - {}", createOrUpdateSamuraiDetailsDto, e);
            throw new VitorMeiraApiException(ErrorMessages.OPERATION_FAILED, e);
        }

        LOGGER.info("Samurai created successfully. Retrieving created samurai with id {}", samuraiDetailsDto.getId());

        return new ResponseEntity<>(samuraiDetailsDto, HttpStatus.CREATED);
    }

    @GetMapping("/{samuraiId}")
    public ResponseEntity<SamuraiDetailsDto> getSamuraiById(@PathVariable long samuraiId) {
        LOGGER.info("Request to get samurai with id {}", samuraiId);

        SamuraiDetailsDto samuraiDetailsDto;
        try {
            samuraiDetailsDto = samuraiService.getSamuraiById(samuraiId);

        } catch (VitorMeiraApiException e) {
            throw e;

        } catch (Exception e) {
            LOGGER.error("Failed to get samurai with id {}", samuraiId, e);
            throw new VitorMeiraApiException(ErrorMessages.OPERATION_FAILED, e);
        }

        LOGGER.info("Retrieving samurai with id {}", samuraiId);

        return new ResponseEntity<>(samuraiDetailsDto, OK);
    }

    @GetMapping
    public ResponseEntity<Paginated<SamuraiDetailsDto>> getAllSamurai(@RequestParam(defaultValue = "0") int page,
                                                                       @RequestParam(defaultValue = "5") int size) {
        LOGGER.info("Request to get samurai list - page: {}, size: {}", page, size);

        Paginated<SamuraiDetailsDto> samuraiList;
        try {
            samuraiList = samuraiService.getSamuraiList(page, size);

        } catch (VitorMeiraApiException e) {
            throw e;

        } catch (Exception e) {
            LOGGER.error("Failed to get all samurai", e);
            throw new VitorMeiraApiException(ErrorMessages.OPERATION_FAILED, e);
        }

        LOGGER.info("Retrieving samurai list");

        return new ResponseEntity<>(samuraiList, OK);
    }

    @PutMapping("/{samuraiId}")
    public ResponseEntity<SamuraiDetailsDto> updateSamurai(@PathVariable long samuraiId,
                                                     @Valid @RequestBody CreateOrUpdateSamuraiDetailsDto createOrUpdateSamuraiDetailsDto) {
        LOGGER.info("Request to update samurai with id {} - {}", samuraiId, createOrUpdateSamuraiDetailsDto);

        SamuraiDetailsDto samuraiDetailsDto;
        try {
            samuraiDetailsDto = samuraiService.updateSamurai(samuraiId, createOrUpdateSamuraiDetailsDto);

        } catch (VitorMeiraApiException e) {
            throw e;

        } catch (Exception e) {
            LOGGER.error("Failed to update samurai with id {} - {}", samuraiId, createOrUpdateSamuraiDetailsDto, e);
            throw new VitorMeiraApiException(ErrorMessages.OPERATION_FAILED, e);
        }

        LOGGER.info("Samurai with id {} updated successfully. Retrieving updated samurai", samuraiId);

        return new ResponseEntity<>(samuraiDetailsDto, OK);
    }

    @DeleteMapping("/{samuraiId}")
    public ResponseEntity deleteUser(@PathVariable long samuraiId) {
        LOGGER.info("Request to delete samurai with id {}", samuraiId);

        try {
            samuraiService.deleteSamurai(samuraiId);

        } catch (VitorMeiraApiException e) {
            throw e;

        } catch (Exception e) {
            LOGGER.error("Failed to delete samurai with id {}", samuraiId, e);
            throw new VitorMeiraApiException(ErrorMessages.OPERATION_FAILED, e);
        }

        LOGGER.info("Samurai with id {} deleted successfully", samuraiId);

        return new ResponseEntity(OK);
    }
}
