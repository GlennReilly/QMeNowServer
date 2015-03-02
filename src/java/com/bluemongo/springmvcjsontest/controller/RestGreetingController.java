package com.bluemongo.springmvcjsontest.controller;

import com.bluemongo.springmvcjsontest.model.Greeting;

import com.bluemongo.springmvcjsontest.model.Phrase;
import com.bluemongo.springmvcjsontest.persistence.PersistGreeting;
import com.bluemongo.springmvcjsontest.persistence.PersistPhrase;
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
    private PersistGreeting persistGreeting = new PersistGreeting();
    private PersistPhrase persistPhrase = new PersistPhrase();

    public @ResponseBody
    Greeting greeting(@RequestParam(value="name", defaultValue="Wurld") String name) {
        Greeting greeting = new Greeting(counter.incrementAndGet(), String.format(template, name), Greeting.Status.OK);
        persistGreeting.SaveGreeting(greeting);
        return greeting;
    }

    @RequestMapping( value = "/createPhrase", method = RequestMethod.GET, headers="Accept=application/json", produces = {"application/json"})
    public @ResponseBody String CreatePhrase(@RequestParam(value="phraseString") String phraseString){
        Phrase phrase = new Phrase();
        phrase.setPhraseText(phraseString);
        persistPhrase.SavePhrase(phrase);
        return phrase.toJson();
    }

    @RequestMapping( value = "/getGreetingJson", method = RequestMethod.GET, headers="Accept=application/json", produces = {"application/json"})
    public @ResponseBody String greetingGson(@RequestParam(value="name", defaultValue="Wurld") String name) {
        Greeting greeting = new Greeting(counter.incrementAndGet(), String.format(template, name), Greeting.Status.OK);
        persistGreeting.SaveGreeting(greeting);
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
