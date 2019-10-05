package com.vippro.controller;

import com.vippro.model.Board;
import com.vippro.model.DumbBot;
import com.vippro.model.Move;

import java.util.Scanner;

public class BoardPlay {
    public void play(String maxPlayer, String minPlayer){
        Board board = new Board(true);
        board.drawBoard();
        board.setTurn(true);
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
//                    else check = false;
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
    public void playWithBot(String maxPlayer, String minPlayer){
        Board board = new Board(true);
        board.drawBoard();
        board.setTurn(true);
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
                }
                board.setTurn(false);
            }
            else{
                System.out.println("Lượt đi của "+ minPlayer+
                        "<=======================================================================================================");
                DumbBot dumbBot = new DumbBot(  5);
                Board fakeBoard = new Board(true);
                fakeBoard.coppyBoard(board);
                Move move = dumbBot.minimax(fakeBoard, dumbBot.getDepth());
                System.out.println(move.toString());
//                Move myMove = new Move();
//                myMove.position = new Scanner(System.in).nextInt();
//                int direct = new Scanner(System.in).nextInt();
//                if ((direct == 0)) {
//                    myMove.direct = false;
//                } else {
//                    myMove.direct = true;
//                }
                board.move(move.position, move.direct);
//                board.move(myMove.position, myMove.direct);
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

// System.out.println("Lượt đi của "+ minPlayer);
//         DumbBot dumbBot = new DumbBot(board, 2);
//         dumbBot.setMoveOut(dumbBot.minimax(board, dumbBot.getDepth()));
//         Move move = dumbBot.getMoveOut();
