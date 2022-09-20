package ru.alfaleasing.dealer.offer.api.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.TypeDef;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@MappedSuperclass
public interface BaseEntity<K extends Serializable> {

    K getId();

    void setId(K id);
}
