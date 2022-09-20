package ru.alfaleasing.dealer.offer.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alfaleasing.dealer.offer.api.entity.Dealer;

import java.util.UUID;

public interface DealerRepository extends JpaRepository<Dealer, Long> {

    Dealer getDealerByUid(UUID uid);
}
