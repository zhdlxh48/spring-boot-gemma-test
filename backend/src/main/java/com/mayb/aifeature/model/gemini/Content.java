package com.mayb.aifeature.model.gemini;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Content {
    private List<Part> parts;
    private String role; // user, model

    public Content(List<Part> parts) {
        this.parts = parts;
    }
}
