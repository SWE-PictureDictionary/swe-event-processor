package com.swe.project.eventprocessor.client;

import com.swe.project.eventprocessor.model.AggregateProgressResponse;
import com.swe.project.eventprocessor.model.TopicCompletedAggregateRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProgressClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${progress.access.url:http://progress-access:8080}")
    private String progressAccessUrl;

    public AggregateProgressResponse recordTopicCompleted(TopicCompletedAggregateRequest request) {
        String url = progressAccessUrl + "/progress/aggregate/topic-completed";

        ResponseEntity<AggregateProgressResponse> response = restTemplate.postForEntity(
                url,
                request,
                AggregateProgressResponse.class
        );

        return response.getBody();
    }
}