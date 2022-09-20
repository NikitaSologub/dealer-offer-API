package ru.alfaleasing.dealer.offer.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alfaleasing.dealer.offer.api.entity.Task;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Task getTaskByUid(UUID uid);
}
