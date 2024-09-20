package co.com.devco.talentpool.videogamestore.application.usecases.videogame;

import co.com.devco.talentpool.videogamestore.domain.models.VideoGame;
import co.com.devco.talentpool.videogamestore.domain.ports.in.videogame.IUpdateVideoGameUseCase;
import co.com.devco.talentpool.videogamestore.domain.ports.out.IVideogameRepositoryPort;

import java.util.Optional;

public class UpdateVideogameUseCaseImplementation implements IUpdateVideoGameUseCase {

    private final IVideogameRepositoryPort iVideogameRepositoryPort;

    public UpdateVideogameUseCaseImplementation(IVideogameRepositoryPort iVideogameRepositoryPort) {
        this.iVideogameRepositoryPort = iVideogameRepositoryPort;
    }

    @Override
    public Optional<VideoGame> updateVideoGame(Long id, VideoGame updateVideoGame) {
        return iVideogameRepositoryPort.update(id, updateVideoGame);
    }
}
