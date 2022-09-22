package ru.alfaleasing.dealer.offer.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.alfaleasing.dealer.offer.api.entity.Connection;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ConnectionRepository extends JpaRepository<Connection, Long> {

    @Query("select c from Connection c join fetch c.dealer d where d.uid = :dealerUid")
    List<Connection> getConnectionsByDealer(UUID dealerUid);

    @Query(value = "select max(last_task_date) from connections c join dealers d on c.dealer_id = d.id where d.uid = :dealerUid",nativeQuery = true)
    LocalDateTime getLastTaskDate(@Param("dealerUid") UUID dealerUid);
}
