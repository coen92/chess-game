package com.coen92.project.chessgame.model.player;

import com.coen92.project.chessgame.model.board.Board;
import com.coen92.project.chessgame.model.pieces.King;
import com.coen92.project.chessgame.model.pieces.Piece;
import com.coen92.project.chessgame.model.rules.Alliance;
import com.coen92.project.chessgame.model.rules.Move;
import com.coen92.project.chessgame.model.rules.MoveStatus;
import com.coen92.project.chessgame.model.rules.MoveTransition;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Player {

    protected final Board board;
    protected final King playerKing;
    protected final Collection<Move> legalMoves;
    private final boolean isInCheck;

    public Player(final Board board, final Collection<Move> legalMoves, final Collection<Move> opponentMoves) {
        this.board = board;
        this.legalMoves = legalMoves;
        this.playerKing = establishKing();
        this.isInCheck = !Player.calculateAttacksOnTile(this.playerKing.getPiecePosition(), opponentMoves).isEmpty();
    }

    public King getPlayerKing() {
        return playerKing;
    }

    public Collection<Move> getLegalMoves() {
        return legalMoves;
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

    public boolean isInCheck() {
        return this.isInCheck;
    }

    public boolean isInCheckMate() {
        return this.isInCheck && !hasEscapeMoves();
    }

    public boolean isInStaleMate() {
        return !this.isInCheck && !hasEscapeMoves();
    }

    //Todo: Method below
    public boolean isCastled() {
        return false;
    }

    //method to accept the Board change if the Move we're trying to make is acceptable
    public MoveTransition makeMove(final Move move) {
        final Board transitionBoard = move.execute();
        final Collection<Move> kingAttacks = Player.calculateAttacksOnTile(
                transitionBoard.currentPlayer().getOpponent().getPlayerKing().getPiecePosition(),
                transitionBoard.currentPlayer().getLegalMoves());

        //if move doesn't belong to collection of legal moves Board won't change
        if(!isMoveLegal(move)) {
            return new MoveTransition(this.board, move, MoveStatus.ILLEGAL_MOVE);
        }
        //if move would get our King in danger zone Board won't change
        if(!kingAttacks.isEmpty()) {
            return new MoveTransition(this.board, move, MoveStatus.LEAVES_PLAYER_IN_CHECK);
        }
        //otherwise we return new Board with executed move we set in our mehtod
        return new MoveTransition(transitionBoard, move, MoveStatus.DONE);
    }

    //method for returning the possible opponent attack moves
    protected static Collection<Move> calculateAttacksOnTile(final int piecePosition, final Collection<Move> opponentMoves) {
        final List<Move> attackMoves = new ArrayList<>();

        for(final Move move : opponentMoves) {
            if(piecePosition == move.getDestinationCoordinate()) {
                attackMoves.add(move);
            }
        }
        return ImmutableList.copyOf(attackMoves);
    }

    protected boolean hasEscapeMoves() {
        for(final Move move : this.legalMoves) {
            final MoveTransition transition = makeMove(move);

            if(transition.getMoveStatus().isDone()) {
                return true;
            }
        }
        return false;
    }

    public abstract Collection<Piece> getActivePieces();
    public abstract Alliance getAlliance();
    public abstract Player getOpponent();
    protected abstract Collection<Move> calculateKingCastles(Collection<Move> playerLegals, Collection<Move> opponentsLegals);
}
