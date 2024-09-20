package co.com.devco.talentpool.videogamestore.application.usecases.videogame;

import co.com.devco.talentpool.videogamestore.domain.exceptions.NoCopiesAvailableException;
import co.com.devco.talentpool.videogamestore.domain.models.VideoGame;
import co.com.devco.talentpool.videogamestore.domain.ports.in.videogame.IPurchaseVideoGameUseCase;
import co.com.devco.talentpool.videogamestore.domain.ports.out.IVideogameRepositoryPort;

public class PurchaseVideoGameUseCaseImplementation implements IPurchaseVideoGameUseCase {

    private final IVideogameRepositoryPort iVideogameRepositoryPort;

    public PurchaseVideoGameUseCaseImplementation(IVideogameRepositoryPort iVideogameRepositoryPort) {
        this.iVideogameRepositoryPort = iVideogameRepositoryPort;
    }

    @Override
    public void purchaseVideoGame(Long id) throws Exception {
        VideoGame videoGame = iVideogameRepositoryPort.findById(id)
                .orElseThrow(() -> new Exception("VideoGame not Found."));

        if (videoGame.getAvailableCopies() <= 0) {
            throw new NoCopiesAvailableException("There are no available copies.");
        }

        videoGame.setAvailableCopies(videoGame.getAvailableCopies() - 1);

        iVideogameRepositoryPort.save(videoGame);
    }
}
