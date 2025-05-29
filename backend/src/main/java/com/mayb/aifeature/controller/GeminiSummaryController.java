package com.mayb.aifeature.controller;

import com.mayb.aifeature.dto.SummaryRequest;
import com.mayb.aifeature.dto.SummaryResponse;
import com.mayb.aifeature.service.GeminiSummaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/gemini")
@CrossOrigin(origins = "http://localhost:8081") // for development env (consider vue's Vue CLI Proxy setting)
@Slf4j
public class GeminiSummaryController {
    private final GeminiSummaryService geminiSummaryService;

    @Autowired
    public GeminiSummaryController(GeminiSummaryService geminiSummaryService) {
        this.geminiSummaryService = geminiSummaryService;
    }

    @PostMapping("/summarize")
    public Mono<ResponseEntity<SummaryResponse>> summarize(@RequestBody SummaryRequest request) {
        String rawText = request.getText();

        if (rawText == null || rawText.trim().isEmpty()) {
            return Mono.just(ResponseEntity.badRequest().body(new SummaryResponse(null, "요악할 텍스트를 입력해주세요.")));
        }

        return geminiSummaryService.summarizeText(request.getText())
                .map(summary -> ResponseEntity.ok(new SummaryResponse(summary)))
                .onErrorResume(error -> {
                    log.error("GeminiSummaryController error", error);
                    return Mono.just(ResponseEntity.internalServerError().body(new SummaryResponse(null, "요약 처리 중 서버 오류가 발생했습니다.")));
                });
    }
}
