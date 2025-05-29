package com.mayb.aifeature.service;

import reactor.core.publisher.Mono;

public interface AISummaryService {
    Mono<String> summarizeText(String textToSummarize);
}
