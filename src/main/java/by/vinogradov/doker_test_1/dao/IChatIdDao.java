package by.vinogradov.doker_test_1.dao;

import by.vinogradov.doker_test_1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IChatIdDao extends JpaRepository<User, Long> {

    Optional<User> findByChatIdValue(String chatIdValue);
}
