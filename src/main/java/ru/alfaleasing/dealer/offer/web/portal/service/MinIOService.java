package ru.alfaleasing.dealer.offer.web.portal.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.UploadObjectArgs;
import io.minio.errors.MinioException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.alfaleasing.dealer.offer.web.portal.dto.ExcelSortedCarsResponse;
import ru.alfaleasing.dealer.offer.web.portal.dto.XmlSortedCarsResponse;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class MinIOService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final MinioClient minioClient;

    @Value("${minio.bucket}")
    private String bucketName;

    /**
     * Для создания Json файла и отправки его в MinIO
     *
     * @param response данными о автомобилях в формате которые положим в MinIO в формате Json
     */
    @SneakyThrows
    public void writeRequestToJsonFile(XmlSortedCarsResponse response) {
        String filename = getFilename();
//        try {
//            objectMapper.writeValue(new File(filename), response);
//        } catch (IOException e) {
//            System.out.println("**file does not exists");
//            log.debug("**file does not exists");
//        }
        writeFileToMinIO(filename, response);
        System.out.println("Должно быть файл записан в MinIO");
    }


    /**
     * Для создания Json файла и отправки его в MinIO
     *
     * @param response данными о автомобилях в формате которые положим в MinIO в формате Json
     */
    @SneakyThrows
    public void writeRequestToJsonFile(ExcelSortedCarsResponse response) {
        String filename = getFilename();
//
//        try {
//            objectMapper.writeValue(new File(filename), response);
//        } catch (IOException e) {
//            System.out.println("**file does not exists");
//            log.debug("**file does not exists");
//        }
        writeFileToMinIO(filename, response);
        System.out.println("Должно быть файл записан в MinIO");
    }

    @SneakyThrows
    public void writeFileToMinIO(String fileName, Object response) {
        try {
            createBucketIfNotExists();

//            UploadObjectArgs args = UploadObjectArgs.builder()
//                .bucket(bucketName)
//                .object(fileName.substring("somefiles".length()))
//                .filename(fileName).build();
//            ObjectWriteResponse objectWriteResponse = minioClient.uploadObject(args);
            byte[] bytes = objectMapper.writeValueAsBytes(response);
            ObjectWriteResponse objectWriteResponse = minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object("some-obj")
                    .stream(new ByteArrayInputStream(bytes), bytes.length, 1024)
                    .build());

            System.out.println("objectWriteResponse = " + objectWriteResponse);
            log.debug("objectWriteResponse = {}", objectWriteResponse);
            System.out.println(fileName + " successfully uploaded to: container: " + bucketName + "   blob: " + fileName);
            log.debug("{} successfully uploaded to: container: {}   blob: {}", fileName, bucketName, fileName);
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
            log.debug("Error occurred: " + e);
        }
    }
    private String getFilename() {
        LocalDateTime time = LocalDateTime.now();
        return "somefiles//car" + time.toLocalDate().toString() +
            "-" + time.toLocalTime().getHour() + "-" + time.toLocalTime().getMinute() +
            "-" + time.toLocalTime().getSecond() + "-" + time.toLocalTime().getNano() + ".json";
    }

    @SneakyThrows
    private void createBucketIfNotExists() {
        boolean bucketExists;
        bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!bucketExists) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }
}