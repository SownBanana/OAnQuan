package com.vippro.model;

public class Board {
    private Cell[] cells;           //king is 5 and 11
    private int maxPoint;           //1-true
    private int minPoint;           //0-false
    private boolean turn;           //1-max player
                                    //0-min player
    private int round;

    public Board() {
    }

    public Board(boolean turn) {

        Cell[] cells = new Cell[12];
        for (int i = 0; i < 5; i++) {
            cells[i] = new Cell(false,5);
        }
        for (int i = 6; i < 11; i++) {
            cells[i] = new Cell(false,5);
        }
        cells[5] = new Cell(true,10);
        cells[11] = new Cell(true,10);

        this.cells = cells;
        this.maxPoint = 0;
        this.minPoint = 0;
        this.round = 1;
        this.turn = turn;
    }

    public Board(Cell[] cells, int maxPoint, int minPoint, boolean turn, int round) {
        this.cells = cells;
        this.maxPoint = maxPoint;
        this.minPoint = minPoint;
        this.turn = turn;
        this.round = round;
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

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public boolean move(int position, boolean direct){         //false-left        true-right
        if (cells[position].getPoint() != 0 && !cells[position].isKing() && !isFirstRoundWrong(position,direct)) {
            if (direct) {                //phải
                int index = (position + 1)%12;
                while (cells[position].getPoint() != 0) {
                    cells[index].raisePoint(1);
                    cells[position].downPoint(1);
                    index = (index + 1) % 12;
//                    drawBoard();
                }
                if (cells[index].isEmpty())
                    checkEat((index - 1 + 12) % 12, direct);
                else move(index, direct);
            } else {                       //trái
                int index = (position - 1 + 12)%12;
                while (cells[position].getPoint() != 0) {
                    cells[index].raisePoint(1);
                    cells[position].downPoint(1);
                    index = (index - 1 + 12) % 12;
//                 drawBoard();
                }
                if (cells[index].isEmpty())
                    checkEat((index + 1) % 12, direct);
                else move(index, direct);
            }
            round++;
            return true;
        }
        return false;
    }
//    public boolean moveTry(int position, boolean direct){
//        if (cells[position].getPoint() != 0 && !cells[position].isKing() && !isFirstRoundWrong(position,direct)) {
//            if (direct) {                //phải
//                int index = (position + 1)%12;
//                while (cells[position].getPoint() != 0) {
//                    cells[index].raisePoint(1);
//                    cells[position].downPoint(1);
//                    index = (index + 1) % 12;
//                }
//                if (cells[index].isEmpty())
//                    checkEatFake((index - 1 + 12) % 12, direct);
//                else move(index, direct);
//            } else {                       //trái
//                int index = (position - 1 + 12)%12;
//                while (cells[position].getPoint() != 0) {
//                    cells[index].raisePoint(1);
//                    cells[position].downPoint(1);
//                    index = (index - 1 + 12) % 12;
//                }
//                if (cells[index].isEmpty())
//                    checkEatFake((index + 1) % 12, direct);
//                else move(index, direct);
//            }
//            round++;
//            return true;
//        }
//        return false;
//    }
    public void checkEat(int position, boolean direct ){
        if(direct){
            if(cells[(position + 1)%12].isEmpty() && !cells[(position + 2)%12].isEmpty() && !cells[(position +1)%12].isKing()) {
                eat((position+2)%12);
                checkEat((position+2)%12,direct);
            }
        }
        else{
            if(cells[(position - 1 + 12)%12].isEmpty() && !cells[(position-2 + 12)%12].isEmpty() && !cells[(position-1+12)%12].isKing()) {
                eat((position-2+12)%12);
                checkEat((position-2+12)%12,direct);
            }
        }
    }
    public void eat(int position){
//            System.out.println("ăn "+cells[position].getPoint() + " ô "+ position);
            if(turn) maxPoint+=cells[position].getPoint();
             else minPoint+=cells[position].getPoint();
            cells[(position)].setPoint(0);
    }

    public void drawBoard(){
        System.out.println(" Point: "+maxPoint);
        System.out.println("       0   1   2   3   4   5 ");
        System.out.println("-------------------------------");
        System.out.println("(    | "+cells[0].getPoint()+" | "+cells[1].getPoint()+" | "+cells[2].getPoint()+" | "+cells[3].getPoint()+" | "+cells[4].getPoint()+
                " | "+cells[5].getPoint()+ " )");
        System.out.println("( "+cells[11].getPoint()+" | "+cells[10].getPoint()+" | "+cells[9].getPoint()+" | "+cells[8].getPoint()+" | "+cells[7].getPoint()+
                " | "+cells[6].getPoint()+ " |    )");
        System.out.println("-------------------------------");
        System.out.println("  11  10   9   8   7   6 ");
        System.out.println(" Point: " + minPoint);
        System.out.println("====================");
    }
    private int getAllPointOfBoard(int s, int e){
        int sum = 0;
        for (int i = s; i <= e; i++) {
            sum += cells[i].getPoint();
        }
        return sum;
    }
    public int checkWin(){  //0- min win    1- max win      2- draw     3- still play
        if (cells[5].getPoint() == 0 && cells[11].getPoint() == 0){
            maxPoint += getAllPointOfBoard(0, 4);
            minPoint += getAllPointOfBoard(6, 10);
            drawBoard();
            if (maxPoint > minPoint) return 1;
            if (maxPoint == minPoint) return 2;
            else return 0;
        }
        else return 3;
    }
    public int checkWinFake(){  //0- min win    1- max win      2- draw     3- still play
        if (cells[5].getPoint() == 0 && cells[11].getPoint() == 0){
            maxPoint += getAllPointOfBoard(0, 4);
            minPoint += getAllPointOfBoard(6, 10);
            if (maxPoint > minPoint) return 1;
            if (maxPoint == minPoint) return 2;
            else return 0;
        }
        else return 3;
    }
    public boolean checkPickMax(int position){
        for (int i = 6; i <= 11; i++) {
            if (position == i) return false;
        }
        return true;
    }
    public void spreadBoard(){
        // check dân max
        boolean tmp = true;
        for (int i = 0; i < 5; i++) {
            if(cells[i].getPoint() != 0){
                tmp = false;
                break;
            }
        }
        // rải dân max
        if (tmp){
            for (int i = 0; i < 5; i++) {
                cells[i].setPoint(1);
            }
            maxPoint -= 5;
        }
        // check dân min
        tmp = true;
        for (int i = 6; i < 11; i++) {
            if(cells[i].getPoint() != 0){
                tmp = false;
                break;
            }
        }
        // rải dân min
        if (tmp){
            for (int i = 6; i < 11; i++) {
                cells[i].setPoint(1);
            }
            minPoint -= 5;
        }
    }
    public boolean isFirstRoundWrong(int position, boolean direct){
        if (round == 1){
            if (turn){              //Max
                if (position == 0 && !direct) return true;
                if (position == 4 && direct) return true;
            }
            else {              //Max
                if (position == 6 && !direct) return true;
                if (position == 10 && direct) return true;
            }
        }
        return false;
    }
    public void coppyBoard(Board source){
        for (int i = 0; i<12; i++){
            int point = source.getCells()[i].getPoint();
            boolean isKing = source.getCells()[i].isKing();
            this.cells[i].setPoint(point);
            this.cells[i].setKing(isKing);
        }
        boolean turn = source.isTurn();
        int minPoint = source.getMinPoint();
        int maxPoint = source.getMaxPoint();
        int round = source.getRound();
        this.setTurn(turn);
        this.setMinPoint(minPoint);
        this.setMaxPoint(maxPoint);
        this.setRound(round);
    }
}
