package dogs.controller;

import dogs.model.Dog;
import dogs.model.Story;
import dogs.model.StoryType;
import dogs.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class StoryController {

    @Autowired
    private StoryService storyService;

    @GetMapping("/api/story/{id}")
    public Story findById(@PathVariable Long id) {
        return storyService.getStory(id);
    }

    @GetMapping("/api/story/type/{type}")
    public List<Story> findAll(@PathVariable StoryType type) {
        return storyService.findAll(type);
    }

    @PostMapping("/api/story/save")
    public Story saveStory(@RequestBody Story story) {
        return storyService.saveStory(story);
    }

    @DeleteMapping("/api/story/delete/{id}")
    public void delete(@PathVariable Long id) {
        storyService.deleteStory(id);
    }
}
