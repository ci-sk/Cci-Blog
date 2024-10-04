package org.example.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFormatUtil {
    public static String formatTimestamp(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(timestamp);
        return sdf.format(date);
    }
}
