package com.im.lunch.controllers;

import com.im.lunch.domain.Receipt;
import com.im.lunch.domain.ReceiptsCollection;
import com.im.lunch.services.LunchReceiptsService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(LunchReceiptsController.class)
public class LunchReceiptsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LunchReceiptsService service;

    ReceiptsCollection rcC;

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

        List<Receipt> recC = new ArrayList<>();
        recC.add(rc);
        recC.add(rc1);

        rcC = new ReceiptsCollection();
        rcC.setRecipes(recC);
    }

    @Test
    public void testRestGetValidReceipts() throws Exception {

        given(service.getValidReceiptsForLunchOnDay(LocalDate.parse("2018-06-17")))
                .willReturn(rcC);

        MvcResult result = mockMvc.perform(get("/lunch?day=2018-06-17"))
                .andExpect(status().isOk()).andReturn();

        verify(service, times(1))
                .getValidReceiptsForLunchOnDay(LocalDate.parse("2018-06-17"));

        Assert.assertEquals("Check responce string.", result.getResponse().getContentAsString(),
                "{\"recipes\":[{\"title\":\"Omlette test\",\"ingredients\":" +
                        "[\"Eggs\",\"Milk\"]},{\"title\":\"Salad test\",\"ingredients\":" +
                        "[\"Lettuce\",\"Olive Oil\"]}]}");

    }

}
