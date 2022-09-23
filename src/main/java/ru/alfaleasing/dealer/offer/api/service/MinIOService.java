package ru.alfaleasing.dealer.offer.api.service;

public interface MinIOService {

    void writeFileToMinIO(Object response, String filename);
}
