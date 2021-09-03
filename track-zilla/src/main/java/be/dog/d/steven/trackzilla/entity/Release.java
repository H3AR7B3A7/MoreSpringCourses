package be.dog.d.steven.trackzilla.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Release {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String releaseDate;
    private String description;

    public Release() {
    }

    public Release(Integer id, String description, String releaseDate){
        this.id = id;
        this.releaseDate = releaseDate;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
