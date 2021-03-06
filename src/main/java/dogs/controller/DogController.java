package dogs.controller;

import dogs.model.Dog;
import dogs.model.Message;
import dogs.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class DogController {

    @Autowired
    private DogService dogService;

    @GetMapping("/api/dog/{id}")
    public Dog findById(@PathVariable Long id) {
        return dogService.getDog(id);
    }

    @GetMapping("/api/dogs")
    public List<Dog> findAll() {
        return dogService.findAll();
    }

    @PostMapping("/api/dog/save")
    public Dog saveDog(@RequestBody Dog dog) {
        return dogService.saveDog(dog);
    }

    @DeleteMapping("/api/dog/delete/{id}")
    public void delete(@PathVariable Long id) {
        dogService.deleteDog(id);
    }


}
