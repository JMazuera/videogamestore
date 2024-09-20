package co.com.devco.talentpool.videogamestore.infrastructure.repositories.videogame;

import co.com.devco.talentpool.videogamestore.infrastructure.entities.VideoGameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IJpaVideoGameRepository extends JpaRepository<VideoGameEntity, Long> {
}
