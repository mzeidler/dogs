package dogs.service;

import dogs.model.Image;
import dogs.model.Video;
import dogs.repo.ImageRepository;
import dogs.repo.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    public Video save(Video video) {
        return videoRepository.saveAndFlush(video);
    }

    public Video get(Long id) {
        Optional<Video> videoOpt = videoRepository.findById(id);
        return videoOpt.isPresent() ? videoOpt.get() : null;
    }

    public void deleteVideo(Long id) {
        videoRepository.deleteById(id);
    }
}
