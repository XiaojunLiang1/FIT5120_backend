package fit5120.monash.fit5120_backend.controller;

import fit5120.monash.fit5120_backend.model.News;
import fit5120.monash.fit5120_backend.service.NewsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    private NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public List<News> getAllNewsSorted() {
        return newsService.getAllNews();
    }
}
