package com.im.lunch.domain;

import java.util.List;

public class ReceiptsCollection {

    public ReceiptsCollection() {
    }

    public ReceiptsCollection(List<Receipt> recipes) {
        this.recipes = recipes;
    }

    private List<Receipt> recipes;

    public List<Receipt> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Receipt> recipes) {
        this.recipes = recipes;
    }
}
