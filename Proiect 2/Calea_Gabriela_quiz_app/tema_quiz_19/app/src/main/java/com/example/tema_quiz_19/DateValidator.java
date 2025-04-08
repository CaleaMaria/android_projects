package com.example.tema_quiz_19;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateValidator {


    public static boolean isValidDate(String dateStr) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {

            sdf.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
