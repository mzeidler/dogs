package dogs.controller;

import dogs.model.Dog;
import dogs.model.Image;
import dogs.service.DogService;
import dogs.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.IOException;

@RestController
@CrossOrigin
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private DogService dogService;

    @GetMapping(value = "/api/image/{id}",  produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@PathVariable Long id) throws IOException {
        Image image = imageService.get(id);
        return image.getImage();
    }

    @PostMapping("/api/upload/{id}/{sortid}")
    public Image uploadFile(@PathVariable Long id, @PathVariable Long sortid, @RequestParam MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Image image = new Image();
        image.setName(file.getOriginalFilename());
        image.setImage(file.getBytes());
        image.setSize(file.getSize());
        image.setSortid(sortid);

        Dog dog = dogService.getDog(id);
        if (dog != null) {
            image.setDog(dog);
        }

        return imageService.save(image);
    }

    @DeleteMapping("/api/image/delete/{id}")
    public void delete(@PathVariable Long id) {

        Image image = imageService.get(id);
        if (image.getDog() != null) {
            Dog dog = image.getDog();
            dog.getImages().remove(image);
            dogService.saveDog(dog);
        }

        imageService.deleteImage(id);
    }
}
