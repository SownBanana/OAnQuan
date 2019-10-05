package com.vippro.main;

import com.vippro.model.Board;

public class maintest {
    public static void main(String[] args) {
        Board board = new Board(true);
        Board boardnew = new Board(true);
        boardnew.coppyBoard(board);
        boardnew.getCells()[1].setPoint(2);
        boardnew.getCells()[2].setPoint(2);
        board.drawBoard();
        boardnew.drawBoard();
    }

}
