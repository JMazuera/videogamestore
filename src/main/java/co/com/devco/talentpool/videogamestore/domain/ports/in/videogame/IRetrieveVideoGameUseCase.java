package co.com.devco.talentpool.videogamestore.domain.ports.in.videogame;

import co.com.devco.talentpool.videogamestore.domain.models.VideoGame;

import java.util.List;
import java.util.Optional;

public interface IRetrieveVideoGameUseCase {
    Optional<VideoGame> getVideoGame(Long id);
    List<VideoGame> getAllVideoGames();
}
