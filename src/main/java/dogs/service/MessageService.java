package dogs.service;

import dogs.model.Dog;
import dogs.model.Message;
import dogs.repo.DogRepository;
import dogs.repo.ImageRepository;
import dogs.repo.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private DogRepository dogRepository;

    public void save(Message message) {
        message.setTimestamp(LocalDateTime.now().plusHours(2)); // TODO: MySQL Timezone
        messageRepository.saveAndFlush(message);
    }

    public List<Message> findAll() {
        List<Message> messages = messageRepository.findAllByOrderByTimestampDesc();
        messages.forEach(message -> {
            if (message.getDogId() != null) {
                Dog dog = dogRepository.getOne(message.getDogId());
                if (dog != null) {
                    message.setDogname(dog.getName());
                }
            }
        });
        return messages;
    }
}
