package ru.rosbank.dutybot.service;

public interface TelegramService {
    void sendMessage(Long chat, String message);
}
