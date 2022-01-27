package by.vinogradov.doker_test_1.controllers;

import by.vinogradov.doker_test_1.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public ResponseEntity<?> getHello(@RequestParam("message") String message) {
        this.messageService.sendMessage(message);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
