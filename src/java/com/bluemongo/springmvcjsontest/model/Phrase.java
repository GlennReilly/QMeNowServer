package com.bluemongo.springmvcjsontest.model;

import com.google.gson.Gson;
import org.joda.time.DateTime;
import utils.InputHelper;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by glenn on 23/12/14.
 */
public class Phrase implements Comparable {
    private String phraseText;
    private int rating;
    private String phraseAuthor = "";
    private Date phraseDate;

    public static boolean AlreadyExists(String phraseString) {
        return false;
    }

    public String getPhraseText() {
        return InputHelper.cleanText(phraseText);
    }

    public String getPhraseAuthor() {
        return InputHelper.cleanText(phraseAuthor);
    }

    public Date getPhraseDate() {
        if(phraseDate == null)
        {
            phraseDate = new Date();
        }
        return phraseDate;
    }

    public enum UsersVoteOptions {DownVote, NoVote, UpVote};
    static int numberOfPhrases;
    private int createdOrder;

    public int getCreatedOrder() {
        return createdOrder;
    }

    public UsersVoteOptions getUsersVote() {
        return usersVote;
    }

    public void setUsersVote(UsersVoteOptions usersVote) {
        this.usersVote = usersVote;
    }

    private UsersVoteOptions usersVote;

    public Phrase(){
        phraseText = "";
        rating = 0;
    }

    public Phrase(String phraseText){
        this.phraseText = InputHelper.cleanText(phraseText);
        rating = 0;
        numberOfPhrases++;
        createdOrder = numberOfPhrases;
    }

    public void setPhraseText(String phraseText){
        if((!phraseText.trim().equals("")) && !Phrase.AlreadyExists(phraseText)) {
            if (this.phraseText.equals("")) {
                this.phraseText = InputHelper.cleanText(phraseText);

            }
        }
    }

    @Override
    public String toString() {
        return phraseText;
    }

    public int getRating(){
        return rating;
    }

    public void addOneToRating(){
        rating++;
    }

    public void minusOneFromRating(){
        rating--;
    }


    @Override
    public int compareTo(Object another) {
        Phrase anotherPhrase = (Phrase) another;
        int phraseRatingPriority = (this.getRating() > anotherPhrase.getRating())? 1 : (this.getRating() < anotherPhrase.getRating())? -1: 0;
        int createdOrderPriority = this.getCreatedOrder() > anotherPhrase.getCreatedOrder()? -1 : 1;
        if(phraseRatingPriority==0){
            return createdOrderPriority;
        }else {
            return phraseRatingPriority;
        }
    }

    public String toJson() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }
}
