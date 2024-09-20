package co.com.devco.talentpool.videogamestore.application.usecases.videogame;

import co.com.devco.talentpool.videogamestore.domain.models.VideoGame;
import co.com.devco.talentpool.videogamestore.domain.ports.in.videogame.IRetrieveVideoGameUseCase;
import co.com.devco.talentpool.videogamestore.domain.ports.out.IVideogameRepositoryPort;

import java.util.List;
import java.util.Optional;

public class RetrieveVideoGameUseCaseImplementation implements IRetrieveVideoGameUseCase {

    private final IVideogameRepositoryPort iVideogameRepositoryPort;

    public RetrieveVideoGameUseCaseImplementation(IVideogameRepositoryPort iVideogameRepositoryPort) {
        this.iVideogameRepositoryPort = iVideogameRepositoryPort;
    }

    @Override
    public Optional<VideoGame> getVideoGame(Long id) {
        return iVideogameRepositoryPort.findById(id);
    }

    @Override
    public List<VideoGame> getAllVideoGames() {
        return iVideogameRepositoryPort.findAll();
    }
}
