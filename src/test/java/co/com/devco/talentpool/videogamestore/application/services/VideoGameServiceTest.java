package co.com.devco.talentpool.videogamestore.application.services;

import co.com.devco.talentpool.videogamestore.domain.models.VideoGame;
import co.com.devco.talentpool.videogamestore.domain.ports.in.videogame.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class VideoGameServiceTest {
    private VideoGameService videoGameService;
    private ICreateVideoGameUseCase mockCreateVideoGame;
    private IDeleteVideoGameUseCase mockDeleteVideoGame;
    private IRetrieveVideoGameUseCase mockRetrieveVideoGame;
    private IUpdateVideoGameUseCase mockUpdateVideoGame;
    private IPurchaseVideoGameUseCase mockPurchaseVideoGame;

    @BeforeEach
    void setUp() {
        mockCreateVideoGame = mock(ICreateVideoGameUseCase.class);
        mockDeleteVideoGame = mock(IDeleteVideoGameUseCase.class);
        mockRetrieveVideoGame = mock(IRetrieveVideoGameUseCase.class);
        mockUpdateVideoGame = mock(IUpdateVideoGameUseCase.class);
        mockPurchaseVideoGame = mock(IPurchaseVideoGameUseCase.class);
        videoGameService = new VideoGameService(mockCreateVideoGame, mockDeleteVideoGame, mockRetrieveVideoGame, mockUpdateVideoGame, mockPurchaseVideoGame);
    }

    @Test
    void testCreateVideoGame() {
        VideoGame videoGame = new VideoGame(1L, "The Legend of Zelda", "Nintendo", 5);
        when(mockCreateVideoGame.createVideoGame(videoGame)).thenReturn(videoGame);

        VideoGame result = videoGameService.createVideoGame(videoGame);

        assertThat(result).isEqualTo(videoGame);
        verify(mockCreateVideoGame).createVideoGame(videoGame);
    }

    @Test
    void testDeleteVideoGame() {
        Long videoGameId = 1L;
        when(mockDeleteVideoGame.deleteVideoGame(videoGameId)).thenReturn(true);

        boolean result = videoGameService.deleteVideoGame(videoGameId);

        assertThat(result).isTrue();
        verify(mockDeleteVideoGame).deleteVideoGame(videoGameId);
    }

    @Test
    void testGetVideoGame() {
        Long videoGameId = 1L;
        VideoGame videoGame = new VideoGame(1L, "The Legend of Zelda", "Nintendo", 5);
        when(mockRetrieveVideoGame.getVideoGame(videoGameId)).thenReturn(Optional.of(videoGame));

        Optional<VideoGame> result = videoGameService.getVideoGame(videoGameId);

        assertThat(result).isPresent().contains(videoGame);
        verify(mockRetrieveVideoGame).getVideoGame(videoGameId);
    }

    @Test
    void testGetAllVideoGames() {
        List<VideoGame> videoGames = List.of(new VideoGame(1L, "The Legend of Zelda", "Nintendo", 5));
        when(mockRetrieveVideoGame.getAllVideoGames()).thenReturn(videoGames);

        List<VideoGame> result = videoGameService.getAllVideoGames();

        assertThat(result).isEqualTo(videoGames);
        verify(mockRetrieveVideoGame).getAllVideoGames();
    }

    @Test
    void testUpdateVideoGame() {
        Long videoGameId = 1L;
        VideoGame updatedVideoGame = new VideoGame(1L, "Zelda: Ocarina of Time", "Nintendo", 4);
        when(mockUpdateVideoGame.updateVideoGame(videoGameId, updatedVideoGame)).thenReturn(Optional.of(updatedVideoGame));

        Optional<VideoGame> result = videoGameService.updateVideoGame(videoGameId, updatedVideoGame);

        assertThat(result).isPresent().contains(updatedVideoGame);
        verify(mockUpdateVideoGame).updateVideoGame(videoGameId, updatedVideoGame);
    }

    @Test
    void testUpdateVideoGame_NotFound() {
        Long videoGameId = 1L;
        VideoGame updatedVideoGame = new VideoGame(1L, "Zelda: Ocarina of Time", "Nintendo", 4);
        when(mockUpdateVideoGame.updateVideoGame(videoGameId, updatedVideoGame)).thenReturn(Optional.empty());

        Optional<VideoGame> result = videoGameService.updateVideoGame(videoGameId, updatedVideoGame);

        assertThat(result).isEmpty();
        verify(mockUpdateVideoGame).updateVideoGame(videoGameId, updatedVideoGame);
    }

    @Test
    void testPurchaseVideoGame() throws Exception {
        Long videoGameId = 1L;
        doNothing().when(mockPurchaseVideoGame).purchaseVideoGame(videoGameId);

        assertThatCode(() -> videoGameService.purchaseVideoGame(videoGameId)).doesNotThrowAnyException();
        verify(mockPurchaseVideoGame).purchaseVideoGame(videoGameId);
    }

    @Test
    void testPurchaseVideoGame_Exception() throws Exception {
        Long videoGameId = 1L;
        doThrow(new Exception("Not enough copies")).when(mockPurchaseVideoGame).purchaseVideoGame(videoGameId);

        Exception exception = assertThrows(Exception.class, () -> videoGameService.purchaseVideoGame(videoGameId));
        assertThat(exception).hasMessage("Not enough copies");
        verify(mockPurchaseVideoGame).purchaseVideoGame(videoGameId);
    }
}
