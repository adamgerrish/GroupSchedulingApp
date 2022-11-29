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

   public static ArrayList<LocalDate> daysInMonthArray(LocalDate date) {
      ArrayList<LocalDate> daysInMonthArray = new ArrayList<>();
      YearMonth yearMonth = YearMonth.from(date);
      int daysInMonth = yearMonth.lengthOfMonth();
      LocalDate firstOfMonth = CalUtils.selectedDate.withDayOfMonth(1);
      int dayOfWeek= firstOfMonth.getDayOfWeek().getValue();

      for (int i = 1; i<=42 ;i++){
         if (i<= dayOfWeek || i> daysInMonth +dayOfWeek)
            daysInMonthArray.add(null);

         else
            daysInMonthArray.add(LocalDate.of(selectedDate.getYear(),selectedDate.getMonth(), i - dayOfWeek));
      }
      return daysInMonthArray;
   }

   public static ArrayList<LocalDate> daysInWeek (LocalDate selectedDate){
      ArrayList<LocalDate> days = new ArrayList<>();
      LocalDate curr = sundayDate(selectedDate);
      LocalDate end = curr.plusWeeks(1);

      while (curr.isBefore(end)){
         days.add(curr);
         curr = curr.plusDays(1);
      }
      return days;
   }

   private static LocalDate sundayDate(LocalDate curr){
      LocalDate lastWeek = curr.minusWeeks(1);

      while(curr.isAfter(lastWeek)){
         if( curr.getDayOfWeek() == DayOfWeek.SUNDAY)
            return curr;
         curr = curr.minusDays(1);
      }

      return null;

   }
   public static String monthYearFromDate(LocalDate date){
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
      return date.format(formatter);
   }


}
