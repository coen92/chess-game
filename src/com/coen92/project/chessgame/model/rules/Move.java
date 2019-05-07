package com.coen92.project.chessgame.model.rules;

import com.coen92.project.chessgame.model.board.Board;
import com.coen92.project.chessgame.model.pieces.Piece;

public abstract class Move {

    final Board board;
    final Piece movedPiece;
    final int destinationCoordinate;

    private Move(final Board board, final Piece movedPiece, final int destinationCoordinate) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinationCoordinate = destinationCoordinate;
    }

    public int getDestinationCoordinate() {
        return this.destinationCoordinate;
    }

    public Piece getMovedPiece() {
        return movedPiece;
    }

    //this abstract execute method is going to prepare execution of new Board - this for itself doesn't change anything
    public abstract Board execute();

    public static final class MajorMove extends Move {

        public MajorMove(final Board board, final Piece movedPiece, final int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }

        @Override
        public Board execute() {
            final Board.Builder builder = new Board.Builder();
            //copying every Piece position at current Board for Pieces that don't make a move for a Player
            for(final Piece piece : this.board.currentPlayer().getActivePieces()) {
                //Todo: hashCode and equals for pieces - very important
                if(!this.movedPiece.equals(piece)) {
                    builder.setPiece(piece);
                }
            }
            //copying every Piece position at current Board for Pieces that don't make a move for a Opponent
            for(final Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()) {
                builder.setPiece(piece);
            }
            //moving the Piece that is trying to make a move and setting it position to new Board
            builder.setPiece(this.movedPiece.movePiece(this));
            //after the Piece made movement we need to set next move maker for an Opponent Alliance
            builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());

            return builder.build();
        }
    }

    public static final class AttackMove extends Move{

        final Piece attackedPiece;

        public AttackMove(final Board board, final Piece movedPiece, final int destinationCoordinate, final Piece attackedPiece) {
            super(board, movedPiece, destinationCoordinate);
            this.attackedPiece = attackedPiece;
        }

        @Override
        public Board execute() {
            return null;
        }
    }

    /* -------- THIS CLASS IS TEMPORARY ------ */
    //Todo: update body of the method for UponMove
    public static final class UponMove extends Move {

        public UponMove(Board board, Piece movedPiece, int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }

        @Override
        public Board execute() {
            return null;
        }
    }
    /* --------------------------------------- */
}
