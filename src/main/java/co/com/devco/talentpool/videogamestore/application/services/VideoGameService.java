package co.com.devco.talentpool.videogamestore.application.services;

import co.com.devco.talentpool.videogamestore.domain.models.VideoGame;
import co.com.devco.talentpool.videogamestore.domain.ports.in.videogame.*;
import java.util.List;
import java.util.Optional;

public class VideoGameService implements ICreateVideoGameUseCase, IDeleteVideoGameUseCase, IRetrieveVideoGameUseCase, IUpdateVideoGameUseCase, IPurchaseVideoGameUseCase {

    private final ICreateVideoGameUseCase iCreateVideogameUseCase;
    private final IDeleteVideoGameUseCase iDeleteVideogameUseCase;
    private final IRetrieveVideoGameUseCase iRetrieveVideogameUseCase;
    private final IUpdateVideoGameUseCase iUpdateVideogameUseCase;
    private final IPurchaseVideoGameUseCase iPurchaseVideoGameUseCase;

    public VideoGameService(ICreateVideoGameUseCase createVideoGameUseCase, IDeleteVideoGameUseCase deleteVideoGameUseCase, IRetrieveVideoGameUseCase retrieveVideoGameUseCase, IUpdateVideoGameUseCase updateVideoGameUseCase, IPurchaseVideoGameUseCase purchaseVideoGameUseCase) {
        this.iCreateVideogameUseCase = createVideoGameUseCase;
        this.iDeleteVideogameUseCase = deleteVideoGameUseCase;
        this.iRetrieveVideogameUseCase = retrieveVideoGameUseCase;
        this.iUpdateVideogameUseCase = updateVideoGameUseCase;
        this.iPurchaseVideoGameUseCase = purchaseVideoGameUseCase;
    }

    @Override
    public VideoGame createVideoGame(VideoGame videogame) {
        return iCreateVideogameUseCase.createVideoGame(videogame);
    }

    @Override
    public boolean deleteVideoGame(Long id) {
        return iDeleteVideogameUseCase.deleteVideoGame(id);
    }

    @Override
    public Optional<VideoGame> getVideoGame(Long id) {
        return iRetrieveVideogameUseCase.getVideoGame(id);
    }

    @Override
    public List<VideoGame> getAllVideoGames() {
        return iRetrieveVideogameUseCase.getAllVideoGames();
    }

    @Override
    public Optional<VideoGame> updateVideoGame(Long id, VideoGame updateVideoGame) {
        return iUpdateVideogameUseCase.updateVideoGame(id, updateVideoGame);
    }

    @Override
    public void purchaseVideoGame(Long id) throws Exception {
        iPurchaseVideoGameUseCase.purchaseVideoGame(id);
    }
}
