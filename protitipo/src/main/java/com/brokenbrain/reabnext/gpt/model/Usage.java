package com.brokenbrain.reabnext.gpt.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Usage {

    private Long promptTokens;

    private Long completionTokens;

    private Long totalTokens;


    public Usage(Long promptTokens, Long completionTokens, Long totalTokens) {
        this.setPromptTokens(promptTokens);
        this.setCompletionTokens(completionTokens);
        this.setTotalTokens(totalTokens);
    }

    public Usage() {
    }


    public Long getPromptTokens() {
        return promptTokens;
    }

    public Usage setPromptTokens(Long promptTokens) {
        this.promptTokens = promptTokens;
        return this;
    }

    public Long getCompletionTokens() {
        return completionTokens;
    }

    public Usage setCompletionTokens(Long completionTokens) {
        this.completionTokens = completionTokens;
        return this;
    }

    public Long getTotalTokens() {
        return totalTokens;
    }

    public Usage setTotalTokens(Long totalTokens) {
        this.totalTokens = totalTokens;
        return this;
    }

    @Override
    public String toString() {
        return "Usage{" +
                "promptTokens=" + promptTokens +
                ", completionTokens=" + completionTokens +
                ", totalTokens=" + totalTokens +
                '}';
    }
}
