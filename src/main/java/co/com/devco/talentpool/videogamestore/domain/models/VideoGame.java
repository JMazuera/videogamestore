package co.com.devco.talentpool.videogamestore.domain.models;

public class VideoGame {
    private Long id;
    private String videoGameName;
    private String videoGameConsole;
    private Integer availableCopies;

    public VideoGame(Long id, String videoGameName, String videoGameConsole, Integer availableCopies) {
        this.id = id;
        this.videoGameName = videoGameName;
        this.videoGameConsole = videoGameConsole;
        this.availableCopies = availableCopies;
    }

    public Long getId() {
        return id;
    }

    public String getVideoGameConsole() {
        return videoGameConsole;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public String getVideoGameName() {
        return videoGameName;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }
}
