package ru.alfaleasing.dealer.offer.api.client;

import feign.form.spring.SpringFormEncoder;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import ru.alfaleasing.dealer.offer.api.dto.StockDTO;

import java.util.List;

@FeignClient(name = "dealer-offer-web-portal-client", url = "${client.dealer-offer-web-portal.url}",
    configuration = DealerOfferWebPortalClient.Config.class)
public interface DealerOfferWebPortalClient {

    @PostMapping(value = "/v1/stock/xml", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    List<StockDTO> getSortedCarsFromXmlFile(@RequestPart(value = "file") MultipartFile file);

    @PostMapping(value = "/v1/dealer/xlsx", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    List<StockDTO> getSortedCarsFromXlsxFile(@RequestPart(value = "file") MultipartFile file);

    @PostMapping(value = "/v1/dealer/xls", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    List<StockDTO> getSortedCarsFromXlsFile(@RequestPart(value = "file") MultipartFile file);

    class Config {
        @Bean
        public SpringFormEncoder multipartFormEncoder() {
            return new SpringFormEncoder(
                new SpringEncoder(() -> new HttpMessageConverters(new RestTemplate().getMessageConverters())));
        }
    }
}
