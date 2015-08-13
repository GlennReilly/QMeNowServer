package com.bluemongo.springmvcjsontest.controller;

import com.bluemongo.springmvcjsontest.model.*;
import com.bluemongo.springmvcjsontest.persistence.DBHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;


/**
 * Created by glenn on 8/02/15.
 */



@RestController
@RequestMapping("/greeting")
public class RestGreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    static final Logger logger = LogManager.getLogger(RestGreetingController.class);
    //private PersistGreeting persistGreeting = new PersistGreeting();


    public @ResponseBody
    Greeting greeting(@RequestParam(value="name", defaultValue="Wurld") String name) {
        Greeting greeting = new Greeting(counter.incrementAndGet(), String.format(template, name), Greeting.Status.OK);
        //persistGreeting.SaveGreeting(greeting);
        return greeting;
    }


    @RequestMapping( value = "/getGreetingJson", method = RequestMethod.GET, headers="Accept=application/json", produces = {"application/json"})
    public @ResponseBody String greetingGson(@RequestParam(value="name", defaultValue="Wurld") String name) {
        Greeting greeting = new Greeting(counter.incrementAndGet(), String.format(template, name), Greeting.Status.OK);
        //persistGreeting.SaveGreeting(greeting);
        return greeting.toJson();
    }


    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public @ResponseBody String greeting2(@PathVariable(value="name") String name) {
        String result =  "Hello " + name;
        return result;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET, headers="Accept=application/json", produces = {"application/json"})
    public @ResponseBody String greetingtest() {
        String result =  "This is a GreetingTest";
        return result;
    }

    @RequestMapping("/testReconfigurableApp")
    public ReconfigurableAppConfig testModelReturn() {
        ReconfigurableAppConfig config = new ReconfigurableAppConfig();
        config.setTitle("Great trucking songs of the renaissance..");

        AppButton button1 = new AppButton();
        button1.setButtonText("button1");
        config.addButton(button1);

        AppButton button2 = new AppButton();
        button2.setButtonText("button2");
        config.addButton(button2);

        AppButton button3 = new AppButton();
        button3.setButtonText("button3");
        config.addButton(button3);

        //persistThis(config);
        return config;
    }

    @RequestMapping("/testDB")
    public String testDB(){
        DBHelper dbHelper = new DBHelper();
        String testValues = dbHelper.testDB();
        //persistThis(config);
        return testValues;
    }



/*    private void persistThis(ReconfigurableAppConfig PageConfig) {
        ObjectifyService.register(ReconfigurableAppConfig.class);
        ofy().save().entity(PageConfig).now();
    }*/


}
