package com.mayb.aifeature.service;

import com.mayb.aifeature.model.gemini.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Service
@Slf4j
public class GeminiSummaryService implements AISummaryService {
    private final WebClient webClient;

    private final String apiKey;
    private final String apiUrl;

    public GeminiSummaryService(WebClient.Builder webClientBuilder,
                                @Value("${gemini.api.key}") String apiKey,
                                @Value("${gemini.api.url}") String apiUrl) {
        this.webClient = webClientBuilder.baseUrl(apiUrl).build();

        this.apiKey = apiKey;
        this.apiUrl = apiUrl;
    }

    public Mono<String> summarizeText(String textToSummarize) {
        Part part = new Part(String.format("Summarize the following text in Korean in one or two sentences: %s", textToSummarize));
        Content content = new Content(Collections.singletonList(part), "user");
        GeminiRequest geminiRequest = new GeminiRequest(Collections.singletonList(content));

        return this.webClient.post()
                .uri((builder) -> builder.queryParam("key", apiKey).build())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(geminiRequest)
                .retrieve()
                .bodyToMono(GeminiResponse.class)
                .map(response -> {
                    if (response == null || response.getCandidates() == null || response.getCandidates().isEmpty()) {
                        return "요약에 실패했습니다. (response or candidates error)";
                    }

                    Candidate firstCandidate = response.getCandidates().get(0);
                    Content firstCandidateContent = firstCandidate.getContent();
                    if (firstCandidateContent == null || firstCandidateContent.getParts() == null || firstCandidateContent.getParts().isEmpty()) {
                        return "요약에 실패했습니다. (candidates index 0 or its parts error)";
                    }

                    return firstCandidateContent.getParts().get(0).getText();
                })
                .onErrorResume(error -> {
                    log.error("Gemini API call error", error);
                    return Mono.just(String.format("요약에 실패했습니다. (Gemini API error: %s)", error.getMessage()));
                });
    }
}
