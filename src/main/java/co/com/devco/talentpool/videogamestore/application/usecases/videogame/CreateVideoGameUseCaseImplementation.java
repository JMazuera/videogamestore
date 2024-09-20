package co.com.devco.talentpool.videogamestore.application.usecases.videogame;

import co.com.devco.talentpool.videogamestore.domain.models.VideoGame;
import co.com.devco.talentpool.videogamestore.domain.ports.in.videogame.ICreateVideoGameUseCase;
import co.com.devco.talentpool.videogamestore.domain.ports.out.IVideogameRepositoryPort;

public class CreateVideoGameUseCaseImplementation implements ICreateVideoGameUseCase {

    private final IVideogameRepositoryPort iVideogameRepositoryPort;

    public CreateVideoGameUseCaseImplementation(IVideogameRepositoryPort iVideogameRepositoryPort) {
        this.iVideogameRepositoryPort = iVideogameRepositoryPort;
    }

    @Override
    public VideoGame createVideoGame(VideoGame videogame) {
        return iVideogameRepositoryPort.save(videogame);
    }
}
