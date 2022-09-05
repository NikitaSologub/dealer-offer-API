package ru.alfaleasing.dealer.offer.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alfaleasing.dealer.offer.api.service.MinIOService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Slf4j
public class DealerOfferController {

    private final MinIOService minIOService;

    @PostMapping("/offers-by-api/new")
    public void getOffersByApi(@RequestBody Object offer){
        log.debug(LocalDateTime.now() + " request from avtomir-dealer-provider");
        minIOService.writeFileToMinIO(offer);
    }
}
