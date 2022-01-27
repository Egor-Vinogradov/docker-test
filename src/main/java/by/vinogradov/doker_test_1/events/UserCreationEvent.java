package by.vinogradov.doker_test_1.events;

import org.springframework.context.ApplicationEvent;

public class UserCreationEvent extends ApplicationEvent {

    private final String name;
    private final Long chatId;

    public UserCreationEvent(Object source, String name, Long chatId) {
        super(source);
        this.name = name;
        this.chatId = chatId;
    }

    public String getName() {
        return name;
    }

    public Long getChatId() {
        return chatId;
    }
}
