package com.example.groupcalendaapp;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class CalUtils {
   public static  LocalDate selectedDate;

   public static String formatDate( LocalDate date){
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
      return date.format(formatter);
   }

   public static String formatTime (LocalTime time){
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
      return  time.format(formatter);
   }

   public static ArrayList<String> daysInMonthArray(LocalDate date) {
      ArrayList<String> daysInMonthArray = new ArrayList<>();
      YearMonth yearMonth = YearMonth.from(date);
      int daysInMonth = yearMonth.lengthOfMonth();
      LocalDate firstOfMonth = CalUtils.selectedDate.withDayOfMonth(1);
      int dayOfWeek= firstOfMonth.getDayOfWeek().getValue();

      for (int i = 1; i<=42 ;i++){
         if (i<= dayOfWeek || i> daysInMonth +dayOfWeek){
            daysInMonthArray.add("");

         }else{
            daysInMonthArray.add(String.valueOf(i - dayOfWeek));
         }
      }
      return daysInMonthArray;
   }

   public String monthYearFromDate(LocalDate date){
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
      return date.format(formatter);
   }

}
