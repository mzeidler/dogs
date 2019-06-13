package dogs.repo;

import dogs.model.Dog;
import dogs.model.Story;
import dogs.model.StoryType;
import dogs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {
    public List<Story> findByType(StoryType type);
}
