package com.coen92.project.chessgame;

import com.coen92.project.chessgame.model.board.Board;

public class Main {

    public static void main(String[] args) {
        Board board = Board.createInitialBoard();

        System.out.println(board);
    }
}
