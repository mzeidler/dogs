package dogs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String youtubeid;

    private String link;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "dogId")
    private Dog dog;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "storyId")
    private Story story;

    private Long sortid;

}
