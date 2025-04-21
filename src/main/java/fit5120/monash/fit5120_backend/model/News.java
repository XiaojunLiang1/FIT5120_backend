package fit5120.monash.fit5120_backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "news")
public class News {

    @Id
    private Integer id;

    private String title;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "news_url")
    private String newsUrl;

    @Column(name = "published_at")
    private LocalDateTime publishedAt;

    @Column(name = "description")
    private String description;

    // Getters
    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public String getDescription() {
        return description;
    }
}
