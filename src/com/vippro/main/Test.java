package com.vippro.main;

import com.vippro.controller.BoardPlay;
import com.vippro.model.Board;
import com.vippro.model.Cell;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        System.out.println("Nhập tên người chơi 1: ");
        String maxPlayer = new Scanner(System.in).nextLine();
        System.out.println("Nhập tên người chơi 2: ");
        String minPlayer = new Scanner(System.in).nextLine();
        BoardPlay boardPlay = new BoardPlay();
        boardPlay.play(maxPlayer, minPlayer);
    }
}
