package dogs.controller;

import dogs.model.Dog;
import dogs.model.Image;
import dogs.model.Story;
import dogs.service.DogService;
import dogs.service.ImageService;
import dogs.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@CrossOrigin
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private DogService dogService;

    @Autowired
    private StoryService storyService;

    @GetMapping(value = "/api/image/{id}",  produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@PathVariable Long id) throws IOException {
        Image image = imageService.get(id);
        return image.getImage();
    }

    @PostMapping("/api/upload/dog/{id}/{sortid}")
    public Image uploadDogImage(@PathVariable Long id, @PathVariable Long sortid, @RequestParam MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Image image = new Image();
        image.setName(file.getOriginalFilename());
        image.setImage(file.getBytes());
        image.setSize(file.getSize());
        image.setSortid(sortid);

        Dog dog = dogService.getDog(id);
        if (dog != null) {
            image.setDog(dog);
        }

        BufferedImage bimg = ImageIO.read(file.getInputStream());
        image.setHeight((long)bimg.getHeight());
        image.setWidth((long)bimg.getWidth());

        return imageService.save(image);
    }

    @PostMapping("/api/upload/story/{id}/{sortid}")
    public Image uploadStoryImage(@PathVariable Long id, @PathVariable Long sortid, @RequestParam MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Image image = new Image();
        image.setName(file.getOriginalFilename());
        image.setImage(file.getBytes());
        image.setSize(file.getSize());
        image.setSortid(sortid);

        Story story = storyService.getStory(id);
        if (story != null) {
            image.setStory(story);
        }

        BufferedImage bimg = ImageIO.read(file.getInputStream());
        image.setHeight((long)bimg.getHeight());
        image.setWidth((long)bimg.getWidth());

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

    @PostMapping("/api/upload2")
    public String uploadFile2(@RequestParam MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Frontend: <app-ngx-editor [imageEndPoint]="'http://localhost:9002/api/upload2'"
        Image image = new Image();
        image.setName(file.getOriginalFilename());
        image.setImage(file.getBytes());
        image.setSize(file.getSize());

        BufferedImage bimg = ImageIO.read(file.getInputStream());
        image.setHeight((long)bimg.getHeight());
        image.setWidth((long)bimg.getWidth());

        Image imageDB = imageService.save(image);

        return "{ \"url\": \"http://localhost:9002/api/image/"+image.getId()+"\" }"; // URL
    }
}
