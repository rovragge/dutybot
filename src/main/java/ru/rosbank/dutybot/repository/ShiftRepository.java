package ru.rosbank.dutybot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rosbank.dutybot.entity.Shift;

import java.time.LocalDate;
import java.util.List;

public interface ShiftRepository extends JpaRepository<Shift, Long> {
    List<Shift> findByDateOrderByRoleCode(LocalDate date);
}
