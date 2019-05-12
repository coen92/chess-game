package com.coen92.project.chessgame.model.rules;

import com.coen92.project.chessgame.model.board.Board;
import com.coen92.project.chessgame.model.pieces.Piece;

public abstract class Move {

    final Board board;
    final Piece movedPiece;
    final int destinationCoordinate;

    public static final Move NULL_MOVE = new NullMove();

    private Move(final Board board, final Piece movedPiece, final int destinationCoordinate) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinationCoordinate = destinationCoordinate;
    }

    public Piece getMovedPiece() {
        return movedPiece;
    }

    public int getDestinationCoordinate() {
        return this.destinationCoordinate;
    }

    public int getCurrentCoordinate() {
        return this.getMovedPiece().getPiecePosition();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + this.getDestinationCoordinate();
        result = prime * result + this.movedPiece.hashCode();
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(!(obj instanceof Move)) {
            return false;
        }
        final Move objMove = (Move) obj;

        return getDestinationCoordinate() == objMove.getDestinationCoordinate()
                && getMovedPiece().equals(objMove.getMovedPiece());
    }

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

    public boolean isAttack() {
        return false;
    }

    public boolean isCastlingMove() {
        return false;
    }

    public Piece getAttackedPiece() {
        return null;
    }

    public static final class MajorMove extends Move {
        public MajorMove(final Board board, final Piece movedPiece, final int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }
    }

    public static class AttackMove extends Move{
        final Piece attackedPiece;

        public AttackMove(final Board board, final Piece movedPiece, final int destinationCoordinate, final Piece attackedPiece) {
            super(board, movedPiece, destinationCoordinate);
            this.attackedPiece = attackedPiece;
        }

        @Override
        public Board execute() {
            return null;
        }

        @Override
        public int hashCode() {
            return this.attackedPiece.hashCode() + super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if(this == obj) {
                return true;
            }
            if(!(obj instanceof AttackMove)) {
                return false;
            }
            final AttackMove objAttackMove = (AttackMove) obj;
            return super.equals(objAttackMove)
                    && getAttackedPiece().equals((objAttackMove).getAttackedPiece());
        }

        @Override
        public boolean isAttack() {
            return true;
        }

        @Override
        public Piece getAttackedPiece() {
            return this.attackedPiece;
        }
    }

    /* -------- THIS CLASS IS TEMPORARY ------ */
    //Todo: update body of the method for PawnMove
    public static final class PawnMove extends Move {
        public PawnMove(Board board, Piece movedPiece, int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }
    }
    /* --------------------------------------- */
    public static class PawnAttackMove extends AttackMove {
        public PawnAttackMove(Board board, Piece movedPiece, int destinationCoordinate, Piece attackedPiece) {
            super(board, movedPiece, destinationCoordinate, attackedPiece);
        }
    }

    public static final class PawnEnPassantAttackMove extends PawnAttackMove {
        public PawnEnPassantAttackMove(Board board, Piece movedPiece, int destinationCoordinate, Piece attackedPiece) {
            super(board, movedPiece, destinationCoordinate, attackedPiece);
        }
    }

    public static final class PawnJump extends Move {
        public PawnJump(Board board, Piece movedPiece, int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }
    }

    static abstract class CastleMove extends Move {
        public CastleMove(Board board, Piece movedPiece, int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }
    }

    public static final class KingSideCastleMove extends CastleMove {
        public KingSideCastleMove(Board board, Piece movedPiece, int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }
    }

    public static final class QueenSideCastleMove extends CastleMove {
        public QueenSideCastleMove(Board board, Piece movedPiece, int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }
    }

    public static final class NullMove extends Move {
        public NullMove() {
            super(null, null, -1);
        }

        @Override
        public Board execute() {
            throw new RuntimeException("Cannot execute NullMove");
        }
    }

    public static class MoveFactory {
        private MoveFactory() {
            throw new RuntimeException("Not instantiable!");
        }

        //DESCRIPTION FOR THIS SHIT TOO !!!!!!!!!!!!!!!!!!!!!
        public static Move createMove(final Board board, final int currentCoordinate, final int destinationCoordinate) {
            for(final Move move : board.getAllLegalMoves()) {
                if(move.getDestinationCoordinate() == destinationCoordinate
                        && move.getCurrentCoordinate() == currentCoordinate) {
                    return move;
                }
            }
            return NULL_MOVE;
        }
    }
}
