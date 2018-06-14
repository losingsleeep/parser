package com.bobby.parser;

import java.time.LocalDateTime;

/**
 * @author Babak Eghbali (Bob)
 * @since 2018/06/09
 */
public class Utils {

    public static LocalDateTime getEndDate(LocalDateTime startDate, Duration duration){
        if (Duration.HOURLY.equals(duration)) {
            return startDate.plusHours(1);
        }else {
            return startDate.plusDays(1);
        }
    }

    public static boolean isEqualOrAfter(LocalDateTime date, LocalDateTime other){
        return !date.isBefore(other);
    }

    public static boolean isEqualOrBefore(LocalDateTime date, LocalDateTime other){
        return !date.isAfter(other);
    }

}
