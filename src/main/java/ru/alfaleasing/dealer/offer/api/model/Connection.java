package ru.alfaleasing.dealer.offer.api.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.alfaleasing.dealer.offer.api.controller.param.LoadingType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "connections")
public class Connection {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    UUID uid;
    @Enumerated(EnumType.STRING)
    LoadingType type;
    @ManyToOne
    @JoinColumn(name = "dealer_id")
    Dealer dealer;
    LocalDateTime createDate;
    String createAuthor;
    Boolean isUsed;
    LocalDateTime lastTaskDate;
}
