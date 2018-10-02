package com.im.lunch.dao;

import com.im.lunch.domain.Ingredient;
import com.im.lunch.domain.Receipt;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author imaltsev
 * @since 15/06/18
 * <p>
 *   LunchDAO integration test class
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LunchDAOTest {

    @Autowired
    LunchDAO lunchDAO;

    @Test
    public void testGetRecipes() {

        Optional<List<Receipt>> rc =  lunchDAO.getLunchReceipts();

        Assert.assertTrue("Receipts collection is present.", rc.isPresent());
        Assert.assertTrue("Receipts collection size > 0",  rc.get().size() > 0);
        Receipt receipt = rc.get().stream().findAny()
                .orElse(null);
        Assert.assertFalse(StringUtils.isEmpty(receipt.getTitle()));
        Assert.assertTrue(receipt.getIngredients().size() > 0);
    }

    @Test
    public void testGetIngredients() {
        Optional<List<Ingredient>> ic = lunchDAO.getValidIngredientsForReceipt(LocalDate.now());
        Assert.assertTrue("Ingredients collection is present.", ic.isPresent());
        Assert.assertTrue("Ingredients collection size > 0",  ic.get().size() > 0);
        Ingredient ingredient = ic.get().stream().findAny()
                .orElse(null);
        Assert.assertFalse(StringUtils.isEmpty(ingredient.getTitle()));
        Assert.assertTrue(ingredient.getUseBy() != null);
        Assert.assertTrue(ingredient.getBestBefore() != null);
    }
}
