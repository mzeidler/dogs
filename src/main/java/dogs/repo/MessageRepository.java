package dogs.repo;

import dogs.model.Dog;
import dogs.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    public List<Message> findAllByOrderByTimestampDesc();
}
