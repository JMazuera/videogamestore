package co.com.devco.talentpool.videogamestore.infrastructure.entities;

import co.com.devco.talentpool.videogamestore.domain.models.VideoGame;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class VideoGameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String videoGameName;
    private String videoGameConsole;
    private Integer availableCopies;

    public VideoGameEntity(Long id, String videoGameName, String videoGameConsole, Integer availableCopies) {
        this.id = id;
        this.videoGameName = videoGameName;
        this.videoGameConsole = videoGameConsole;
        this.availableCopies = availableCopies;
    }

    public static VideoGameEntity fromDomainModel(VideoGame videoGame){
        return new VideoGameEntity(videoGame.getId(), videoGame.getVideoGameName(), videoGame.getVideoGameConsole(), videoGame.getAvailableCopies());
    }

    public VideoGame toDomainModel(){
        return new VideoGame(id, videoGameName, videoGameConsole, availableCopies);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVideoGameName() {
        return videoGameName;
    }

    public void setVideoGameName(String videoGameName) {
        this.videoGameName = videoGameName;
    }

    public String getVideoGameConsole() {
        return videoGameConsole;
    }

    public void setVideoGameConsole(String videoGameConsole) {
        this.videoGameConsole = videoGameConsole;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }
}
