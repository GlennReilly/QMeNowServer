import com.bluemongo.springmvcjsontest.model.Appointment;
import com.bluemongo.springmvcjsontest.model.Customer;
import com.bluemongo.springmvcjsontest.model.Location;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by glenn on 14/10/15.
 */
public class testModels {

    @Test
    public void testAppointmentWithoutLocationSave(){
        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(new Date());
        appointment.setAppointmentType("test appointment type here");
        appointment.setLocationId(1);
        appointment.setMessageToUser("this is a test message to the user");
        appointment.setUserId(1);
        int newId = appointment.save();
        assertNotNull(newId);
    }

    @Test
    public void testCreateCustomer(){

        Customer customer = new Customer();
        customer.setEmailAddress("test@email.com.au");
        customer.setPhoneNumber("0405060708");
        customer.setPhysicalAddress("123 smith street, Whererever, NSW 2345");
        customer.setFirstName("nigel");
        customer.setLastName("jones");

        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
        String dateInString = "31-08-1982";
        try {
            Date DOB = sdf.parse(dateInString);
            customer.setDOB(DOB);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int newId = customer.save();
        assertNotNull(newId);
    }
}
