package ru.rosbank.dutybot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.rosbank.dutybot.dto.ShiftDto;
import ru.rosbank.dutybot.service.CrudService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dutybot/shift")
@Slf4j
public class ShiftController {

    private final CrudService crudService;

    @GetMapping("")
    public ResponseEntity<List<ShiftDto>> find(@RequestParam(value = "date", required = false) LocalDate date,
                                               @RequestParam(value = "employeeCode", required = false) String employeeCode,
                                               @RequestParam(value = "roleCode", required = false) String roleCode) {
        log.info("FIND: date={} employeeCode={} roleCode={}", date, employeeCode, roleCode);
        List<ShiftDto> data = crudService.find(date, employeeCode, roleCode);
        return ResponseEntity.ok(data);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody List<ShiftDto> request) {
        log.info("CREATE: {}", request);
        crudService.create(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("")
    public ResponseEntity<?> delete(@RequestParam("ids") List<Long> ids) {
        log.info("DELETE: {}", ids);
        crudService.delete(ids);
        return ResponseEntity.ok().build();
    }
}
