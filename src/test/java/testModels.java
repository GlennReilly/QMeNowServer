import com.bluemongo.springmvcjsontest.model.Appointment;
import com.bluemongo.springmvcjsontest.model.AppointmentStatus;
import com.bluemongo.springmvcjsontest.model.Customer;
import com.bluemongo.springmvcjsontest.persistence.AppointmentStore;
import org.junit.Test;
import utils.InputHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by glenn on 14/10/15.
 */
public class testModels {

    @Test
    public void testCreateAppointmentWithoutLocation(){
        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(new Date());
        appointment.setAppointmentTypeId(1);
        appointment.setLocationId(1);
        appointment.setStatus(1);
        appointment.setMessageToCustomer("this is a test message to the user");
        appointment.setCustomerId(1);
        int newId = appointment.saveNew();
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
        String dateInString = "31-08-1952";
        try {
            Date DOB = sdf.parse(dateInString);
            customer.setDOB(DOB);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int newId = customer.saveNew();
        assertNotNull(newId);
    }

    @Test
    public void testCreateAppointmentStatus(){
        AppointmentStatus appointmentStatus = new AppointmentStatus();
        appointmentStatus.setBusinessId(1);
        appointmentStatus.setStatusName("expected");
        int newId = appointmentStatus.saveNew();
        assertNotNull(newId);
    }

    @Test
    public void testGetAllAppointmentsForUser(){
        AppointmentStore appointmentStore = new AppointmentStore();
        List<Appointment> appointmentList = appointmentStore.getAll(1);
        assertTrue(appointmentList.size() > 0);
    }

    @Test
    public void testGetAllAppointmentsForUserForToday(){
        AppointmentStore appointmentStore = new AppointmentStore();
        List<Appointment> appointmentList = appointmentStore.getAllForToday(1);
        assertTrue(appointmentList.size() > 0);
    }

    @Test
    public void testGetAllAppointmentsForUserForDate(){
        AppointmentStore appointmentStore = new AppointmentStore();
        Date date = Calendar.getInstance().getTime();

        Calendar cal = Calendar.getInstance(); // locale-specific
        cal.setTime(date);
        InputHelper.resetTimeOfDate(cal);

        Date dateAtMidnight = cal.getTime();

        List<Appointment> appointmentList = appointmentStore.getAllForDate(1, dateAtMidnight);
        assertTrue(appointmentList.size() > 0);
    }


}
