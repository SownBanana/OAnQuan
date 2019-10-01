package com.vippro.model;

public class Cell {
    private int point;
    private boolean isKing;

    public Cell(boolean isKing, int point) {
        this.isKing = isKing;
        this.point = point;
    }

    public Cell() {
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public boolean isKing() {
        return isKing;
    }

    public void setKing(boolean king) {
        isKing = king;
    }
    public void raisePoint(int num){
        setPoint(getPoint() + num);
    }
    public void downPoint(int num){
        setPoint(getPoint() - num);
    }
    public boolean isEmpty(){
        if(point==0) return true;
         else return false;
    }
}
