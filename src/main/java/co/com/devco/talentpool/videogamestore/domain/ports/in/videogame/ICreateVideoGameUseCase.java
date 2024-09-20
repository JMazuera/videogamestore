package co.com.devco.talentpool.videogamestore.domain.ports.in.videogame;

import co.com.devco.talentpool.videogamestore.domain.models.VideoGame;

public interface ICreateVideoGameUseCase {
    VideoGame createVideoGame(VideoGame videogame);
}
