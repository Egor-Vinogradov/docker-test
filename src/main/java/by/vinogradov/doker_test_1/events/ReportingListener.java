package by.vinogradov.doker_test_1.events;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ReportingListener {

    @EventListener(UserCreationEvent.class)
    public void reportUserCreation(UserCreationEvent event) {
        System.out.println("Добавился пользователь: " + event.getName());
    }
}
