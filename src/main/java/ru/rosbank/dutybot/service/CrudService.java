package ru.rosbank.dutybot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rosbank.dutybot.dto.ShiftDto;
import ru.rosbank.dutybot.entity.Employee;
import ru.rosbank.dutybot.entity.Role;
import ru.rosbank.dutybot.entity.Shift;
import ru.rosbank.dutybot.mapping.ShiftMapper;
import ru.rosbank.dutybot.repository.EmployeeRepository;
import ru.rosbank.dutybot.repository.RoleRepository;
import ru.rosbank.dutybot.repository.ShiftRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CrudService {
    private final ShiftRepository shiftRepository;
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final ShiftMapper shiftMapper;

    public List<ShiftDto> find(LocalDate date, String employeeCode, String roleCode) {
        Example<Shift> example = makeExample(date, employeeCode, roleCode);
        return shiftRepository.findAll(example).stream()
                .map(shiftMapper::map)
                .collect(Collectors.toList());
    }

    public void create(List<ShiftDto> request) {
        for (ShiftDto dto : request) {
            log.info("Saving shift: {}", dto);
            Shift shift = new Shift();
            shift.setDate(dto.getDate());
            shift.setEmployee(employeeRepository.getReferenceById(dto.getEmployeeCode()));
            shift.setRole(roleRepository.getReferenceById(dto.getRoleCode()));
            shiftRepository.save(shift);
        }
    }

    public void delete(List<Long> ids) {
        shiftRepository.deleteAllById(ids);
    }

    private Example<Shift> makeExample(LocalDate date, String employeeCode, String roleCode) {
        Shift probe = new Shift();
        probe.setDate(date);
        if (employeeCode != null) {
            Employee employee = new Employee();
            employee.setCode(employeeCode);
            probe.setEmployee(employee);
        }
        if (roleCode != null) {
            Role role = new Role();
            role.setCode(roleCode);
            probe.setRole(role);
        }
        return Example.of(probe);
    }
}
