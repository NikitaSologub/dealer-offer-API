package ru.alfaleasing.dealer.offer.web.portal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alfaleasing.dealer.offer.web.portal.service.MinIOService;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class DealerOfferController {

    private final MinIOService minIOService;

    @PostMapping("/offers-by-api/new/")
    public void getOffersByApi(@RequestBody Object offer){
        minIOService.writeFileToMinIO(offer);
    }

}
