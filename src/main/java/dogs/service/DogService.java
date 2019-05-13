package dogs.service;

import dogs.model.Dog;
import dogs.repo.DogRepository;
import dogs.repo.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DogService {

    @Autowired
    private DogRepository dogRepository;

    public Dog saveDog(Dog dog) {
        if (dog.getId() == null) {
            Dog dogDB = dogRepository.saveAndFlush(dog);
            return dogDB;
        } else {
            Dog dogDB = getDog(dog.getId());
            dogDB.setName(dog.getName());
            dogDB.setBorn(dog.getBorn());
            dogDB.setDescription(dog.getDescription());
            dogDB.setGender(dog.getGender());
            dogDB.setVaccinated(dog.getVaccinated());
            dogDB.setNutered(dog.getNutered());
            dogDB.setWeight(dog.getWeight());
            return dogRepository.saveAndFlush(dogDB);
        }
    }

    public Dog getDog(Long id) {
        Optional<Dog> dogOpt = dogRepository.findById(id);
        return dogOpt.isPresent() ? dogOpt.get() : null;
    }

    public void deleteDog(Long id) {
        dogRepository.deleteById(id);
    }
}
