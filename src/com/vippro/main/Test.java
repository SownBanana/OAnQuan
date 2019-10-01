package com.vippro.main;

import com.vippro.model.Board;
import com.vippro.model.Cell;

public class Test {
    public static void main(String[] args) {
        Cell[] cells = new Cell[12];
        for (int i = 0; i < 5; i++) {
            cells[i] = new Cell(false,5);
        }
        for (int i = 6; i < 11; i++) {
            cells[i] = new Cell(false,5);
        }
        cells[5] = new Cell(true,10);
        cells[11] = new Cell(true,10);

        Board board = new Board(cells, 0, 0, true);
        board.drawBoard();
        board.move(0, true);
        board.drawBoard();
    }
}
