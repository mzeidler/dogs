package dogs.service;

import dogs.model.Dog;
import dogs.model.Image;
import dogs.model.Message;
import dogs.model.Video;
import dogs.repo.DogRepository;
import dogs.repo.ImageRepository;
import dogs.repo.MessageRepository;
import dogs.repo.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DogService {

    @Autowired
    private DogRepository dogRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private VideoRepository videoRepository;

    public List<Dog> findAll() {
        return dogRepository.findAll();
    }

    public Dog saveDog(Dog dog) {

        boolean isNew = dog.getId() == null || dog.getId() == 0;

        Dog dogDB = isNew ? new Dog() : getDog(dog.getId());
        dogDB.setName(dog.getName());
        dogDB.setBorn(dog.getBorn());
        dogDB.setDescription(dog.getDescription());
        dogDB.setGender(dog.getGender());
        dogDB.setVaccinated(dog.getVaccinated());
        dogDB.setNutered(dog.getNutered());
        dogDB.setWeight(dog.getWeight());
        dogDB.setTitleimage(dog.getTitleimage());
        dogDB.setEn(dog.getEn());
        dogDB.setHr(dog.getHr());
        dogDB.setDe(dog.getDe());
        dogDB.setSize(dog.getSize());
        dogDB.setAntiparasite(dog.getAntiparasite());
        dogRepository.saveAndFlush(dogDB);

        if (isNew) {

            for (Image image : dog.getImages()) {
                Image imageDB = imageRepository.getOne(image.getId());
                imageDB.setSortid(image.getSortid());
                imageDB.setDog(dogDB);
                imageRepository.saveAndFlush(imageDB);
            }

            dogDB.setImages(dog.getImages());

            for (Video video : dog.getVideos()) {
                Video videoDB = videoRepository.getOne(video.getId());
                videoDB.setSortid(video.getSortid());
                videoDB.setDog(dogDB);
                videoRepository.saveAndFlush(videoDB);
            }

            dogDB.setVideos(dog.getVideos());

        } else {

            for (Image imageDB : dogDB.getImages()) {
                for (Image i : dog.getImages()) {
                    if (i.getId().equals(imageDB.getId())) {
                        imageDB.setSortid(i.getSortid());
                        imageRepository.saveAndFlush(imageDB);
                    }
                }
            }

            for (Video videoDB : dogDB.getVideos()) {
                for (Video v : dog.getVideos()) {
                    if (v.getId().equals(videoDB.getId())) {
                        videoDB.setSortid(v.getSortid());
                        videoRepository.saveAndFlush(videoDB);
                    }
                }
            }

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
