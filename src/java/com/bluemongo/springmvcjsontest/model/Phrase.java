package com.bluemongo.springmvcjsontest.model;

import com.google.gson.Gson;

import java.util.Calendar;

/**
 * Created by glenn on 23/12/14.
 */
public class Phrase implements Comparable {
    private String phrase;
    private int rating;

    public static boolean AlreadyExists(String phraseString) {
        return false;
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
        phrase = "";
        rating = 0;
    }

    public Phrase(String phraseText){
        phrase = phraseText;
        rating = 0;
        numberOfPhrases++;
        createdOrder = numberOfPhrases;
    }

    public void setPhrase(String phraseText){
        if(this.phrase.equals("")) {
            this.phrase = phraseText;
        }
    }

    @Override
    public String toString() {
        return phrase;
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
