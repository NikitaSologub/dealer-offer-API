package ru.alfaleasing.dealer.offer.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.alfaleasing.dealer.offer.api.entity.Connection;

import java.util.List;
import java.util.UUID;

public interface ConnectionRepository extends JpaRepository<Connection, Long> {

    @Query("select c from Connection c join fetch c.dealer d where d.uid = :dealerUid")
    List<Connection> getConnectionsByDealer(UUID dealerUid);
}
