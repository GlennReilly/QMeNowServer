import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateParser;
import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by glenn on 11/10/15.
 */
public class testBarcodeParsing {


    @Test
    public void testDateStringParsing(){
        //"yyyy-MM-dd'T'HH:mm:ssz"
        //2015-10-10T11:16+00:00

        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        //sdf.setTimeZone(TimeZone.getTimeZone("GMT"));


        //Convert Date to String..
        final String pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";

        try {
            Date origDate = Calendar.getInstance().getTime(); //Sun Oct 11 10:42:21 AEDT 2015
            Date dateFromString;
            String dateString = DateFormatUtils.format(origDate,pattern);


            //Convert String back into Date..
            DateParser fastDateParser = FastDateFormat.getInstance(pattern);
            dateFromString = fastDateParser.parse(dateString);

            assertEquals(origDate.toString(), dateFromString.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }


/*        String nowString = ISO8601DateParser.toString(now);  //2015-10-10T23:21+00:00
        try {
            Date dateFromString = ISO8601DateParser.parse(nowString);
        }catch (ParseException pex){
            pex.printStackTrace();
        }*/
    }
}
