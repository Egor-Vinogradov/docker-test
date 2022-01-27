package by.vinogradov.doker_test_1.service;

import by.vinogradov.doker_test_1.dao.IChatIdDao;
import by.vinogradov.doker_test_1.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatIdService {

    private final IChatIdDao repository;

    public ChatIdService(IChatIdDao repository) {
        this.repository = repository;
    }

    public User save(String chatIdValue) {
        Optional<User> chatIdOptional = this.repository.findByChatIdValue(chatIdValue);
        User user = null;
        if (!chatIdOptional.isPresent()) {
            user = new User();
            user.setChatIdValue(chatIdValue);
            return this.repository.save(user);
        }
        return chatIdOptional.get();
    }

    public List<User> findAll() {
        return this.repository.findAll();
    }
}
