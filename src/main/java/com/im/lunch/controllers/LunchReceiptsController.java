package com.im.lunch.controllers;

import com.im.lunch.domain.ReceiptsCollection;
import com.im.lunch.services.LunchReceiptsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * @author imaltsev
 * @since 15/06/18
 * <p>
 * This is the RestController which return Collection of valid receipts
 */
@RestController
public class LunchReceiptsController {

    @Autowired
    LunchReceiptsService lunchReceiptsService;

    /**
     * Rest server method, get request and return the valid lunch receipts.
     *
     * @param day (optional) is dayOfLunch in format e.g. 2018-06-15
     *            (return exception message if format is incorrect) ,
     *            if day is not entered - the current dayOfLunch selected.
     *
     * @return ReceiptsCollection
     */
    @RequestMapping(value = "/lunch", method = RequestMethod.GET)
    public ReceiptsCollection getReceiptsForDay(@RequestParam(value = "day", required = false) String day) {
        LocalDate dayOfLunch;
        if(StringUtils.isEmpty(day)){
            dayOfLunch = LocalDate.now();
        } else {
            dayOfLunch = LocalDate.parse(day);
        }
        return lunchReceiptsService.getValidReceiptsForLunchOnDay(dayOfLunch);
    }

}
