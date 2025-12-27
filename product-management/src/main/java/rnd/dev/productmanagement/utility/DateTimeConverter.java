package rnd.dev.productmanagement.utility;

import rnd.dev.productmanagement.constant.DateTimeConstants;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeConverter {

    private DateTimeConverter() {

    }

    public static String getDateTime(Date date) {
        return new SimpleDateFormat(DateTimeConstants.DATE_TIME_FORMAT).format(date);
    }
}
