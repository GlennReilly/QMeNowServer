package utils;
import java.text.Normalizer;
import java.text.Normalizer.Form;

/**
 * Created by glenn on 1/03/15.
 */
public class InputHelper {
    public String cleanText(String stringToClean){
        String returnString = Normalizer.normalize(stringToClean, Form.NFC);
        returnString.replace("<","").replace(">","").replace("[","").replace("]","");
        return returnString;
    }

}
