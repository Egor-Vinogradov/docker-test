package by.vinogradov.doker_test_1.service;

import by.vinogradov.doker_test_1.telegram.BotTest;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class MessageService {

    private final BotTest bot;

    public MessageService(BotTest bot) {
        this.bot = bot;
    }

    public void sendMessage(String message) throws IllegalArgumentException {
        try {
            this.bot.sendMessage(message);
        } catch (TelegramApiException e) {
            throw new IllegalArgumentException("Что-то пошло не так! Проверьте телеграм!");
        }
    }
}
