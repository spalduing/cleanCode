package com.spalduing.cache.springredisbetroulet.classes;

import java.io.Serializable;

public class Roulette implements Serializable {

    private static final long serialVersionUID = -5266752687994451785L;
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
