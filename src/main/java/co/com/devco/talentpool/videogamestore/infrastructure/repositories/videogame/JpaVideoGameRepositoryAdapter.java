package co.com.devco.talentpool.videogamestore.infrastructure.repositories.videogame;

import co.com.devco.talentpool.videogamestore.domain.models.VideoGame;
import co.com.devco.talentpool.videogamestore.domain.ports.out.IVideogameRepositoryPort;
import co.com.devco.talentpool.videogamestore.infrastructure.entities.VideoGameEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JpaVideoGameRepositoryAdapter implements IVideogameRepositoryPort {

    private final IJpaVideoGameRepository iJpaVideoGameRepository;

    public JpaVideoGameRepositoryAdapter(IJpaVideoGameRepository iJpaVideoGameRepository) {
        this.iJpaVideoGameRepository = iJpaVideoGameRepository;
    }

    @Override
    public VideoGame save(VideoGame videogame) {
        VideoGameEntity videoGameEntity = VideoGameEntity.fromDomainModel(videogame);
        VideoGameEntity savedVideoGameEntity = iJpaVideoGameRepository.save(videoGameEntity);
        return savedVideoGameEntity.toDomainModel();
    }

    @Override
    public Optional<VideoGame> findById(Long id) {
        return iJpaVideoGameRepository.findById(id).map(VideoGameEntity::toDomainModel);
    }

    @Override
    public List<VideoGame> findAll() {
        return iJpaVideoGameRepository.findAll().stream().map(VideoGameEntity::toDomainModel).toList();
    }

    @Override
    public Optional<VideoGame> update(Long id, VideoGame videoGame) {
        if(iJpaVideoGameRepository.existsById(videoGame.getId())){
            VideoGameEntity videoGameEntity = VideoGameEntity.fromDomainModel(videoGame);
            VideoGameEntity updateVideGameEntity = iJpaVideoGameRepository.save(videoGameEntity);
            return Optional.of(updateVideGameEntity.toDomainModel());
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        if (iJpaVideoGameRepository.existsById(id)) {
            iJpaVideoGameRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
