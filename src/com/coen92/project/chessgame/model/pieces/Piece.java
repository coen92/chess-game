package com.coen92.project.chessgame.model.pieces;

import com.coen92.project.chessgame.model.board.Board;
import com.coen92.project.chessgame.model.rules.Alliance;
import com.coen92.project.chessgame.model.rules.Move;

import java.util.Collection;

public abstract class Piece {

    private final int piecePosition;
    private final Alliance pieceAlliance;
    protected final boolean isFirstMove;

    protected Piece(final int piecePosition, final Alliance pieceAlliance) {
        this.piecePosition = piecePosition;
        this.pieceAlliance = pieceAlliance;
        // Todo: more work here!!
        this.isFirstMove = false;
    }

    public int getPiecePosition() {
        return piecePosition;
    }

    public Alliance getPieceAlliance() {
        return pieceAlliance;
    }

    public boolean isFirstMove() {
        return isFirstMove;
    }

    public abstract Collection<Move> calculateLegalMoves(final Board board);

    // creating enum for declaration of piece names for each piece type for printing it out in console
    public enum PieceType {

        PAWN("P"),
        KNIGHT("N"),
        BISHOP("B"),
        ROOK("R"),
        QUEEN("Q"),
        KING("K");

        private String pieceName;

        PieceType(final String pieceName) {
            this.pieceName = pieceName;
        }

        @Override
        public String toString() {
            return this.pieceName;
        }
    }
}
