package com.vippro.model;

public class Board {
    private Cell[] cells;
    private int maxPoint;           //1-true
    private int minPoint;           //0-false
    private boolean turn;

    public Board() {
    }

    public Board(boolean turn) {
        this.cells = new Cell[12];
        this.maxPoint = 0;
        this.minPoint = 0;
        this.turn = turn;
    }

    public Board(Cell[] cells, int maxPoint, int minPoint, boolean turn) {
        this.cells = cells;
        this.maxPoint = maxPoint;
        this.minPoint = minPoint;
        this.turn = turn;
    }

    public Cell[] getCells() {
        return cells;
    }

    public void setCells(Cell[] cells) {
        this.cells = cells;
    }

    public int getMaxPoint() {
        return maxPoint;
    }

    public void setMaxPoint(int maxPoint) {
        this.maxPoint = maxPoint;
    }

    public int getMinPoint() {
        return minPoint;
    }

    public void setMinPoint(int minPoint) {
        this.minPoint = minPoint;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public boolean move(int position, boolean direct){         //false-left        true-right
        if (cells[position].getPoint() != 0 && !cells[position].isKing()) {

            if (direct) {                //phải
                int index = position + 1;
                while (cells[position].getPoint() != 0) {
                    cells[index].raisePoint(1);
                    cells[position].downPoint(1);
                    index = (index + 1) % 12;
                    drawBoard();
                }
                if (cells[index].isEmpty())
                    checkEat((index - 1) % 12, direct);
                else move(index, direct);
            } else {                       //trái
                int index = position - 1;
                while (cells[position].getPoint() != 0) {
                    cells[index].raisePoint(1);
                    cells[position].downPoint(1);
                    index = (index - 1) % 12;
                    drawBoard();
                }
                if (cells[index].isEmpty())
                    checkEat((index + 1) % 12, direct);
                else move(index, direct);
            }
        return true;
        }
        return false;
    }
    public void eat(int position){
            System.out.println("ăn "+position);
            if(turn) maxPoint+=cells[position].getPoint();
             else minPoint+=cells[position].getPoint();
            cells[(position)].setPoint(0);
            drawBoard();
    }
    public void checkEat(int position, boolean direct ){
        if(direct){
          if(  !cells[position+2].isEmpty() && !cells[position+1].isKing()) {
              System.out.println("ăn được");
              eat(position+2);
              checkEat((position+2)%12,direct);
          }
        }
    }
    public void drawBoard(){
        System.out.println("|"+cells[0].getPoint()+"|"+cells[1].getPoint()+"|"+cells[2].getPoint()+"|"+cells[3].getPoint()+"|"+cells[4].getPoint()+
                "|"+cells[5].getPoint()+ ")");
        System.out.println("("+cells[11].getPoint()+"|"+cells[10].getPoint()+"|"+cells[9].getPoint()+"|"+cells[8].getPoint()+"|"+cells[7].getPoint()+
                "|"+cells[6].getPoint()+ "|");
        System.out.println("====================");
    }
}
