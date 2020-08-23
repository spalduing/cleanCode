package com.spalduing.cache.springredisbetroulet.gclass;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Roulette implements Serializable {
    private static final long serialVersionUID = 6432364725432526272L;
    private String id;
    private String name;
    private String betState;

    public Roulette(String id, String name, String betState) {
        this.id = id;
        this.name = name;
        this.betState = betState;
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

    public String getBetState() {
        return betState;
    }

    public void setBetState(String betState) {
        this.betState = betState;
    }
}
