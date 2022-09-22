package ru.alfaleasing.dealer.offer.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(of = "uid")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "dealers")
@NamedEntityGraph(
    name = "dealer-entity-graph",
    attributeNodes = {
        @NamedAttributeNode("connections")
    }
)
public class Dealer implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID uid;

    private String name;

    private String location;

    private String region;

    private String inn;

    private String kpp;

    private LocalDateTime createDate;

    private String createAuthor;

    private boolean isDeleted;

    @ToString.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "dealer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Connection> connections = new ArrayList<>();

    @ToString.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "dealer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();

    public void addConnection(Connection connection){
        connection.setDealer(this);
        connections.add(connection);
    }
}
