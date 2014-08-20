package ru.stereohorse.polypus.services;


import org.junit.Test;
import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateServiceTest {
    private DateService dateService = new DateService();

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.SSS");

    @Test
    public void testStartOfDay() throws ParseException {
        Date date = dateService.getStartOfDay(dateFormat.parse("19.08.2014 20:47:54.345"));
        assertEquals(dateFormat.parse("19.08.2014 00:00:00.000"), date);
    }

    @Test
    public void testEndOfDay() throws ParseException {
        Date date = dateService.getEndOfDay(dateFormat.parse("19.08.2014 20:47:54.345"));
        assertEquals(dateFormat.parse("19.08.2014 23:59:59.999"), date);
    }
}
