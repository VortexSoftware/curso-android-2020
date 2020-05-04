package com.cursoandroid.gestordegastos.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {
    public static final String FULL_DATE = "EEEE dd 'de' MMM 'de' yyyy , HH:mm";
    public static final String SERVER_DATE = "yyyy-MM-dd'T'HH:mm:ssZ";

    public static String dateToString(Date date, String format){
        SimpleDateFormat formater = new SimpleDateFormat(format);
        return formater.format(date);
    }

    public static Date stringToDate(String sDate,String format){
        if (sDate ==null || sDate.isEmpty())return null;
        DateFormat formater = new SimpleDateFormat(format);
        formater.setTimeZone(TimeZone.getDefault());
        try {
            return formater.parse(sDate);
        }catch (ParseException e){
            e.printStackTrace();
            return null;
        }
    }

    public static String stringToString(String date, String inputFormat,String outputFormat){
        Date d = stringToDate(date,inputFormat);
        return dateToString(d,outputFormat);
    }
}
