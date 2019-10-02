package com.vippro.controller;

import com.vippro.model.Board;
import com.vippro.model.Move;

import java.util.Scanner;

public class BoardPlay {
    public void play(String maxPlayer, String minPlayer){
        Board board = new Board(true);
        board.drawBoard();
        board.setTurn(false);
        while (true){
            if (board.isTurn()){
                boolean check = false;
                while (!check){
                    System.out.println("Lượt đi của "+ maxPlayer + " (chọn ô 0 - 4, trái - 0, phải - 1):");
                    Move move = new Move();
                    move.position = new Scanner(System.in).nextInt();
                    int direct = new Scanner(System.in).nextInt();
                    if ((direct == 0)) {
                        move.direct = false;
                    } else {
                        move.direct = true;
                    }
                    if (board.checkPickMax(move.position))
                        check = board.move(move.position, move.direct);
                    else check = false;
                }
                board.setTurn(false);
            }
            else{
                boolean check = false;
                while (!check){
                    System.out.println("Lượt đi của "+ minPlayer + " (chọn ô 6 - 10, trái - 0, phải - 1):");
                    Move move = new Move();
                    move.position = new Scanner(System.in).nextInt();
                    int direct = new Scanner(System.in).nextInt();
                    if ((direct == 0)) {
                        move.direct = true;
                    } else {
                        move.direct = false;
                    }
                    if (!board.checkPickMax(move.position))
                        check = board.move(move.position, move.direct);
                    else check = false;
                }
                board.setTurn(true);
            }
            if (board.checkWin() != 3) break;
            board.spreadBoard();
            board.drawBoard();
        }
        if (board.checkWin() == 2) System.out.println("Hoà!!!");
        else if ((board.checkWin() == 0))
            System.out.println("Người thắng cuộc là " + minPlayer);
        else
            System.out.println("Người thắng cuộc là " + maxPlayer);
    }
}
