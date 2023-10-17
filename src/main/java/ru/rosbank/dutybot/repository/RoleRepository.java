package ru.rosbank.dutybot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rosbank.dutybot.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String> {
}
