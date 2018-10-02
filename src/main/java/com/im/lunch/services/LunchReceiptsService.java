package com.im.lunch.services;

import com.im.lunch.dao.LunchDAO;
import com.im.lunch.domain.Ingredient;
import com.im.lunch.domain.ReceiptsCollection;
import com.im.lunch.domain.Receipt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author imaltsev
 * @since 15/06/18
 * <p>
 * This class holds methods to return the valid lunch receipts for the day
 */
@Service
public class LunchReceiptsService {

    private static final Logger log = LoggerFactory.getLogger(LunchReceiptsService.class);

    @Autowired
    LunchDAO lunchDAO;

    /**
     * This method returns the valid lunch receipts for the day
     *
     * @param dayOfLunch     selected day of lunch
     * @return ReceiptsCollection - the list of valid receipt on dayOfLunch
     */
    public ReceiptsCollection getValidReceiptsForLunchOnDay(LocalDate dayOfLunch) {

        Optional<List<Ingredient>> ingredientsOpt = lunchDAO.getValidIngredientsForReceipt(dayOfLunch);
        Optional<List<Receipt>> receiptsOpt = lunchDAO.getLunchReceipts();
        List<Receipt> receipts;
        List<Ingredient> ingredients;

        if (ingredientsOpt.isPresent() && receiptsOpt.isPresent()) {
            ingredients = ingredientsOpt.get();
            receipts = receiptsOpt.get();
        } else {
            //Here Exception can be raised to notify customer about problem with empty collections.
            //The second case - the empty result may return as now with logging warning.
            log.warn("Ingredients or Receipt collection are empty.");
            return new ReceiptsCollection();
        }

        List<Receipt> resultRpt = receipts.stream().filter(p -> isIngredientsValid(p, ingredients, dayOfLunch))
                .sorted((r1, r2) -> Integer.compare(r2.getSort(), r1.getSort()))
                .collect(Collectors.toList());
        return new ReceiptsCollection(resultRpt);
    }


    private boolean isIngredientsValid(Receipt rpt, List<Ingredient> ingredients, LocalDate dayOfLunch) {

        for (String ing : rpt.getIngredients()) {
            Optional<Ingredient> ingResult =
                    ingredients.stream().filter(i -> ing.equals(i.getTitle())).findFirst();
            if (ingResult.isPresent()) {
                if (dayOfLunch.isAfter(ingResult.get().getBestBefore())
                        && dayOfLunch.isBefore(ingResult.get().getUseBy())) {
                    rpt.setSort(0);
                }
            } else {
                return false;
            }
        }
        return true;
    }

}
