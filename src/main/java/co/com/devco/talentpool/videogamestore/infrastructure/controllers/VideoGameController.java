package co.com.devco.talentpool.videogamestore.infrastructure.controllers;

import co.com.devco.talentpool.videogamestore.application.services.VideoGameService;
import co.com.devco.talentpool.videogamestore.domain.exceptions.NoCopiesAvailableException;
import co.com.devco.talentpool.videogamestore.domain.models.VideoGame;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videogames")
public class VideoGameController {

    private final VideoGameService videoGameService;

    public VideoGameController(VideoGameService videoGameService) {
        this.videoGameService = videoGameService;
    }

    @PostMapping
    public ResponseEntity<VideoGame> createVideoGame(@RequestBody VideoGame videoGame){
        VideoGame createdVideoGame = videoGameService.createVideoGame(videoGame);
        return new ResponseEntity<>(createdVideoGame, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoGame> getVideoGameById(@PathVariable Long id){
        return videoGameService.getVideoGame(id)
                .map(videoGame -> new ResponseEntity<>(videoGame, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<VideoGame>> getAllVideoGames(){
        List<VideoGame> videoGameList = videoGameService.getAllVideoGames();
        return new ResponseEntity<>(videoGameList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideoGame> updateVideoGame(@PathVariable Long id, @RequestBody VideoGame updateVideoGame){
        return videoGameService.updateVideoGame(id, updateVideoGame)
                .map(videoGame -> new ResponseEntity<>(videoGame, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideoGame(@PathVariable Long id){
        if (videoGameService.deleteVideoGame(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{id}/purchase")
    public ResponseEntity<String> purchaseVideoGame(@PathVariable Long id) {
        try {
            videoGameService.purchaseVideoGame(id);
            return new ResponseEntity<>("Video game purchased successfully!", HttpStatus.OK);
        } catch (NoCopiesAvailableException e) {
            return new ResponseEntity<>("No copies available for this video game.", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while processing the purchase.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
