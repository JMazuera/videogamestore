package co.com.devco.talentpool.videogamestore.domain.ports.out;

import co.com.devco.talentpool.videogamestore.domain.models.VideoGame;
import java.util.List;
import java.util.Optional;

public interface IVideogameRepositoryPort {

    VideoGame save(VideoGame videogame);
    Optional<VideoGame> findById(Long id);
    List<VideoGame> findAll();
    Optional<VideoGame> update(Long id, VideoGame updateVideoGame);
    boolean deleteById(Long id);
}
