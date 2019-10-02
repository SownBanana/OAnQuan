package com.vippro.model;

public class Move {
    public int position;
    public boolean direct;  //0-left   1-right


    public Move() {
    }

    public Move(int position, boolean direct) {
        this.position = position;
        this.direct = direct;
    }

}
