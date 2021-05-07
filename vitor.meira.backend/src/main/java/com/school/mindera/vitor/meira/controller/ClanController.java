package com.school.mindera.vitor.meira.controller;

import com.school.mindera.vitor.meira.command.clan.ClanDetailsDto;
import com.school.mindera.vitor.meira.command.clan.CreateOrUpdateClanDetailsDto;
import com.school.mindera.vitor.meira.error.ErrorMessages;
import com.school.mindera.vitor.meira.exception.VitorMeiraApiException;
import com.school.mindera.vitor.meira.service.ClanServiceImp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/clans")
public class ClanController {
    private static final Logger LOGGER = LogManager.getLogger(ClanController.class);

    private final ClanServiceImp clanService;

    public ClanController(ClanServiceImp clanService) {
        this.clanService = clanService;
    }

    @PostMapping
    public ResponseEntity<ClanDetailsDto> createClan(@Valid @RequestBody CreateOrUpdateClanDetailsDto createOrUpdateClanDetailsDto) {
        LOGGER.info("Request to create new clan - {}", createOrUpdateClanDetailsDto);

        ClanDetailsDto clanDetailsDto;
        try {
            clanDetailsDto = clanService.createClan(createOrUpdateClanDetailsDto);

        } catch (VitorMeiraApiException e) {
            throw e;

        } catch (Exception e) {
            LOGGER.error("Failed to created clan - {}", createOrUpdateClanDetailsDto, e);
            throw new VitorMeiraApiException(ErrorMessages.OPERATION_FAILED, e);
        }

        LOGGER.info("Clan created successfully. Retrieving created clan with id {}", clanDetailsDto.getId());

        return new ResponseEntity<>(clanDetailsDto, HttpStatus.CREATED);
    }
}
