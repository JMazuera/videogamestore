package co.com.devco.talentpool.videogamestore.application.usecases.videogame;

import co.com.devco.talentpool.videogamestore.domain.ports.in.videogame.IDeleteVideoGameUseCase;
import co.com.devco.talentpool.videogamestore.domain.ports.out.IVideogameRepositoryPort;

public class DeleteVideoGameUseCaseImplementation implements IDeleteVideoGameUseCase {

    private final IVideogameRepositoryPort iVideogameRepositoryPort;

    public DeleteVideoGameUseCaseImplementation(IVideogameRepositoryPort iVideogameRepositoryPort) {
        this.iVideogameRepositoryPort = iVideogameRepositoryPort;
    }

    @Override
    public boolean deleteVideoGame(Long id) {
        return iVideogameRepositoryPort.deleteById(id);
    }
}
