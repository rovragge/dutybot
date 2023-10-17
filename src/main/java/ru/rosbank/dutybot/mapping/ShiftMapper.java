package ru.rosbank.dutybot.mapping;

import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.rosbank.dutybot.dto.ShiftDto;
import ru.rosbank.dutybot.entity.Shift;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
@Slf4j
public abstract class ShiftMapper {

    @Mapping(target = "employeeCode", source = "employee.code")
    @Mapping(target = "employeeTelegram", source = "employee.telegramUsername")
    @Mapping(target = "employeeName", source = "employee.fullName")
    @Mapping(target = "employeePhone", source = "employee.phone")
    @Mapping(target = "roleCode", source = "role.code")
    @Mapping(target = "roleDescription", source = "role.description")
    public abstract ShiftDto map(Shift shift);

}
