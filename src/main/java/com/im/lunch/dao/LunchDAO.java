package com.im.lunch.dao;

import com.im.lunch.domain.Ingredient;
import com.im.lunch.domain.Receipt;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LunchDAO {

    Optional<List<Receipt>> getLunchReceipts();

    Optional<List<Ingredient>> getValidIngredientsForReceipt(LocalDate dayOfLunch);
}
