package ru.alfaleasing.dealer.offer.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.util.UUID;

import static org.apache.http.entity.ContentType.APPLICATION_JSON;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MinIOService {

    private static final String JSON = ".json";
    private final ObjectMapper objectMapper;
    private final MinioClient minioClient;

    @Value("${minio.bucket}")
    private String bucketName;

    /**
     * Метод используется для записи стоков автомобилей в minIO
     *
     * @param response объект стоков валидных и не валидных автомобилей который будет записан в minIO в формате JSON
     */
    @SneakyThrows
    public String writeFileToMinIO(Object response, UUID salonId) {
        String filename = salonId + JSON;
        try {
            createBucketIfNotExists();

            byte[] bytes = objectMapper.writeValueAsBytes(response);
            log.info("Пытаемся положить объект в minIO");
            ObjectWriteResponse objectWriteResponse = minioClient.putObject(PutObjectArgs.builder()
                .bucket(bucketName)
                .object(filename)
                .stream(new ByteArrayInputStream(bytes), bytes.length, -1)
                .contentType(APPLICATION_JSON.getMimeType())
                .build());

            log.info("objectWriteResponse = {}", objectWriteResponse);
            log.info("{} successfully uploaded to: container: {}   blob: {}", filename, bucketName, filename);
        } catch (MinioException e) {
            log.info("Error occurred: " + e);
            return "FILE_IS_NOT_WRITTEN_TO_MINIO_BUCKET";
        }
        return filename;
    }

    /**
     * Метод создаёт bucket в пространстве minIO если его не существует
     */
    @SneakyThrows
    private void createBucketIfNotExists() {// todo - для этого на dev и prod не будет прав. Продумать проброс ошибки
        boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder()
            .bucket(bucketName)
            .build());
        if (!bucketExists) {
            minioClient.makeBucket(MakeBucketArgs.builder()
                .bucket(bucketName)
                .build());
        }
    }
}
