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
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long size;

    private Long height;

    private Long width;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "dogId")
    private Dog dog;

    @JsonIgnore
    @Lob
    private byte[] image;

    private Long sortid;

}
