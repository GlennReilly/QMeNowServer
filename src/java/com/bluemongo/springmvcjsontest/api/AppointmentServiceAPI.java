package com.bluemongo.springmvcjsontest.api;

import com.bluemongo.springmvcjsontest.model.Appointment;
import com.bluemongo.springmvcjsontest.model.AppointmentCheckInDTO;
import com.bluemongo.springmvcjsontest.model.AppointmentsResponse;
import com.bluemongo.springmvcjsontest.service.AppointmentAndCustomer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


/**
 * Created by glenn on 4/10/15.
 */

/*@RequestMapping(value = "/user/getAppointments/{userId}", method = RequestMethod.GET)
public String GetUserAppointments(@PathVariable int userId){
        String result = "Nothing";*/

public interface AppointmentServiceAPI{


    // @PUT("/FlexibleUIConfig/api/v1/Appointment/{id}/CheckIn")
    AppointmentsResponse checkInAppointment(int appointmentId, AppointmentCheckInDTO appointmentCheckIn);

    //@GET("/FlexibleUIConfig/api/v1/Appointments/{customerId}")
    // eg: http://10.1.1.7:8080/FlexibleUIConfig/api/v1/AppointmentsToday/1/7
    AppointmentsResponse getAppointmentsToday(Integer businessId, Integer customerId);



    //@GET("/FlexibleUIConfig/api/v1/AppointmentsTodayTest/{customerId}")
    List<Appointment> getAppointmentsTest(Integer customerId);

    //@GET("/FlexibleUIConfig/api/v1/test/1234")
    List<String> doTest(Integer userId);

    //@GET("/FlexibleUIConfig/api/v1/Appointments?customerId={userId}&firstName={firstName}&lastName={lastName}")
    AppointmentsResponse getAppointmentsByUserIDAndName(Integer customerId, String firstName, String lastName);

    //@GET("/SpringMVCJsonTest/FlexibleUIConfig/api/v1/Customer?firstName={firstName}&lastName={lastName}")
    //@GET("/SpringMVCJsonTest/FlexibleUIConfig/api/v1/Customer/getByName?firstName={firstName}&lastName={lastName}")
    //List<UserDetails> getUserMatchesByName(String firstName, String lastName);

    //@RequestMapping(value = "/logo", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    //@GET("/FlexibleUIConfig/api/v1/Business/getLogo)
    //byte[] getLogo(HttpSession httpSession) throws IOException;

}
