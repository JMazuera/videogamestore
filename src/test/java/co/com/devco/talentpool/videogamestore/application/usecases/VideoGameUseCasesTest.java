package co.com.devco.talentpool.videogamestore.application.usecases;

import co.com.devco.talentpool.videogamestore.application.usecases.videogame.*;
import co.com.devco.talentpool.videogamestore.domain.exceptions.NoCopiesAvailableException;
import co.com.devco.talentpool.videogamestore.domain.models.VideoGame;
import co.com.devco.talentpool.videogamestore.domain.ports.out.IVideogameRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class VideoGameUseCasesTest {
    private IVideogameRepositoryPort mockVideogameRepositoryPort;
    private CreateVideoGameUseCaseImplementation createUseCase;
    private DeleteVideoGameUseCaseImplementation deleteUseCase;
    private PurchaseVideoGameUseCaseImplementation purchaseUseCase;
    private RetrieveVideoGameUseCaseImplementation retrieveUseCase;
    private UpdateVideogameUseCaseImplementation updateUseCase;

    @BeforeEach
    void setUp() {
        mockVideogameRepositoryPort = Mockito.mock(IVideogameRepositoryPort.class);
        createUseCase = new CreateVideoGameUseCaseImplementation(mockVideogameRepositoryPort);
        deleteUseCase = new DeleteVideoGameUseCaseImplementation(mockVideogameRepositoryPort);
        purchaseUseCase = new PurchaseVideoGameUseCaseImplementation(mockVideogameRepositoryPort);
        retrieveUseCase = new RetrieveVideoGameUseCaseImplementation(mockVideogameRepositoryPort);
        updateUseCase = new UpdateVideogameUseCaseImplementation(mockVideogameRepositoryPort);
    }

    @Test
    void testCreateVideoGame() {
        VideoGame videogame = new VideoGame(1L, "Solid Snake", "Xbox", 5);
        when(mockVideogameRepositoryPort.save(videogame)).thenReturn(videogame);

        VideoGame result = createUseCase.createVideoGame(videogame);

        assertThat(result)
                .isNotNull()
                .isEqualTo(videogame);

        verify(mockVideogameRepositoryPort).save(videogame);
    }

    @Test
    void testDeleteVideoGame() {
        Long id = 1L;
        when(mockVideogameRepositoryPort.deleteById(id)).thenReturn(true);

        boolean result = deleteUseCase.deleteVideoGame(id);

        assertThat(result).isTrue();
        verify(mockVideogameRepositoryPort).deleteById(id);
    }

    @Test
    void testPurchaseVideoGame_Success() throws Exception {
        Long id = 1L;
        VideoGame videoGame = new VideoGame(1L, "Solid Snake", "Xbox", 1);
        videoGame.setAvailableCopies(1);
        when(mockVideogameRepositoryPort.findById(id)).thenReturn(Optional.of(videoGame));
        when(mockVideogameRepositoryPort.save(videoGame)).thenReturn(videoGame);

        purchaseUseCase.purchaseVideoGame(id);

        assertThat(videoGame.getAvailableCopies()).isEqualTo(0);
        verify(mockVideogameRepositoryPort).findById(id);
        verify(mockVideogameRepositoryPort).save(videoGame);
    }

    @Test
    void testPurchaseVideoGame_NoCopiesAvailable() {
        Long id = 1L;
        VideoGame videoGame = new VideoGame(1L, "Solid Snake", "Xbox", 0);
        videoGame.setAvailableCopies(0);
        when(mockVideogameRepositoryPort.findById(id)).thenReturn(Optional.of(videoGame));

        assertThatThrownBy(() -> purchaseUseCase.purchaseVideoGame(id))
                .isInstanceOf(NoCopiesAvailableException.class)
                .hasMessage("There are no available copies.");

        verify(mockVideogameRepositoryPort).findById(id);
    }

    @Test
    void testRetrieveVideoGame() {
        Long id = 1L;
        VideoGame videoGame = new VideoGame(1L, "Solid Snake", "Xbox", 5);
        when(mockVideogameRepositoryPort.findById(id)).thenReturn(Optional.of(videoGame));

        Optional<VideoGame> result = retrieveUseCase.getVideoGame(id);

        assertThat(result)
                .isPresent()
                .contains(videoGame);

        verify(mockVideogameRepositoryPort).findById(id);
    }

    @Test
    void testGetAllVideoGames() {
        VideoGame videoGame = new VideoGame(1L, "Solid Snake", "Xbox", 5);
        List<VideoGame> videoGames = List.of(videoGame);
        when(mockVideogameRepositoryPort.findAll()).thenReturn(videoGames);

        List<VideoGame> result = retrieveUseCase.getAllVideoGames();

        assertThat(result)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .contains(videoGame);

        verify(mockVideogameRepositoryPort).findAll();
    }

    @Test
    void testUpdateVideoGame() {
        Long id = 1L;
        VideoGame existingVideoGame = new VideoGame(1L, "Solid Snake 1", "Xbox", 5);
        VideoGame updatedVideoGame = new VideoGame(1L, "Solid Snake 2", "Xbox One", 10);
        when(mockVideogameRepositoryPort.update(id, updatedVideoGame)).thenReturn(Optional.of(updatedVideoGame));

        Optional<VideoGame> result = updateUseCase.updateVideoGame(id, updatedVideoGame);

        assertThat(result)
                .isPresent()
                .contains(updatedVideoGame);

        verify(mockVideogameRepositoryPort).update(id, updatedVideoGame);
    }
}
