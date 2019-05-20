package dogs.service;

import dogs.model.Dog;
import dogs.model.Image;
import dogs.repo.DogRepository;
import dogs.repo.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DogService {

    @Autowired
    private DogRepository dogRepository;

    @Autowired
    private ImageRepository imageRepository;

    public List<Dog> findAll() {
        return dogRepository.findAll();
    }

    public Dog saveDog(Dog dog) {

        boolean isNew = dog.getId() == null;

        Dog dogDB = isNew ? new Dog() : getDog(dog.getId());
        dogDB.setName(dog.getName());
        dogDB.setBorn(dog.getBorn());
        dogDB.setDescription(dog.getDescription());
        dogDB.setGender(dog.getGender());
        dogDB.setVaccinated(dog.getVaccinated());
        dogDB.setNutered(dog.getNutered());
        dogDB.setWeight(dog.getWeight());
        dogRepository.saveAndFlush(dogDB);

        if (isNew) {
            for (Image image : dog.getImages()) {
                Image imageDB = imageRepository.getOne(image.getId());
                imageDB.setDog(dogDB);
                imageRepository.saveAndFlush(imageDB);
            }

            dogDB.setImages(dog.getImages());
        }

        return dogDB;
    }

    public Dog getDog(Long id) {
        Optional<Dog> dogOpt = dogRepository.findById(id);
        return dogOpt.isPresent() ? dogOpt.get() : null;
    }

    public void deleteDog(Long id) {
        dogRepository.deleteById(id);
    }
}
