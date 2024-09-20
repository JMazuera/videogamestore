package co.com.devco.talentpool.videogamestore.domain.ports.in.videogame;

import co.com.devco.talentpool.videogamestore.domain.models.VideoGame;

import java.util.Optional;

public interface IUpdateVideoGameUseCase {
    Optional<VideoGame> updateVideoGame(Long id, VideoGame updateVideoGame);
}
