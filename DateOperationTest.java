package com.prathosh;
import dateOperations.DateOperation;
import org.junit.Assert;
import org.junit.Assert.*;

import org.junit.Test;

import java.time.DateTimeException;
import java.util.LinkedHashMap;

public class DateOperationTest {
    DateOperation test = new DateOperation();

    @Test
    public  void works_properly_for_leapYear(){
        LinkedHashMap<String, String> result = test.getDays(2000, 02, 28);
        LinkedHashMap<String, String> leapYear = new LinkedHashMap<String, String>();

        leapYear.put("29/2/2000", " TUESDAY");
        leapYear.put("1/3/2000", " WEDNESDAY");
        leapYear.put("2/3/2000", " THURSDAY");
        leapYear.put("3/3/2000", " FRIDAY");
        leapYear.put("4/3/2000", " SATURDAY");
        leapYear.put("5/3/2000", " SUNDAY");
        leapYear.put("6/3/2000", " MONDAY");
        Assert.assertTrue("True",leapYear.equals(result));
    }

    @Test
    public  void works_properly_for_nonLeapYear(){
        LinkedHashMap<String, String> result = test.getDays(2001, 02, 27);
        LinkedHashMap<String, String> nonLeapYear = new LinkedHashMap<String, String>();

        nonLeapYear.put("28/2/2001", " WEDNESDAY");
        nonLeapYear.put("1/3/2001", " THURSDAY");
        nonLeapYear.put("2/3/2001", " FRIDAY");
        nonLeapYear.put("3/3/2001", " SATURDAY");
        nonLeapYear.put("4/3/2001", " SUNDAY");
        nonLeapYear.put("5/3/2001", " MONDAY");
        nonLeapYear.put("6/3/2001", " TUESDAY");
        Assert.assertTrue("ok",nonLeapYear.equals(result));
    }
    @Test
    public  void works_properly_for_yearEnd(){
        LinkedHashMap<String, String> result = test.getDays(2000, 12, 31);
        LinkedHashMap<String, String> yearEnd = new LinkedHashMap<String, String>();

        yearEnd.put("1/1/2001", " MONDAY");
        yearEnd.put("2/1/2001", " TUESDAY");
        yearEnd.put("3/1/2001", " WEDNESDAY");
        yearEnd.put("4/1/2001", " THURSDAY");
        yearEnd.put("5/1/2001", " FRIDAY");
        yearEnd.put("6/1/2001", " SATURDAY");
        yearEnd.put("7/1/2001", " SUNDAY");
        Assert.assertTrue(yearEnd.equals(result));
    }
    @Test
    public  void works_properly_for_31_days_months(){
        LinkedHashMap<String, String> result = test.getDays(2001, 07, 27);
        LinkedHashMap<String, String> check31Days = new LinkedHashMap<String, String>();

        check31Days.put("28/7/2001", " SATURDAY");
        check31Days.put("29/7/2001", " SUNDAY");
        check31Days.put("30/7/2001", " MONDAY");
        check31Days.put("31/7/2001", " TUESDAY");
        check31Days.put("1/8/2001", " WEDNESDAY");
        check31Days.put("2/8/2001", " THURSDAY");
        check31Days.put("3/8/2001", " FRIDAY");
        Assert.assertTrue(check31Days.equals(result));
    }

    @Test
    public  void works_properly_for_30_days_months(){
        LinkedHashMap<String, String> result = test.getDays(1999, 11, 28);
        LinkedHashMap<String, String> check30Days = new LinkedHashMap<String, String>();

        check30Days.put("29/11/1999", " MONDAY");
        check30Days.put("30/11/1999", " TUESDAY");
        check30Days.put("1/12/1999", " WEDNESDAY");
        check30Days.put("2/12/1999", " THURSDAY");
        check30Days.put("3/12/1999", " FRIDAY");
        check30Days.put("4/12/1999", " SATURDAY");
        check30Days.put("5/12/1999", " SUNDAY");
        Assert.assertTrue(check30Days.equals(result));
    }
    @Test(expected = DateTimeException.class)
    public void works_properly_for_invalid_input(){
        LinkedHashMap<String, String> result = test.getDays(2000, 02, 31);
    }

}
