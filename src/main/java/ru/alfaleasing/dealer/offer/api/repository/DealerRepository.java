package ru.alfaleasing.dealer.offer.api.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.alfaleasing.dealer.offer.api.entity.Dealer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DealerRepository extends JpaRepository<Dealer, Long> {

    @Query("select d from Dealer d")
    @EntityGraph(value = "dealer-entity-graph", type = EntityGraph.EntityGraphType.FETCH)
    List<Dealer> findAllWithConnections();

    Optional<Dealer> getDealerByUid(UUID uid);
}
