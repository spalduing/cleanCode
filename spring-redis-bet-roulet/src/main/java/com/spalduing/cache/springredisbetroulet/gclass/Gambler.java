package com.spalduing.cache.springredisbetroulet.gclass;

public class Gambler {
    private String id;
    private String name;
    private int bet;
    private boolean color;

    public Gambler(String id, String name, int bet, boolean color) {
        this.id = id;
        this.name = name;
        this.bet = bet;
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }
}
