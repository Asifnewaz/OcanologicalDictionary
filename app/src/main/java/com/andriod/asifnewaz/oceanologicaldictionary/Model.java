package com.andriod.asifnewaz.oceanologicaldictionary;

import java.io.Serializable;

public class Model implements Serializable{
    String word, definition;
    long id;
    int isBookmarked;


    public Model() {
    }

    public Model(String word, String definition){
        this.word=word;
        this.definition = definition;
    }

    public Model(String word, String definition, int isBookmarked, long id) {
        this.word = word;
        this.definition = definition;
        this.id = id;
        this.isBookmarked = isBookmarked;
    }

    public int isBookmarked() {
        return isBookmarked;
    }

    public void setBookmarked(int bookmarked) {
        isBookmarked = bookmarked;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public int getIsBookmarked() {
        return isBookmarked;
    }
}
