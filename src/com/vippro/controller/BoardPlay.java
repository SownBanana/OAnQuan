package com.vippro.controller;

import com.vippro.model.Board;

import java.util.Scanner;

public class BoardPlay {
    public void play(String maxPlayer, String minPlayer){
        Board board = new Board(true);
        board.drawBoard();
        boolean turn = true;
        while (true){
            if (turn){
                boolean check = false;
                while (!check){
                    System.out.println("Lượt đi của "+ maxPlayer + " (chọn ô 0 - 4, trái - 0, phải - 1):");
                    int pos = new Scanner(System.in).nextInt();
                    int directTmp = new Scanner(System.in).nextInt();
                    boolean direct = (directTmp == 0)?false:true;
                    if (board.checkPickMax(pos))
                        check = board.move(pos, direct);
                    else check = false;
                }
                turn = false;
            }
            else{
                boolean check = false;
                while (!check){
                    System.out.println("Lượt đi của "+ minPlayer + " (chọn ô 6 - 10, trái - 0, phải - 1):");
                    int pos = new Scanner(System.in).nextInt();
                    int directTmp = new Scanner(System.in).nextInt();
                    boolean direct = (directTmp == 1)?false:true;
                    if (!board.checkPickMax(pos))
                        check = board.move(pos, direct);
                    else check = false;
                }
                turn = true;
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
