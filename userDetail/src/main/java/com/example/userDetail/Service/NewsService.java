package com.example.userDetail.Service;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class NewsService {
    private final RestClient restClient;

    @Value("${newsdata.key}")
    private String newsApiKey;

    public NewsService(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder
                .baseUrl("https://newsdata.io/api/1")
                .build();
    }
@Tool
    public NewsResponse getTopNewsForLocation(String location) {
        return restClient.get()
                .uri("/news?apikey={apiKey}&q={location}&language=en&size=3",
                        newsApiKey, location)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(NewsResponse.class);
    }

    // NewsResponse class remains the same as in the previous implementation
    public static class NewsResponse {
        private String status;
        private List<Article> results;

        // Getters and setters (same as previous implementation)
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public List<Article> getResults() { return results; }
        public void setResults(List<Article> results) { this.results = results; }

        public static class Article {
            private String title;
            private String description;
            private String link;
            private String pubDate;

            // Getters and setters (same as previous implementation)
            public String getTitle() { return title; }
            public void setTitle(String title) { this.title = title; }
            public String getDescription() { return description; }
            public void setDescription(String description) { this.description = description; }
            public String getLink() { return link; }
            public void setLink(String link) { this.link = link; }
            public String getPubDate() { return pubDate; }
            public void setPubDate(String pubDate) { this.pubDate = pubDate; }
        }
    }
}