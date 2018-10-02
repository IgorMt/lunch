package com.im.lunch.services;

import com.im.lunch.dao.LunchDAO;
import com.im.lunch.domain.ReceiptsCollection;
import com.im.lunch.domain.Ingredient;
import com.im.lunch.domain.Receipt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Assert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LunchReceiptsServiceTest {

    @MockBean
    LunchDAO lunchDAO;

    @Autowired
    LunchReceiptsService lunchReceiptsService;


    List<Receipt> recC;
    List<Ingredient> icC;

    @Before
    public void setup() {

        Receipt rc = new Receipt();
        rc.setTitle("Omlette test");
        Set<String> ingr = new HashSet<>();
        ingr.add("Eggs");
        ingr.add("Milk");

        rc.setIngredients(ingr);

        Receipt rc1 = new Receipt();
        rc1.setTitle("Salad test");
        Set<String> ingr1 = new HashSet<>();
        ingr1.add("Lettuce");
        ingr1.add("Olive Oil");
        rc1.setIngredients(ingr1);

        recC = new ArrayList<>();
        recC.add(rc);
        recC.add(rc1);


        Ingredient in = new Ingredient();
        in.setTitle("Eggs");
        in.setUseBy(LocalDate.parse("2018-06-30"));
        in.setBestBefore(LocalDate.parse("2018-06-25"));

        Ingredient in1 = new Ingredient();
        in1.setTitle("Milk");
        in1.setUseBy(LocalDate.parse("2018-06-30"));
        in1.setBestBefore(LocalDate.parse("2018-06-25"));

        Ingredient in2 = new Ingredient();
        in2.setTitle("Lettuce");
        in2.setUseBy(LocalDate.parse("2018-06-20"));
        in2.setBestBefore(LocalDate.parse("2018-06-15"));

        Ingredient in3 = new Ingredient();
        in3.setTitle("Olive Oil");
        in3.setUseBy(LocalDate.parse("2018-06-20"));
        in3.setBestBefore(LocalDate.parse("2018-06-15"));

        icC = new ArrayList<>();
        icC.add(in);
        icC.add(in1);
        icC.add(in2);
        icC.add(in3);
    }

    @Test
    public void testGetValidRecipesForTheDay() {

        given(lunchDAO.getLunchReceipts()).willReturn(Optional.of(recC));
        given(lunchDAO.getValidIngredientsForReceipt(LocalDate.parse("2018-06-17")))
                .willReturn(Optional.of(icC));

        ReceiptsCollection rc = lunchReceiptsService
                .getValidReceiptsForLunchOnDay(LocalDate.parse("2018-06-17"));

        verify(lunchDAO, times(1)).getLunchReceipts();
        verify(lunchDAO, times(1))
                .getValidIngredientsForReceipt(LocalDate.parse("2018-06-17"));
        Assert.assertTrue("Result of Receipts collection size > 0",
                rc.getRecipes().size() == 2);
        Assert.assertTrue("Check sort, salad must be last in collection",
                "Salad test".equals(rc.getRecipes().get(1).getTitle()));
    }

}
