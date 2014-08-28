package ru.stereohorse.polypus.services;


import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class DateService {
    public Date getNextDay(Date date) {
        Calendar calendar = getStartOfDate(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    public Date getStartOfDay(Date date) {
        return getStartOfDate(date).getTime();
    }

    private Calendar getStartOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }
}
