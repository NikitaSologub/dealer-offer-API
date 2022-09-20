package ru.alfaleasing.dealer.offer.api.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import ru.alfaleasing.dealer.offer.api.controller.param.TaskStatus;
import ru.alfaleasing.dealer.offer.api.dto.TaskResultDTO;

import javax.persistence.Column;
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
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Entity
@Table(name = "tasks")
public class Task implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    UUID uid;
    @ManyToOne
    @JoinColumn(name = "connection_id")
    Connection connection;
    @ManyToOne
    @JoinColumn(name = "dealer_id")
    Dealer dealer;
    LocalDateTime createDate;
    String author;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    TaskStatus status;
    boolean isUsed;
    @Type(type = "jsonb")
    TaskResultDTO taskResult; // jsonb
    Integer offersReceived;
    Integer offersPublished;
}
