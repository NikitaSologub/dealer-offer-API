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
import ru.alfaleasing.dealer.offer.api.dto.ExcelCarDTO;
import ru.alfaleasing.dealer.offer.api.dto.XmlCarDTO;

import java.util.List;

@FeignClient(name = "dealer-offer-web-portal-client", url = "${client.dealer-offer-web-portal.url}",
    configuration = DealerOfferWebPortalClient.Config.class)
public interface DealerOfferWebPortalClient {

    @PostMapping(value = "/xml", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    List<XmlCarDTO> getSortedCarsFromXmlFile(@RequestPart(value = "file") MultipartFile file);

    @PostMapping(value = "/xlsx", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    List<ExcelCarDTO> getSortedCarsFromXlsxFile(@RequestPart(value = "file") MultipartFile file);

    @PostMapping(value = "/xls", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    List<ExcelCarDTO> getSortedCarsFromXlsFile(@RequestPart(value = "file") MultipartFile file);

    class Config {
        @Bean
        public SpringFormEncoder multipartFormEncoder() {
            return new SpringFormEncoder(
                new SpringEncoder(() -> new HttpMessageConverters(new RestTemplate().getMessageConverters())));
        }
    }
}
