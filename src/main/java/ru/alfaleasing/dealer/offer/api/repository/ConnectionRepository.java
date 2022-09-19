package ru.alfaleasing.dealer.offer.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alfaleasing.dealer.offer.api.model.Connection;
import ru.alfaleasing.dealer.offer.api.model.Dealer;

import java.util.List;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection, Long> {

    List<Connection> getConnectionsByDealer(Dealer dealer);

}
