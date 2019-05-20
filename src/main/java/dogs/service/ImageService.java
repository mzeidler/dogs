package dogs.service;

import dogs.model.Image;
import dogs.repo.ImageRepository;
import dogs.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public Image save(Image image) {
        return imageRepository.saveAndFlush(image);
    }

    public Image get(Long id) {
        Optional<Image> imageOpt = imageRepository.findById(id);
        return imageOpt.isPresent() ? imageOpt.get() : null;
    }

    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }

}
