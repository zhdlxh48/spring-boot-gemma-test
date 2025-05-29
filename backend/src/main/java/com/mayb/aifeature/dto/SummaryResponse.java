package com.mayb.aifeature.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SummaryResponse {
    private String summary;
    private String error;

    public SummaryResponse(String summary) {
        this.summary = summary;
    }
}
