package com.bluemongo.springmvcjsontest.controller;

import com.bluemongo.springmvcjsontest.model.Greeting;
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

    @RequestMapping( value = "/getGreeting", method = RequestMethod.GET, headers="Accept=application/json", produces = {"application/json"})
    public @ResponseBody
    Greeting greeting(@RequestParam(value="name", defaultValue="Wurld") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name), Greeting.Status.OK);
    }

    @RequestMapping( value = "/getGreetingGson", method = RequestMethod.GET, headers="Accept=application/json", produces = {"application/json"})
    public @ResponseBody String greetingGson(@RequestParam(value="name", defaultValue="Wurld") String name) {
        Greeting greeting = new Greeting(counter.incrementAndGet(), String.format(template, name), Greeting.Status.OK);
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
}
