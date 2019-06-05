package dogs.controller;

import dogs.model.Dog;
import dogs.model.Image;
import dogs.model.Video;
import dogs.service.DogService;
import dogs.service.ImageService;
import dogs.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private DogService dogService;

    @GetMapping("/api/video/{id}")
    public Video findById(@PathVariable Long id) {
        return videoService.get(id);
    }

    @DeleteMapping("/api/video/delete/{id}")
    public void delete(@PathVariable Long id) {

        Video video = videoService.get(id);
        if (video.getDog() != null) {
            Dog dog = video.getDog();
            dog.getImages().remove(video);
            dogService.saveAndFlush(dog);
        }

        videoService.deleteVideo(id);
    }

    @PostMapping("/api/video/{dogid}")
    public Video saveVideo(@PathVariable Long dogid, @RequestBody Video video) {

        Dog dog = dogService.getDog(dogid);
        if (dog != null) {
            video.setDog(dog);
        }

        return videoService.save(video);
    }

}
