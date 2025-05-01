package fit5120.monash.fit5120_backend.service;

import fit5120.monash.fit5120_backend.model.News;
import fit5120.monash.fit5120_backend.repository.NewsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {
    private NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<News> getAllNews() {
        return newsRepository.findAllByOrderByPublishedAtDesc ();
    }
}
