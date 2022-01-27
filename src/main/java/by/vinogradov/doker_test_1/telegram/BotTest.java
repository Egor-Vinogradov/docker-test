package by.vinogradov.doker_test_1.telegram;

import by.vinogradov.doker_test_1.events.UserCreationEvent;
import by.vinogradov.doker_test_1.model.User;
import by.vinogradov.doker_test_1.model.api.ECommands;
import by.vinogradov.doker_test_1.service.ChatIdService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
public class BotTest extends TelegramLongPollingBot {

    @Value("${bot.name}")
    private String botUsername;

    @Value("${bot.token}")
    private String botToken;

    @Value("${bot.pin}")
    private String pin;

    private final String QUERY_PIN = "Ввести пин:";

    private final ChatIdService service;

    private final ApplicationEventPublisher eventPublisher;

    public BotTest(ChatIdService service, ApplicationEventPublisher eventPublisher) {
        this.service = service;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void onUpdateReceived(Update update) {
//        if (update.getCallbackQuery().getData().equals(ECommands.PIN)) {
//            System.out.println("123321321321");
//            return;
//        }
//        if (update.hasCallbackQuery()) {
//            System.out.println(update.getCallbackQuery().getData());
//        }
        if (update.hasMessage() && update.getMessage().hasEntities()) {
            SendMessage message = new SendMessage();
            message.setChatId(update.getMessage().getChatId().toString());
            this.service.save(update.getMessage().getChatId().toString());

            this.eventPublisher.publishEvent(new UserCreationEvent(this,
                    update.getMessage().getFrom().getFirstName(),
                    update.getMessage().getChatId()));
//            System.out.println(update.getMessage().getText());
            message.setText("Рад тебя видеть " + update.getMessage().getFrom().getFirstName());
            message.setReplyMarkup(getKeyboard());

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }


        }
    }

    public void sendMessage(String textMessage) throws TelegramApiException {
        SendMessage message = new SendMessage();
        List<User> userList = this.service.findAll();
        for (User user : userList) {
            if (user.isValidation()) {
                message.setChatId(user.getChatIdValue());
                message.setText(textMessage);

                execute(message);
            }
        }
    }

    private InlineKeyboardMarkup getKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText(QUERY_PIN);
        inlineKeyboardButton.setCallbackData(ECommands.PIN.getCommand());

//        InlineKeyboardButton inlineKeyboardButtonAccess = new InlineKeyboardButton();
//        inlineKeyboardButtonAccess.setText(ACCESS_LABEL);
//        inlineKeyboardButtonAccess.setCallbackData(COMMANDS.ACCESS.getCommand());
//
//        InlineKeyboardButton inlineKeyboardButtonDemo = new InlineKeyboardButton();
//        inlineKeyboardButtonDemo.setText(DEMO_LABEL);
//        inlineKeyboardButtonDemo.setCallbackData(COMMANDS.DEMO.getCommand());
//
//        InlineKeyboardButton inlineKeyboardButtonSuccess = new InlineKeyboardButton();
//        inlineKeyboardButtonSuccess.setText(SUCCESS_LABEL);
//        inlineKeyboardButtonSuccess.setCallbackData(COMMANDS.SUCCESS.getCommand());
//
        List<List<InlineKeyboardButton>> keyboardButtons = new ArrayList<>();

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton);
//        keyboardButtonsRow1.add(inlineKeyboardButtonAccess);
//
//        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
//        keyboardButtonsRow2.add(inlineKeyboardButtonSuccess);
//
//        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
//        keyboardButtonsRow3.add(inlineKeyboardButtonDemo);
//
        keyboardButtons.add(keyboardButtonsRow1);
//        keyboardButtons.add(keyboardButtonsRow3);
//        keyboardButtons.add(keyboardButtonsRow2);
//
        inlineKeyboardMarkup.setKeyboard(keyboardButtons);

        return inlineKeyboardMarkup;
    }

    @Override
    public  String  getBotUsername () {
        return this.botUsername;
    }

    @Override
    public  String  getBotToken () {
        return this.botToken;
    }
}
