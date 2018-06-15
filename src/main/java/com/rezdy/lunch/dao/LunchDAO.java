package com.rezdy.lunch.dao;

import com.rezdy.lunch.domain.Ingredient;
import com.rezdy.lunch.domain.Receipt;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LunchDAO {

    Optional<List<Receipt>> getLunchReceipts();

    Optional<List<Ingredient>> getValidIngredientsForReceipt(LocalDate dayOfLunch);
}
