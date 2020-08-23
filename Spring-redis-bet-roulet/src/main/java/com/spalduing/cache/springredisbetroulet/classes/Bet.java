package com.spalduing.cache.springredisbetroulet.classes;

import java.io.Serializable;

public class Bet implements Serializable {

    private static final long serialVersionUID = 8940856680802226143L;
    private String id;
    private String rouletteId;
    private String betColor;
    private int betNumber;
    private double betAmount;

    public Bet(String id, String rouletteId, String betColor, int betNumber, double betAmount) {
        this.id = id;
        this.rouletteId = rouletteId;
        this.betColor = betColor;
        this.betNumber = betNumber;
        this.betAmount = betAmount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRouletteId() {
        return rouletteId;
    }

    public void setRouletteId(String rouletteId) {
        this.rouletteId = rouletteId;
    }

    public String getBetColor() {
        return betColor;
    }

    public void setBetColor(String betColor) {
        this.betColor = betColor;
    }

    public int getBetNumber() {
        return betNumber;
    }

    public void setBetNumber(int betNumber) {
        this.betNumber = betNumber;
    }

    public double getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(double betAmount) {
        this.betAmount = betAmount;
    }
}
