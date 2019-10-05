package com.vippro.model;

public class DumbBot {
    private int depth;
//    private int acceptPoint;

    public DumbBot() {
    }
    public DumbBot(int depth) {
        this.depth = depth;
    }

    public int evaluate(Board board){
        return board.getMinPoint() - board.getMaxPoint();
    }
    public Move minimax(Board board, int depth){
//        dk kết thúc
        if (depth == 0 || board.checkWinFake() != 3) {
            Move rsMove = new Move(evaluate(board));
            return rsMove;
        }
        if(board.isTurn()){
            Move maxMove = new Move(1000);
            for (int pos = 0; pos < 5; pos++) {
                for (int dr = 0; dr <= 1; dr++){
                    Board boardmin = new Board(true);
                    boardmin.coppyBoard(board);
                    boolean canMove;
                    if (dr == 0)
                        canMove = boardmin.move(pos, false);
                    else
                        canMove = boardmin.move(pos, true);
                    if (!canMove) continue;
                    boardmin.setTurn(false);
                    Move move = minimax(boardmin, depth - 1);
                    move.position = pos;
                    if (dr == 0) move.direct = false;
                    else move.direct = true;
//                    board1.setPlayer(true);
                    maxMove = min(move, maxMove);
                }
            }
            return maxMove;
        }
        else {
            Move minMove = new Move(-1000);
            for (int pos = 6; pos < 11; pos++) {
                for (int dr = 0; dr <= 1; dr++){
                    Board boardmax = new Board(true);
                    boardmax.coppyBoard(board);
                    boolean canMove;
                    if (dr == 0)
                        canMove = boardmax.move(pos, false);
                    else
                        canMove = boardmax.move(pos, true);
                    if (!canMove) continue;
                    boardmax.setTurn(true);
                    Move move = minimax(boardmax, depth - 1);
                    move.position = pos;
                    if (dr == 0) move.direct = false;
                    else move.direct = true;
//                    board1.setPlayer(false);
                    minMove = max(move, minMove);
                }
            }
            return minMove;
        }
    }

    public Move max(Move move, Move maxMove){
        return (move.eval > maxMove.eval)?move:maxMove;
    }
    public Move min(Move move, Move minMove){
        return (move.eval < minMove.eval)?move:minMove;
    }


    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}
