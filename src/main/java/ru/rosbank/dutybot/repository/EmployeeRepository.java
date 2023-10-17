package ru.rosbank.dutybot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rosbank.dutybot.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
