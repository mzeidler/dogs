package dogs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private StoryType type;

    private String title1hr;

    private String title2hr;

    private String title3hr;

    private String title1de;

    private String title2de;

    private String title3de;

    private Long sortid;

    private Boolean opened;

    @Lob
    private String de;

    @Lob
    private String hr;

    @OneToMany(mappedBy = "story", cascade = CascadeType.ALL)
    private List<Image> images;

    @OneToMany(mappedBy = "story", cascade = CascadeType.ALL)
    private List<Video> videos;

}
