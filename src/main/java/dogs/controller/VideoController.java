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

@RestController
@CrossOrigin
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private DogService dogService;

    @GetMapping(value = "/api/video/{id}",  produces = "video/mp4")
    public @ResponseBody byte[] getVideo(@PathVariable Long id) throws IOException {
        Video video = videoService.get(id);
        return video.getVideo();
    }

    @PostMapping("/api/video/{id}/{sortid}")
    public Video uploadFile(@PathVariable Long id, @PathVariable Long sortid, @RequestParam MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Video video = new Video();
        video.setName(file.getOriginalFilename());
        video.setVideo(file.getBytes());
        video.setSize(file.getSize());
        video.setSortid(sortid);

        Dog dog = dogService.getDog(id);
        if (dog != null) {
            video.setDog(dog);
        }

        BufferedImage bimg = ImageIO.read(file.getInputStream());
        video.setHeight((long)bimg.getHeight());
        video.setWidth((long)bimg.getWidth());

        return videoService.save(video);
    }

    @DeleteMapping("/api/video/delete/{id}")
    public void delete(@PathVariable Long id) {

        Video video = videoService.get(id);
        if (video.getDog() != null) {
            Dog dog = video.getDog();
            dog.getImages().remove(video);
            dogService.saveDog(dog);
        }

        videoService.deleteVideo(id);
    }

}
