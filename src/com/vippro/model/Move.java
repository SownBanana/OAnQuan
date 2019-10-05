package com.vippro.model;

public class Move {
    public int position;
    public boolean direct;  //0-left   1-right
    public int eval;


    public Move() {
    }

    public Move(int position, boolean direct) {
        this.position = position;
        this.direct = direct;
    }

    public Move(int position, boolean direct, int eval) {
        this.position = position;
        this.direct = direct;
        this.eval = eval;
    }

    public Move(int eval) {
        this.eval = eval;
    }

    @Override
    public String toString() {
        return "Move{" +
                "position=" + position +
                ", direct=" + direct +
                ", eval=" + eval +
                '}';
    }
}
