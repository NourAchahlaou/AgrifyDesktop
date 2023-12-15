/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agrify.utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author ihebb
 */
public class DateUtils {
    public static String getDateString(Date date, String format){
        // Create a SimpleDateFormat object with the pattern
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);

        // Use the SimpleDateFormat to format the Date as a String
        return dateFormat.format(date);
    }
    
    public static Date convertDateToLocalDate(LocalDate date){
        LocalDate selectedDate = date;
        ZoneId defaultZoneId = ZoneId.systemDefault();
        
        return Date.from(selectedDate.atStartOfDay(defaultZoneId).toInstant());
    }
    
   public static LocalDate convertToLocalDate(Date date) {
        if (date != null) {
            // Convert the java.sql.Date to java.util.Date
            java.util.Date utilDate = new java.util.Date(date.getTime());

            // Convert the java.util.Date to Instant
            Instant instant = utilDate.toInstant();

            // Convert the Instant to LocalDate using the system default time zone
            LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();

            return localDate;
        }

        return null; // Handle null dates if necessary
    }
}
