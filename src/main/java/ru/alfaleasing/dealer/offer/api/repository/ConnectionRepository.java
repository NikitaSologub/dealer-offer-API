package ru.alfaleasing.dealer.offer.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alfaleasing.dealer.offer.api.model.Connection;
import ru.alfaleasing.dealer.offer.api.model.Dealer;

import java.util.List;
public interface ConnectionRepository extends JpaRepository<Connection, Long> {

    List<Connection> getConnectionsByDealer(Dealer dealer);

}
