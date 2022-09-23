package ru.alfaleasing.dealer.offer.api.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alfaleasing.dealer.offer.api.service.MinIOService;

import java.io.ByteArrayInputStream;

import static org.apache.http.entity.ContentType.APPLICATION_JSON;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MinIOServiceImpl implements MinIOService {

    private final ObjectMapper objectMapper;
    private final MinioClient minioClient;

    @Value("${app.minio.bucket}")
    private String bucketName;

    /**
     * Метод используется для записи стоков автомобилей в minIO
     *
     * @param response объект стоков валидных и не валидных автомобилей который будет записан в minIO в формате JSON
     */
    @SneakyThrows
    public void writeFileToMinIO(Object response, String filename) {
        log.info("Trying to put object {} like a file {} to minIO", response, filename);
        if (minioClient.bucketExists(getBucket())) {
            byte[] bytes = objectMapper.writeValueAsBytes(response);
            ObjectWriteResponse objectWriteResponse = minioClient.putObject(PutObjectArgs.builder()
                .bucket(bucketName)
                .object(filename)
                .stream(new ByteArrayInputStream(bytes), bytes.length, -1)
                .contentType(APPLICATION_JSON.getMimeType())
                .build());

            log.info("{} successfully uploaded to: container. ObjectWriteResponse = {}", filename, objectWriteResponse);
        } else {
            log.info("Bucket with name {} is not exists", bucketName);
            throw new IllegalArgumentException("");
        }
    }

    /**
     * Метод возвращает minIO bucket
     */
    private BucketExistsArgs getBucket() {
        return BucketExistsArgs.builder()
            .bucket(bucketName)
            .build();
    }
}
