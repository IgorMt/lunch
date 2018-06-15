package com.rezdy.lunch.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

public class Receipt {

    private String title;
    private Set<String> ingredients;

    @JsonIgnore
    private Integer sort = 1;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
