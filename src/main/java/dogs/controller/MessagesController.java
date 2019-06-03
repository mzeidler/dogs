package dogs.controller;

import dogs.model.Dog;
import dogs.model.Message;
import dogs.repo.MessageRepository;
import dogs.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class MessagesController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/api/message")
    public void message(@RequestBody Message message) {
        messageService.save(message);
    }

    @GetMapping("/api/message")
    public List<Message> findAll() {
        return messageService.findAll();
    }
}
