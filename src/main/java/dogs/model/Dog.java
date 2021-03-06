package dogs.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Size size;

    private BigDecimal weight;

    private String born;

    private Boolean nutered;

    private Boolean vaccinated;

    private Boolean antiparasite;

    private String description;

    @OneToMany(mappedBy = "dog", cascade = CascadeType.ALL)
    private List<Image> images;

    @OneToMany(mappedBy = "dog", cascade = CascadeType.ALL)
    private List<Video> videos;

    @Lob
    private String titleimage;

    @Lob
    private String de;

    @Lob
    private String en;

    @Lob
    private String hr;

}
