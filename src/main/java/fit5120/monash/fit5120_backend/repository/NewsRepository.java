package fit5120.monash.fit5120_backend.repository;

import fit5120.monash.fit5120_backend.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Integer> {
    List<News> findAllByOrderByPublishedAtDesc();
}
