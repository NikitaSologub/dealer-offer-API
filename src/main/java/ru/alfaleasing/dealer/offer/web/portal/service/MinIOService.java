package ru.alfaleasing.dealer.offer.web.portal.service;

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
import java.time.LocalDateTime;

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
    public void writeFileToMinIO(Object response) {
        String filename = LocalDateTime.now() + JSON;
        try {
            createBucketIfNotExists();

            byte[] bytes = objectMapper.writeValueAsBytes(response);
            ObjectWriteResponse objectWriteResponse = minioClient.putObject(PutObjectArgs.builder()
                .bucket(bucketName)
                .object(filename)
                .stream(new ByteArrayInputStream(bytes), bytes.length, -1)
                .contentType(APPLICATION_JSON.getMimeType())
                .build());

            System.out.println("objectWriteResponse = " + objectWriteResponse);
            log.debug("objectWriteResponse = {}", objectWriteResponse);
            System.out.println(filename + " successfully uploaded to: container: " + bucketName + "   blob: " + filename);
            log.debug("{} successfully uploaded to: container: {}   blob: {}", filename, bucketName, filename);
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
            log.debug("Error occurred: " + e);
        }
        System.out.println("Должно быть файл записан в MinIO");
    }

    /**
     * Метод создаёт bucket в пространстве minIO если его не существует
     */
    @SneakyThrows
    private void createBucketIfNotExists() {
        boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!bucketExists) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }
}