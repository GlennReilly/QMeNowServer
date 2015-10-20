package utils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateParser;
import org.apache.commons.lang3.time.FastDateFormat;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by glenn on 1/03/15.
 */
public class InputHelper {

    public static String cleanText(String stringToClean){
        String returnString = Normalizer.normalize(stringToClean, Form.NFC);
        returnString = returnString.replace("<","")
                .replace(">","")
                .replace("[","")
                .replace("]","")
                .replace(";","");
        return returnString;
    }

    public static String getISO8601StringFromDate(Date date) {
        final String pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        if(date == null) {
            date = Calendar.getInstance().getTime();
        }
        String dateString = DateFormatUtils.format(date, pattern);
        return dateString;
    }

    public static Date getDateFromISO8601String(String dateString){
        final String pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        Date dateFromString = null;
        try {
            DateParser fastDateParser = FastDateFormat.getInstance(pattern);
            dateFromString = fastDateParser.parse(dateString);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateFromString;
    }

    public static void resetTimeOfDate(Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
    }
}
