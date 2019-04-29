package com.coen92.project.chessgame.model.pieces;

import com.coen92.project.chessgame.model.board.Board;
import com.coen92.project.chessgame.model.rules.Alliance;
import com.coen92.project.chessgame.model.rules.Move;
import com.coen92.project.chessgame.model.utils.BoardUtils;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pawn extends Piece {

    private final static int[] CANDIDATE_MOVE_COORDINATE = {8, 16};

    public Pawn(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();

        for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE) {
            int candidateDestinationCoordinate = this.getPiecePosition() + (this.getPieceAlliance().getDirection() * currentCandidateOffset);

            // skipping the addition of legal moves if destination doesn't lie on board
            if(!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                continue;
            }
            // setting normal move for Pawn
            if(currentCandidateOffset == 8 && !board.getChessTile(candidateDestinationCoordinate).isTileOccupied()) {
                legalMoves.add(new Move.UponMove(board, this, candidateDestinationCoordinate));
            }
            // setting optional jump for Pawn if it's first move of it and destination tile is not occupied
            else if (currentCandidateOffset == 16 && this.isFirstMove()
                    && (BoardUtils.SECOND_ROW[this.getPiecePosition()] && this.getPieceAlliance().isBlack())
                    || (BoardUtils.SEVENTH_ROW[this.getPiecePosition()] && this.getPieceAlliance().isWhite())) {
                final int behindCandidateDestinationCoordinate = this.getPiecePosition() + (this.getPieceAlliance().getDirection() * 8);

                if(!board.getChessTile(behindCandidateDestinationCoordinate).isTileOccupied()
                        && !board.getChessTile(candidateDestinationCoordinate).isTileOccupied()) {
                    legalMoves.add(new Move.UponMove(board, this, candidateDestinationCoordinate));
                }
            }
            // setting one option for attacking move - when Pawn attacks Piece which is in front-right Tile
            else if (currentCandidateOffset == 7
                    && !((BoardUtils.EIGHTH_COLUMN[this.getPiecePosition()] && this.getPieceAlliance().isWhite())
                    || (BoardUtils.FIRST_COLUMN[this.getPiecePosition()] && this.getPieceAlliance().isBlack()))) {
                //Todo: change repetable code to external method
                if(board.getChessTile(candidateDestinationCoordinate).isTileOccupied()) {
                    final Piece pieceOnCandidate = board.getChessTile(candidateDestinationCoordinate).getPiece();
                    if(this.getPieceAlliance() != pieceOnCandidate.getPieceAlliance()) {
                        legalMoves.add(new Move.AttackMove(board, this, candidateDestinationCoordinate, pieceOnCandidate));
                    }
                }
            }
            // setting one option for attacking move - when Pawn attacks Piece which is in front-left Tile
            else if (currentCandidateOffset == 9
                    && !((BoardUtils.EIGHTH_COLUMN[this.getPiecePosition()] && this.getPieceAlliance().isBlack())
                    || (BoardUtils.FIRST_COLUMN[this.getPiecePosition()] && this.getPieceAlliance().isWhite()))) {
                //Todo: change repetable code to external method
                if(board.getChessTile(candidateDestinationCoordinate).isTileOccupied()) {
                    final Piece pieceOnCandidate = board.getChessTile(candidateDestinationCoordinate).getPiece();
                    if(this.getPieceAlliance() != pieceOnCandidate.getPieceAlliance()) {
                        legalMoves.add(new Move.AttackMove(board, this, candidateDestinationCoordinate, pieceOnCandidate));
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }
}
