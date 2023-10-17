package ru.rosbank.dutybot.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ShiftDto {
    private Long id;
    private LocalDate date;
    private String employeeCode;
    private String employeeTelegram;
    private String employeeName;
    private String employeePhone;
    private String roleCode;
    private String roleDescription;
}
