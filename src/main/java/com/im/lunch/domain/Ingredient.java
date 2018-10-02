package com.im.lunch.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import java.time.LocalDate;

public class Ingredient {

    private String title;

    @JsonProperty("best-before")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate bestBefore;

    @JsonProperty("use-by")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate useBy;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getBestBefore() {
        return bestBefore;
    }

    public void setBestBefore(LocalDate bestBefore) {
        this.bestBefore = bestBefore;
    }

    public LocalDate getUseBy() {
        return useBy;
    }

    public void setUseBy(LocalDate useBy) {
        this.useBy = useBy;
    }
}
