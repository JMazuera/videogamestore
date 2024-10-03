package co.com.devco.talentpool.videogamestore.infrastructure.controllers;

import co.com.devco.talentpool.videogamestore.application.services.VideoGameService;
import co.com.devco.talentpool.videogamestore.domain.exceptions.NoCopiesAvailableException;
import co.com.devco.talentpool.videogamestore.domain.models.VideoGame;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Create a new video game")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Video game created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VideoGame.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping
    public ResponseEntity<VideoGame> createVideoGame(@RequestBody VideoGame videoGame) {
        VideoGame createdVideoGame = videoGameService.createVideoGame(videoGame);
        return new ResponseEntity<>(createdVideoGame, HttpStatus.CREATED);
    }

    @Operation(summary = "Get a video game by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the video game",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VideoGame.class))),
            @ApiResponse(responseCode = "404", description = "Video game not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<VideoGame> getVideoGameById(@Parameter(description = "id of video game to be searched")
                                                      @PathVariable Long id) {
        return videoGameService.getVideoGame(id)
                .map(videoGame -> new ResponseEntity<>(videoGame, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Get all video games")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of video games",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VideoGame.class)))
    })
    @GetMapping
    public ResponseEntity<List<VideoGame>> getAllVideoGames() {
        List<VideoGame> videoGameList = videoGameService.getAllVideoGames();
        return new ResponseEntity<>(videoGameList, HttpStatus.OK);
    }

    @Operation(summary = "Update an existing video game")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Video game updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VideoGame.class))),
            @ApiResponse(responseCode = "404", description = "Video game not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<VideoGame> updateVideoGame(@Parameter(description = "id of video game to be updated")
                                                     @PathVariable Long id,
                                                     @RequestBody VideoGame updateVideoGame) {
        return videoGameService.updateVideoGame(id, updateVideoGame)
                .map(videoGame -> new ResponseEntity<>(videoGame, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Delete a video game by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Video game deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Video game not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideoGame(@Parameter(description = "id of video game to be deleted")
                                                @PathVariable Long id) {
        if (videoGameService.deleteVideoGame(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Purchase a video game by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Video game purchased successfully",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "No copies available", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error processing the purchase", content = @Content)
    })
    @PostMapping("/{id}/purchase")
    public ResponseEntity<String> purchaseVideoGame(@Parameter(description = "id of video game to be purchased")
                                                    @PathVariable Long id) {
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

