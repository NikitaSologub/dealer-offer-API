package ru.alfaleasing.dealer.offer.api.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "dealers")
public class Dealer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    UUID uid;
    String name;
    String region;
    String inn;
    String kpp;
    LocalDate createDate;
    String createAuthor;
    Boolean isDeleted;
}
