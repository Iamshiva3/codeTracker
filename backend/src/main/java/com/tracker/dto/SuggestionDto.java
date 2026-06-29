package com.tracker.dto;

public class SuggestionDto {
    private String suggestionText;

    public SuggestionDto(String suggestionText) {
        this.suggestionText = suggestionText;
    }

    public String getSuggestionText() { return suggestionText; }
    public void setSuggestionText(String suggestionText) { this.suggestionText = suggestionText; }
}
