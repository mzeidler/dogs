package dogs.controller;

import dogs.model.Dog;
import dogs.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class DogController {

    @Autowired
    private DogService dogService;

    @GetMapping("/api/dog/{id}")
    public Dog findById(@PathVariable Long id) {
        return dogService.getDog(id);
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
