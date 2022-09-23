package ru.alfaleasing.dealer.offer.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.alfaleasing.dealer.offer.api.constant.LoadingType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@EqualsAndHashCode(of = "uid")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "connections")
public class Connection implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID uid;

    @Enumerated(EnumType.STRING)
    private LoadingType type;

    private LocalDateTime createDate;

    private String createAuthor;

    private boolean isUsed;

    private LocalDateTime lastTaskDate;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Dealer dealer;
}
