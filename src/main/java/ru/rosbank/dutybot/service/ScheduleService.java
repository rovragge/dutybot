package ru.rosbank.dutybot.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.rosbank.dutybot.entity.Shift;
import ru.rosbank.dutybot.repository.ShiftRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleService {
    private final TelegramService telegramService;
    private final ShiftRepository shiftRepository;

    @Value("${telegram.chats}")
    private List<Long> chats;

    @Value("${scheduling.cron}")
    private String cron;

    @PostConstruct
    public void init() {
        log.info("Bot started! Announce cron: {} Chats to announce: {}", cron, chats);
    }

    @Scheduled(cron = "${scheduling.cron}")
    public void announce() {
        List<Shift> shifts = shiftRepository.findByDateOrderByRoleCode(LocalDate.now());
        StringBuilder sb =  new StringBuilder();
        String formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy (EEEE)", new Locale("ru", "RU")));
        sb.append("#Дежурные на ").append(formattedDate).append(":\n");
        for (Shift shift : shifts) {
            sb.append(" - ").append(shift.getRole().getDescription()).append(": ")
                    .append(shift.getEmployee().getFullName())
                    .append(" (").append(shift.getEmployee().getTelegramUsername()).append(", ")
                    .append(shift.getEmployee().getPhone()).append(")\n");
        }
        log.info("Announcing >>> {}", sb);
        for (Long chat : chats) {
            telegramService.sendMessage(chat, sb.toString());
        }
    }
}
