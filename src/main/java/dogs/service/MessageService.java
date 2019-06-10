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
import java.util.Optional;

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
                Optional<Dog> dog = dogRepository.findById(message.getDogId());
                if (dog.isPresent()) {
                    message.setDogname(dog.get().getName());
                }
            }
        });
        return messages;
    }

    public void delete(Long id) {
        messageRepository.deleteById(id);
    }
}
