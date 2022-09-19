package ru.alfaleasing.dealer.offer.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alfaleasing.dealer.offer.api.model.Dealer;

import java.util.UUID;

@Repository
public interface DealerRepository extends JpaRepository<Dealer, Long> {

    Dealer getDealerByUid(UUID uid);
}
