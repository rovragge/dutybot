package ru.rosbank.dutybot.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class BasicTelegramService implements TelegramService {

    private static final String API_ENDPOINT = "https://api.telegram.org/bot";

    @Value("${telegram.token}")
    private String token;

    private RestTemplate restTemplate;

    @PostConstruct
    public void init() {
        restTemplate = new RestTemplate();
    }

    @Override
    public void sendMessage(Long chat, String message) {
        Map<String, Object> request = Map.of("chat_id", chat, "text", message);
        try {
            restTemplate.postForObject(API_ENDPOINT + token + "/sendMessage", request, String.class);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
