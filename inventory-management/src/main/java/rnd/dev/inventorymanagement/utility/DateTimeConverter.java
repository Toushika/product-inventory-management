package rnd.dev.inventorymanagement.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

import static rnd.dev.inventorymanagement.constant.DatePatternConstants.DATE_TIME_PATTERN;

public class DateTimeConverter {

    private DateTimeConverter() {

    }

    public static String getCurrentDateTime() {
        return new SimpleDateFormat(DATE_TIME_PATTERN).format(new Date());
    }
}
