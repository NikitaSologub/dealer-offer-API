package ru.alfaleasing.dealer.offer.web.portal.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class MinIOConfig {

    MinioClient minioClient;

    @Value("${minio.url}")
    private String url;

    @Value("${minio.access-key}")
    private String accessKey;

    @Value("${minio.secret-key}")
    private String secretKey;


    @PostConstruct
    void initMinioClient() {
        minioClient = MinioClient.builder()
            .endpoint(url)
            .credentials(accessKey, secretKey)
            .build();
    }

    @Bean
    public MinioClient minioClient() {
        return minioClient;
    }
}