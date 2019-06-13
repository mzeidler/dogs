package dogs.service;

import dogs.model.*;
import dogs.repo.ImageRepository;
import dogs.repo.StoryRepository;
import dogs.repo.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoryService {

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private VideoRepository videoRepository;

    public List<Story> findAll(StoryType type) {
        return storyRepository.findByType(type);
    }

    public void deleteStory(Long id) {
        storyRepository.deleteById(id);
    }

    public Story saveStory(Story story) {

        boolean isNew = story.getId() == null || story.getId() == 0;

        Story storyDB = isNew ? new Story() : getStory(story.getId());

        storyDB.setDe(story.getDe());
        storyDB.setHr(story.getHr());
        storyDB.setOpened(story.getOpened());
        storyDB.setTitle1de(story.getTitle1de());
        storyDB.setTitle2de(story.getTitle2de());
        storyDB.setTitle3de(story.getTitle3de());
        storyDB.setTitle1hr(story.getTitle1hr());
        storyDB.setTitle2hr(story.getTitle2hr());
        storyDB.setTitle3hr(story.getTitle3hr());
        storyDB.setType(story.getType());
        storyDB.setSortid(story.getSortid());
        storyRepository.saveAndFlush(storyDB);

        if (isNew) {

            for (Image image : story.getImages()) {
                Image imageDB = imageRepository.getOne(image.getId());
                imageDB.setSortid(image.getSortid());
                imageDB.setName(image.getName());
                imageDB.setDescription(image.getDescription());
                imageDB.setStory(storyDB);
                imageRepository.saveAndFlush(imageDB);
            }

            storyDB.setImages(story.getImages());

            for (Video video : story.getVideos()) {
                Video videoDB = videoRepository.getOne(video.getId());
                videoDB.setSortid(video.getSortid());
                videoDB.setName(video.getName());
                videoDB.setDescription(video.getDescription());
                videoDB.setStory(storyDB);
                videoRepository.saveAndFlush(videoDB);
            }

            storyDB.setVideos(story.getVideos());

        } else {

            for (Image imageDB : storyDB.getImages()) {
                for (Image i : story.getImages()) {
                    if (i.getId().equals(imageDB.getId())) {
                        imageDB.setSortid(i.getSortid());
                        imageDB.setName(i.getName());
                        imageDB.setDescription(i.getDescription());
                        imageRepository.saveAndFlush(imageDB);
                    }
                }
            }

            for (Video videoDB : storyDB.getVideos()) {
                for (Video v : story.getVideos()) {
                    if (v.getId().equals(videoDB.getId())) {
                        videoDB.setSortid(v.getSortid());
                        videoDB.setName(v.getName());
                        videoDB.setDescription(v.getDescription());
                        videoRepository.saveAndFlush(videoDB);
                    }
                }
            }

        }

        return storyDB;
    }


    public Story getStory(Long id) {
        Optional<Story> storyOpt = storyRepository.findById(id);
        return storyOpt.isPresent() ? storyOpt.get() : null;
    }
}
