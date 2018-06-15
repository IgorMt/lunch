package com.rezdy.lunch.dao.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rezdy.lunch.dao.LunchDAO;
import com.rezdy.lunch.domain.Ingredient;
import com.rezdy.lunch.domain.IngredientsCollection;
import com.rezdy.lunch.domain.Receipt;
import com.rezdy.lunch.domain.ReceiptsCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author imaltsev
 * @since 15/06/18
 * <p>
 * The repository LunchDAO implementation to get
 * a required collections from *.json files.
 * File names takes from application.properties
 */
@Repository("lunchDAO")
public class LunchDAOImpl implements LunchDAO {

    private static final Logger log = LoggerFactory.getLogger(LunchDAOImpl.class);

    private final ObjectMapper mapper = new ObjectMapper();

    @Value("${receipts.file.name}")
    private String receiptsFileName;

    @Value("${ingredients.file.name}")
    private String ingredientsFileName;

    /**
     * This method returns he list of all possible receipts
     * @return Optional<List<Receipt>> - the list of all possible receipts
     */
    @Override
    public Optional<List<Receipt>> getLunchReceipts() {

        try {
            return Optional.of(mapper.readValue(readFile(receiptsFileName)
                    , ReceiptsCollection.class).getRecipes());
        } catch (IOException ie) {
            log.error("JSON Deserialization error with ReceiptsCollection.", ie);
        }
        return Optional.empty();
    }

    /**
     * This method returns  the list of all ingredients
     * where dayOfLunch is before use-by date.
     *
     * @param dayOfLunch     selected day of lunch
     * @return Optional<List<Ingredient>> - the list of ingredients
     * where dayOfLunch is before use-by date
     */
    @Override
    public Optional<List<Ingredient>> getValidIngredientsForReceipt(LocalDate dayOfLunch) {

        try {
            return Optional.of(mapper.readValue(readFile(ingredientsFileName)
                    , IngredientsCollection.class).getIngredients().stream().filter(p ->
                    dayOfLunch.isBefore(p.getUseBy())).collect(Collectors.toList()));
        } catch (IOException ie) {
            log.error("JSON Deserialization error with IngredientsCollection.", ie);
        }
        return Optional.empty();
    }

    private InputStreamReader readFile(String fileName) {
        return new InputStreamReader(getClass().getClassLoader().getResourceAsStream(fileName));
    }

}

