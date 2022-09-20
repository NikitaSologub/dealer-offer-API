package ru.alfaleasing.dealer.offer.api.entity;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public interface BaseEntity<K extends Serializable> {

    K getId();

    void setId(K id);
}
