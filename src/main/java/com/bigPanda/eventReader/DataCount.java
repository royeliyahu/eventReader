package com.bigPanda.eventReader;

public class DataCount {

    private int count;
    private String word;

    public DataCount(int count, String word) {
        this.count = count;
        this.word = word;
    }

    public DataCount() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
