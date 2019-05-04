package com.coen92.project.chessgame.model.player;

import com.coen92.project.chessgame.model.board.Board;
import com.coen92.project.chessgame.model.pieces.King;
import com.coen92.project.chessgame.model.pieces.Piece;
import com.coen92.project.chessgame.model.rules.Alliance;
import com.coen92.project.chessgame.model.rules.Move;
import com.coen92.project.chessgame.model.rules.MoveTransition;

import java.util.Collection;

public abstract class Player {

    protected final Board board;
    protected final King playerKing;
    protected final Collection<Move> legalMoves;

    public Player(final Board board, final Collection<Move> legalMoves, final Collection<Move> opponentMoves) {
        this.board = board;
        this.legalMoves = legalMoves;
        this.playerKing = establishKing();
    }

    private King establishKing() {
        for(final Piece piece : getActivePieces()) {
            if(piece.getPieceType().isKing()) {
                return (King)piece;
            }
        }
        throw new RuntimeException("Invalid board!");
    }

    public boolean isMoveLegal(final Move move) {
        return this.legalMoves.contains(move);
    }

    //Todo: Method below
    public boolean isInCheck() {
        return false;
    }

    //Todo: Method below
    public boolean isInCheckMate() {
        return false;
    }

    //Todo: Method below
    public boolean isInStaleMate() {
        return false;
    }

    //Todo: Method below
    public boolean isCastled() {
        return false;
    }

    //Todo: Method below
    public MoveTransition makeMove(final Move move) {
        return null;
    }

    public abstract Collection<Piece> getActivePieces();
    public abstract Alliance getAlliance();
    public abstract Player getOpponent();
}
