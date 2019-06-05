package dogs.service;

import dogs.model.Image;
import dogs.model.Video;
import dogs.repo.ImageRepository;
import dogs.repo.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class VideoService {

    private Pattern[] patterns = new Pattern[] {
            Pattern.compile("youtu.be/(\\S*)"),
            Pattern.compile("www.youtube.com/embed/(\\S*)"),
            Pattern.compile("www.youtube.com/watch\\?v=(\\S*)")
    };

    @Autowired
    private VideoRepository videoRepository;

    public Video save(Video video) {
        video.setYoutubeid(parseYoutubeId(video.getLink()));
        return videoRepository.saveAndFlush(video);
    }

    public Video get(Long id) {
        Optional<Video> videoOpt = videoRepository.findById(id);
        return videoOpt.isPresent() ? videoOpt.get() : null;
    }

    public void deleteVideo(Long id) {
        videoRepository.deleteById(id);
    }

    private String parseYoutubeId(String link) {
        if (link != null) {
            for (Pattern p : patterns) {
                Matcher m = p.matcher(link);
                while(m.find()) {
                    if (m.groupCount() > 0) {
                        return m.group(1).replace("\"", "");
                    }
                }
            }
        }
        return null;
    }
}
