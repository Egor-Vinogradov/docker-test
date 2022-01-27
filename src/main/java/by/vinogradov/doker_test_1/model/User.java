package by.vinogradov.doker_test_1.model;

import javax.persistence.*;

@Entity
@Table(schema = "public")
public class User {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String chatIdValue;

    private boolean validation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChatIdValue() {
        return chatIdValue;
    }

    public void setChatIdValue(String chatIdValue) {
        this.chatIdValue = chatIdValue;
    }

    public boolean isValidation() {
        return validation;
    }

    public void setValidation(boolean validation) {
        this.validation = validation;
    }
}

