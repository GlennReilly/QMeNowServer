package com.bluemongo.springmvcjsontest.controller;

import com.bluemongo.springmvcjsontest.model.Phrase;
import com.bluemongo.springmvcjsontest.persistence.PersistPhrase;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by glenn on 8/03/15.
 */
@Controller
@RequestMapping(value = "/phrase")
public class RestPhraseController {
    private PersistPhrase persistPhrase = new PersistPhrase();

    @RequestMapping( value = "/createPhrase", method = RequestMethod.GET, headers="Accept=application/json", produces = {"application/json"})
    public @ResponseBody
    String CreatePhrase(@RequestParam(value="phraseString") String phraseString){
        Phrase phrase = new Phrase();
        phrase.setPhraseText(phraseString);
        persistPhrase.SavePhrase(phrase);
        return phrase.toJson();
    }

    @RequestMapping(value="/newPhraseForm", method= RequestMethod.GET)
    public ModelAndView getNewPhraseForm(){
        ModelAndView modelAndView = new ModelAndView("createPhraseForm","command", new Phrase());
        return modelAndView;
    }


    @RequestMapping(value="/newPhraseForm", method= RequestMethod.POST)
    public String processNewPhraseForm(@ModelAttribute() Phrase phrase){
        if(Phrase.AlreadyExists(phrase.getPhraseText(), phrase.getPhraseAuthor())){

        }
        else{
            persistPhrase.SavePhrase(phrase);
        }


        return "success";
    }
}
