package com.coen92.project.chessgame.model.pieces;

import com.coen92.project.chessgame.model.board.Board;
import com.coen92.project.chessgame.model.rules.Alliance;
import com.coen92.project.chessgame.model.rules.Move;

import java.util.Collection;

public abstract class Piece {

    protected final PieceType pieceType;
    private final int piecePosition;
    private final Alliance pieceAlliance;
    protected final boolean isFirstMove;
    private final int cachedHashCode;

    protected Piece(final PieceType pieceType, final int piecePosition, final Alliance pieceAlliance) {
        this.pieceType = pieceType;
        this.piecePosition = piecePosition;
        this.pieceAlliance = pieceAlliance;
        // Todo: more work here!!
        this.isFirstMove = false;
        this.cachedHashCode = getCachedHashCode();
    }

    public PieceType getPieceType() {
        return this.pieceType;
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

    public int getCachedHashCode() {
        int result = pieceType.hashCode();
        result = 31 * result + pieceAlliance.hashCode();
        result = 31 * result + piecePosition;
        result = 31 * result + (isFirstMove ? 1 : 0);
        return result;
    }

    @Override
    public int hashCode() {
        return this.cachedHashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(!(obj instanceof Piece)) {
            return false;
        }
        final Piece objPiece = (Piece) obj;
        return piecePosition == objPiece.getPiecePosition() && pieceType == objPiece.getPieceType()
                && pieceAlliance == objPiece.getPieceAlliance() && isFirstMove == objPiece.isFirstMove();

    }

    public abstract Collection<Move> calculateLegalMoves(final Board board);
    //method for creating new Piece in when moving the old one to the legal move position
    public abstract Piece movePiece(final Move move);

    // creating enum for declaration of piece names for each piece type for printing it out in console
    public enum PieceType {

        PAWN("P") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        KNIGHT("N") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        BISHOP("B") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        ROOK("R") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        QUEEN("Q") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        KING("K") {
            @Override
            public boolean isKing() {
                return true;
            }
        };

        private String pieceName;

        PieceType(final String pieceName) {
            this.pieceName = pieceName;
        }

        @Override
        public String toString() {
            return this.pieceName;
        }

        public abstract boolean isKing();
    }
}
